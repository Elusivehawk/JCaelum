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
import com.elusivehawk.engine.physics.jbullet.linearmath.Transform;

/*
 * NOTICE: Edited by Elusivehawk
 */
/**
 * ConvexShape is an abstract shape class. It describes general convex shapes
 * using the {@link #localGetSupportingVertex localGetSupportingVertex} interface
 * used in combination with GJK or ConvexCast.
 * 
 * @author jezek2
 */
public abstract class ConvexShape extends CollisionShape
{
	public static final int MAX_PREFERRED_PENETRATION_DIRECTIONS = 10;
	
	public abstract Vector localGetSupportingVertex(Vector vec, Vector out);
	
	//#ifndef __SPU__
	public abstract Vector localGetSupportingVertexWithoutMargin(Vector vec, Vector out);
	
	//notice that the vectors should be unit length
	public abstract void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector[] vectors, Vector[] supportVerticesOut, int numVectors);
	//#endif
	
	public abstract void getAabbSlow(Transform t, Vector aabbMin, Vector aabbMax);
	
	public abstract void setLocalScaling(Vector scaling);
	
	public abstract Vector getLocalScaling(Vector out);
	
	@Override
	public abstract void setMargin(float margin);
	
	@Override
	public abstract float getMargin();
	
	public abstract int getNumPreferredPenetrationDirections();
	
	public abstract void getPreferredPenetrationDirection(int index, Vector penetrationVector);
	
}