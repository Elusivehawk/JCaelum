
package com.elusivehawk.util.math;

import com.elusivehawk.util.RNG;

/**
 * 
 * Convenience class for math functions.
 * 
 * @author Elusivehawk
 */
public final class MathHelper
{
	public static final float PI = 3.141592653589793238f;
	
	private MathHelper(){}
	
	public static boolean bounds(float f, float min, float max)
	{
		return clamp(f, min, max) == f;
	}
	
	public static boolean bounds(int i, int min, int max)
	{
		return clamp(i, min, max) == i;
	}
	
	public static boolean bounds(long l, long min, long max)
	{
		return clamp(l, min, max) == l;
	}
	
	public static float clamp(float f, float min, float max)
	{
		return Math.min(max, Math.max(f, min));
	}
	
	public static int clamp(int i, int min, int max)
	{
		return Math.min(max, Math.max(i, min));
	}
	
	public static long clamp(long l, long min, long max)
	{
		return Math.min(max, Math.max(l, min));
	}
	
	public static Vector cross(Vector one, Vector two)
	{
		assert one.getSize() <= 3 && two.getSize() <= 3;
		
		float ax = one.get(MathConst.X);
		float ay = one.get(MathConst.Y);
		float az = one.get(MathConst.Z);
		
		float bx = two.get(MathConst.X);
		float by = two.get(MathConst.Y);
		float bz = two.get(MathConst.Z);
		
		return new Vector((ay * bz) - (az * by),
				(az * bx) - (ax * bz),
				(ax * by) - (ay * bx));
	}
	
	public static float dist(Vector from, Vector to)
	{
		return (float)Math.sqrt(distSquare(from, to));
	}
	
	public static float distSquare(Vector from, Vector to)
	{
		int size = Math.min(from.getSize(), to.getSize());
		float ret = 0f;
		
		for (int c = 0; c < size; c++)
		{
			ret += square(to.get(c) - from.get(c));
			
		}
		
		return ret;
	}
	
	public static float dot(Vector one, Vector two)
	{
		int size = Math.min(one.getSize(), two.getSize());
		float ret = 0f;
		
		for (int c = 0; c < size; c++)
		{
			ret += one.get(c) * two.get(c);
			
		}
		
		return ret;
	}
	
	public static float interpolate(float one, float two, float factor)
	{
		assert bounds(factor, 0.0f, 1.0f);
		
		return ((two * factor) + ((1f - factor) * one));
	}
	
	public static Vector interpolate(Vector one, Vector two, float factor)
	{
		return interpolate(one, two, factor, new Vector(Math.min(one.getSize(), two.getSize())));
	}
	
	public static Vector interpolate(Vector one, Vector two, float factor, Vector dest)
	{
		for (int c = 0; c < dest.getSize(); c++)
		{
			dest.set(c, ((two.get(c) * factor) + ((1f - factor) * one.get(c))), false);
			
		}
		
		return dest;
	}
	
	public static boolean isOdd(int i)
	{
		return (i & 1) == 1;
	}
	
	public static float length(Vector vec)
	{
		return (float)Math.sqrt(lengthSquared(vec));
	}
	
	public static float lengthSquared(Vector vec)
	{
		float ret = 0f;
		
		for (int c = 0; c < vec.getSize(); c++)
		{
			ret += square(vec.get(c));
			
		}
		
		return ret;
	}
	
	public static int percent(int i, int max)
	{
		return (int)(((float)i / max) * 100);
	}
	
	public static float pow(float f, int pow)
	{
		float ret = f;
		
		for (int c = 0; c < pow; c++)
		{
			ret *= f;
			
		}
		
		return ret;
	}
	
	public static int pow(int i, int pow)
	{
		int ret = i;
		
		for (int c = 0; c < pow; c++)
		{
			ret *= i;
			
		}
		
		return ret;
	}
	
	public static boolean rollDice(float weight)
	{
		return weight > RNG.rng().nextFloat();
	}
	
	public static float square(float f)
	{
		return f * f;
	}
	
	public static int square(int i)
	{
		return i * i;
	}
	
	public static float toDegrees(float radian)
	{
		return (radian * 180) / PI;
	}
	
	public static float toRadians(float degree)
	{
		return (degree * PI) / 180;
	}
	
	public static Vector toRadians(Vector vec)
	{
		Vector ret = new Vector(vec);
		
		for (int c = 0; c < ret.getSize(); c++)
		{
			ret.set(c, toRadians(ret.get(c)), false);
			
		}
		
		ret.onChanged();
		
		return ret;
	}
	
	public static int max(int... is)
	{
		int ret = Integer.MIN_VALUE;
		
		for (int i : is)
		{
			ret = Math.max(ret, i);
			
		}
		
		return ret;
	}
	
	public static int min(int... is)
	{
		int ret = Integer.MAX_VALUE;
		
		for (int i : is)
		{
			ret = Math.min(ret, i);
			
		}
		
		return ret;
	}
	
}