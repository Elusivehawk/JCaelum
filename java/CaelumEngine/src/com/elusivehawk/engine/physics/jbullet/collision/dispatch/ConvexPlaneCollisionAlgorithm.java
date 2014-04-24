/*
 * Java port of Bullet (c) 2008 Martin Dvorak <jezek2@advel.cz>
 *
 * Bullet Continuous Collision Detection and Physics Library
 * Copyright (c) 2003-2008 Erwin Coumans  http://www.bulletphysics.com/
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from
 * the use of this software.
 * 
 * Permission is granted to anyone to use this software for any purpose, 
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 * 
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 * 3. This notice may not be removed or altered from any source distribution.
 */

package com.elusivehawk.engine.physics.jbullet.collision.dispatch;

import com.elusivehawk.engine.math.Vector;
import com.elusivehawk.engine.physics.jbullet.collision.broadphase.CollisionAlgorithm;
import com.elusivehawk.engine.physics.jbullet.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.elusivehawk.engine.physics.jbullet.collision.broadphase.DispatcherInfo;
import com.elusivehawk.engine.physics.jbullet.collision.narrowphase.PersistentManifold;
import com.elusivehawk.engine.physics.jbullet.collision.shapes.ConvexShape;
import com.elusivehawk.engine.physics.jbullet.collision.shapes.StaticPlaneShape;
import com.elusivehawk.engine.physics.jbullet.linearmath.Transform;
import com.elusivehawk.engine.physics.jbullet.util.ObjectArrayList;
import com.elusivehawk.engine.physics.jbullet.util.ObjectPool;
import cz.advel.stack.Stack;

/*
 * NOTICE: Edited by Elusivehawk
 */
/**
 * ConvexPlaneCollisionAlgorithm provides convex/plane collision detection.
 * 
 * @author jezek2
 */
public class ConvexPlaneCollisionAlgorithm extends CollisionAlgorithm {

	private boolean ownManifold;
	private PersistentManifold manifoldPtr;
	private boolean isSwapped;
	
	public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject col0, CollisionObject col1, boolean isSwapped) {
		super.init(ci);
		this.ownManifold = false;
		this.manifoldPtr = mf;
		this.isSwapped = isSwapped;

		CollisionObject convexObj = isSwapped ? col1 : col0;
		CollisionObject planeObj = isSwapped ? col0 : col1;

		if (manifoldPtr == null && dispatcher.needsCollision(convexObj, planeObj)) {
			manifoldPtr = dispatcher.getNewManifold(convexObj, planeObj);
			ownManifold = true;
		}
	}
	
	@Override
	public void destroy() {
		if (ownManifold) {
			if (manifoldPtr != null) {
				dispatcher.releaseManifold(manifoldPtr);
			}
			manifoldPtr = null;
		}
	}
	
	@Override
	public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) {
		if (manifoldPtr == null) {
			return;
		}
		
		Transform tmpTrans = Stack.alloc(Transform.class);

		CollisionObject convexObj = isSwapped ? body1 : body0;
		CollisionObject planeObj = isSwapped ? body0 : body1;

		ConvexShape convexShape = (ConvexShape) convexObj.getCollisionShape();
		StaticPlaneShape planeShape = (StaticPlaneShape) planeObj.getCollisionShape();

		boolean hasCollision = false;
		Vector planeNormal = planeShape.getPlaneNormal(Stack.alloc(new Vector(3)));
		float planeConstant = planeShape.getPlaneConstant();

		Transform planeInConvex = Stack.alloc(Transform.class);
		convexObj.getWorldTransform(planeInConvex);
		planeInConvex.inverse();
		planeInConvex.mul(planeObj.getWorldTransform(tmpTrans));

		Transform convexInPlaneTrans = Stack.alloc(Transform.class);
		convexInPlaneTrans.inverse(planeObj.getWorldTransform(tmpTrans));
		convexInPlaneTrans.mul(convexObj.getWorldTransform(tmpTrans));

		Vector tmp = Stack.alloc(new Vector(3));
		tmp.negate(planeNormal);
		planeInConvex.basis.transform(tmp);

		Vector vtx = convexShape.localGetSupportingVertex(tmp, Stack.alloc(new Vector(3)));
		Vector vtxInPlane = Stack.alloc(vtx);
		convexInPlaneTrans.transform(vtxInPlane);

		float distance = (planeNormal.dot(vtxInPlane) - planeConstant);

		Vector vtxInPlaneProjected = Stack.alloc(new Vector(3));
		tmp.scale(distance, planeNormal);
		vtxInPlaneProjected.sub(vtxInPlane, tmp);

		Vector vtxInPlaneWorld = Stack.alloc(vtxInPlaneProjected);
		planeObj.getWorldTransform(tmpTrans).transform(vtxInPlaneWorld);

		hasCollision = distance < manifoldPtr.getContactBreakingThreshold();
		resultOut.setPersistentManifold(manifoldPtr);
		if (hasCollision) {
			// report a contact. internally this will be kept persistent, and contact reduction is done
			Vector normalOnSurfaceB = Stack.alloc(planeNormal);
			planeObj.getWorldTransform(tmpTrans).basis.transform(normalOnSurfaceB);

			Vector pOnB = Stack.alloc(vtxInPlaneWorld);
			resultOut.addContactPoint(normalOnSurfaceB, pOnB, distance);
		}
		if (ownManifold) {
			if (manifoldPtr.getNumContacts() != 0) {
				resultOut.refreshContactPoints();
			}
		}
	}

	@Override
	public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) {
		// not yet
		return 1f;
	}

	@Override
	public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray) {
		if (manifoldPtr != null && ownManifold) {
			manifoldArray.add(manifoldPtr);
		}
	}

	////////////////////////////////////////////////////////////////////////////
	
	public static class CreateFunc extends CollisionAlgorithmCreateFunc {
		private final ObjectPool<ConvexPlaneCollisionAlgorithm> pool = ObjectPool.get(ConvexPlaneCollisionAlgorithm.class);

		@Override
		public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1) {
			ConvexPlaneCollisionAlgorithm algo = pool.get();
			if (!swapped) {
				algo.init(null, ci, body0, body1, false);
			}
			else {
				algo.init(null, ci, body0, body1, true);
			}
			return algo;
		}

		@Override
		public void releaseCollisionAlgorithm(CollisionAlgorithm algo) {
			pool.release((ConvexPlaneCollisionAlgorithm)algo);
		}
	}
	
}