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

package com.elusivehawk.engine.physics.jbullet.collision.broadphase;

import com.elusivehawk.engine.math.Vector;

/*
 * NOTICE: Edited by Elusivehawk
 */
/**
 * 
 * @author jezek2
 */
class SimpleBroadphaseProxy extends BroadphaseProxy
{
	protected final Vector min = new Vector(3), max = new Vector(3);
	
	public SimpleBroadphaseProxy(){}
	
	public SimpleBroadphaseProxy(Vector minpt, Vector maxpt, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Object multiSapProxy)
	{
		super(userPtr, collisionFilterGroup, collisionFilterMask, multiSapProxy);
		this.min.set(minpt);
		this.max.set(maxpt);
	}
	
}