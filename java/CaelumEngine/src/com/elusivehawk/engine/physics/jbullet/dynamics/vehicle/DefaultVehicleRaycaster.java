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

package com.elusivehawk.engine.physics.jbullet.dynamics.vehicle;

import com.elusivehawk.engine.math.Vector;
import com.elusivehawk.engine.physics.jbullet.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.elusivehawk.engine.physics.jbullet.dynamics.DynamicsWorld;
import com.elusivehawk.engine.physics.jbullet.dynamics.RigidBody;

/*
 * NOTICE: Edited by Elusivehawk
 */
/**
 * Default implementation of {@link VehicleRaycaster}.
 * 
 * @author jezek2
 */
public class DefaultVehicleRaycaster extends VehicleRaycaster {

	protected DynamicsWorld dynamicsWorld;

	public DefaultVehicleRaycaster(DynamicsWorld world) {
		this.dynamicsWorld = world;
	}

	@Override
	public Object castRay(Vector from, Vector to, VehicleRaycasterResult result) {
		//RayResultCallback& resultCallback;

		ClosestRayResultCallback rayCallback = new ClosestRayResultCallback(from, to);

		this.dynamicsWorld.rayTest(from, to, rayCallback);

		if (rayCallback.hasHit()) {
			RigidBody body = RigidBody.upcast(rayCallback.collisionObject);
			if (body != null && body.hasContactResponse()) {
				result.hitPointInWorld.set(rayCallback.hitPointWorld);
				result.hitNormalInWorld.set(rayCallback.hitNormalWorld);
				result.hitNormalInWorld.normalize();
				result.distFraction = rayCallback.closestHitFraction;
				return body;
			}
		}
		return null;
	}

}