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

package com.elusivehawk.engine.physics.jbullet.collision.shapes;

import com.elusivehawk.engine.math.Vector;
import com.elusivehawk.engine.physics.jbullet.linearmath.VectorUtil;
import cz.advel.stack.Stack;

/*
 * NOTICE: Edited by Elusivehawk
 */
/**
 * StridingMeshInterface is the abstract class for high performance access to
 * triangle meshes. It allows for sharing graphics and collision meshes. Also
 * it provides locking/unlocking of graphics meshes that are in GPU memory.
 * 
 * @author jezek2
 */
public abstract class StridingMeshInterface {

	protected final Vector scaling = new Vector(1f, 1f, 1f);
	
	public void internalProcessAllTriangles(InternalTriangleIndexCallback callback, Vector aabbMin, Vector aabbMax) {
		int graphicssubparts = getNumSubParts();
		Vector[] triangle/*[3]*/ = new Vector[]{Stack.alloc(new Vector(3)), Stack.alloc(new Vector(3)), Stack.alloc(new Vector(3))};

		Vector meshScaling = getScaling(Stack.alloc(new Vector(3)));

		for (int part=0; part<graphicssubparts; part++) {
			VertexData data = getLockedReadOnlyVertexIndexBase(part);

			for (int i=0, cnt=data.getIndexCount()/3; i<cnt; i++) {
				data.getTriangle(i*3, meshScaling, triangle);
				callback.internalProcessTriangleIndex(triangle, part, i);
			}

			unLockReadOnlyVertexBase(part);
		}
	}

	private static class AabbCalculationCallback extends InternalTriangleIndexCallback {
		public final Vector aabbMin = new Vector(1e30f, 1e30f, 1e30f);
		public final Vector aabbMax = new Vector(-1e30f, -1e30f, -1e30f);

		public void internalProcessTriangleIndex(Vector[] triangle, int partId, int triangleIndex) {
			VectorUtil.setMin(aabbMin, triangle[0]);
			VectorUtil.setMax(aabbMax, triangle[0]);
			VectorUtil.setMin(aabbMin, triangle[1]);
			VectorUtil.setMax(aabbMax, triangle[1]);
			VectorUtil.setMin(aabbMin, triangle[2]);
			VectorUtil.setMax(aabbMax, triangle[2]);
		}
	}
	
	public void calculateAabbBruteForce(Vector aabbMin, Vector aabbMax) {
		// first calculate the total aabb for all triangles
		AabbCalculationCallback aabbCallback = new AabbCalculationCallback();
		aabbMin.set(-1e30f, -1e30f, -1e30f);
		aabbMax.set(1e30f, 1e30f, 1e30f);
		internalProcessAllTriangles(aabbCallback, aabbMin, aabbMax);

		aabbMin.set(aabbCallback.aabbMin);
		aabbMax.set(aabbCallback.aabbMax);
	}
	
	/**
	 * Get read and write access to a subpart of a triangle mesh.
	 * This subpart has a continuous array of vertices and indices.
	 * In this way the mesh can be handled as chunks of memory with striding
	 * very similar to OpenGL vertexarray support.
	 * Make a call to unLockVertexBase when the read and write access is finished.
	 */
	public abstract VertexData getLockedVertexIndexBase(int subpart/*=0*/);

	public abstract VertexData getLockedReadOnlyVertexIndexBase(int subpart/*=0*/);

	/**
	 * unLockVertexBase finishes the access to a subpart of the triangle mesh.
	 * Make a call to unLockVertexBase when the read and write access (using getLockedVertexIndexBase) is finished.
	 */
	public abstract void unLockVertexBase(int subpart);

	public abstract void unLockReadOnlyVertexBase(int subpart);

	/**
	 * getNumSubParts returns the number of seperate subparts.
	 * Each subpart has a continuous array of vertices and indices.
	 */
	public abstract int getNumSubParts();

	public abstract void preallocateVertices(int numverts);
	public abstract void preallocateIndices(int numindices);

	public Vector getScaling(Vector out) {
		out.set(scaling);
		return out;
	}
	
	public void setScaling(Vector scaling) {
		this.scaling.set(scaling);
	}
	
}