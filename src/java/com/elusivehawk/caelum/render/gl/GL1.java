
package com.elusivehawk.caelum.render.gl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import com.elusivehawk.caelum.render.RenderHelper;
import com.elusivehawk.caelum.render.tex.Color;
import com.elusivehawk.caelum.render.tex.ColorFilter;
import com.elusivehawk.caelum.render.tex.ITexture;
import com.elusivehawk.caelum.window.Window;
import com.elusivehawk.util.storage.BufferHelper;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public final class GL1
{
	private GL1(){}
	
	public static void glActiveTexture(int texture) throws GLException
	{
		GL13.glActiveTexture(texture);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBindBuffer(GLBuffer vbo)
	{
		glBindBuffer(vbo.getTarget(), vbo.getId());
		
	}
	
	public static void glBindBuffer(GLEnumBufferTarget target, int buffer) throws GLException
	{
		glBindBuffer(target.gl, buffer);
		
	}
	
	public static void glBindBuffer(int target, int buffer) throws GLException
	{
		GL15.glBindBuffer(target, buffer);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBindTexture(ITexture texture)
	{
		glBindTexture(texture.getType(), texture);
		
	}
	
	public static void glBindTexture(GLEnumTexture target, ITexture texture)
	{
		glBindTexture(target.gl, texture == null ? 0 : texture.getId());
		
	}
	
	public static void glBindTexture(GLEnumTexture target, int texture) throws GLException
	{
		glBindTexture(target.gl, texture);
		
	}
	
	public static void glBindTexture(int target, ITexture texture)
	{
		glBindTexture(target, texture == null ? 0 : texture.getId());
		
	}
	
	public static void glBindTexture(int target, int texture) throws GLException
	{
		GL11.glBindTexture(target, texture);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBlendFunc(int sfactor, int dfactor) throws GLException
	{
		GL11.glBlendFunc(sfactor, dfactor);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferData(GLEnumBufferTarget target, ByteBuffer data, GLEnumDataUsage usage) throws GLException
	{
		glBufferData(target.gl, data, usage.gl);
		
	}
	
	public static void glBufferData(int target, ByteBuffer data, int usage) throws GLException
	{
		GL15.glBufferData(target, data, usage);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferData(GLEnumBufferTarget target, DoubleBuffer data, GLEnumDataUsage usage) throws GLException
	{
		glBufferData(target.gl, data, usage.gl);
		
	}
	
	public static void glBufferData(int target, DoubleBuffer data, int usage) throws GLException
	{
		GL15.glBufferData(target, data, usage);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferData(GLEnumBufferTarget target, FloatBuffer data, GLEnumDataUsage usage) throws GLException
	{
		glBufferData(target.gl, data, usage.gl);
		
	}
	
	public static void glBufferData(int target, FloatBuffer data, int usage) throws GLException
	{
		GL15.glBufferData(target, data, usage);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferData(GLEnumBufferTarget target, IntBuffer data, GLEnumDataUsage usage) throws GLException
	{
		glBufferData(target.gl, data, usage.gl);
		
	}
	
	public static void glBufferData(int target, IntBuffer data, int usage) throws GLException
	{
		GL15.glBufferData(target, data, usage);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferData(GLEnumBufferTarget target, ShortBuffer data, GLEnumDataUsage usage) throws GLException
	{
		glBufferData(target.gl, data, usage.gl);
		
	}
	
	public static void glBufferData(int target, ShortBuffer data, int usage) throws GLException
	{
		GL15.glBufferData(target, data, usage);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferSubData(GLEnumBufferTarget target, long offset, ByteBuffer data) throws GLException
	{
		glBufferSubData(target.gl, offset, data);
		
	}
	
	public static void glBufferSubData(int target, long offset, ByteBuffer data) throws GLException
	{
		GL15.glBufferSubData(target, offset, data);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferSubData(GLEnumBufferTarget target, long offset, DoubleBuffer data) throws GLException
	{
		glBufferSubData(target.gl, offset, data);
		
	}
	
	public static void glBufferSubData(int target, long offset, DoubleBuffer data) throws GLException
	{
		GL15.glBufferSubData(target, offset, data);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferSubData(GLEnumBufferTarget target, long offset, FloatBuffer data) throws GLException
	{
		glBufferSubData(target.gl, offset, data);
		
	}
	
	public static void glBufferSubData(int target, long offset, FloatBuffer data) throws GLException
	{
		GL15.glBufferSubData(target, offset, data);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferSubData(GLEnumBufferTarget target, long offset, IntBuffer data) throws GLException
	{
		glBufferSubData(target.gl, offset, data);
		
	}
	
	public static void glBufferSubData(int target, long offset, IntBuffer data) throws GLException
	{
		GL15.glBufferSubData(target, offset, data);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glBufferSubData(GLEnumBufferTarget target, long offset, ShortBuffer data) throws GLException
	{
		glBufferSubData(target.gl, offset, data);
		
	}
	
	public static void glBufferSubData(int target, long offset, ShortBuffer data) throws GLException
	{
		GL15.glBufferSubData(target, offset, data);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glClear(int mask) throws GLException
	{
		GL11.glClear(mask);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glClearColor(Color col)
	{
		glClearColor(col.getColorf(ColorFilter.RED), col.getColorf(ColorFilter.GREEN), col.getColorf(ColorFilter.BLUE), col.getColorf(ColorFilter.ALPHA));
		
	}
	
	public static void glClearColor(float r, float g, float b, float a)
	{
		GL11.glClearColor(r, g, b, a);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) throws GLException
	{
		GL11.glCopyTexImage1D(target, level, internalFormat, x, y, width, border);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) throws GLException
	{
		GL11.glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) throws GLException
	{
		GL11.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) throws GLException
	{
		GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) throws GLException
	{
		GL12.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glCullFace(int mode) throws GLException
	{
		GL11.glCullFace(mode);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDeleteBuffers(GLBuffer... buffers)
	{
		IntBuffer bufs = BufferHelper.createIntBuffer(buffers.length);
		
		for (GLBuffer vb : buffers)
		{
			bufs.put(vb.getId());
			
		}
		
		glDeleteBuffers(bufs);
		
	}
	
	public static void glDeleteBuffers(int... buffer) throws GLException
	{
		glDeleteBuffers(BufferHelper.makeIntBuffer(buffer));
		
	}
	
	public static void glDeleteBuffers(IntBuffer buffers) throws GLException
	{
		GL15.glDeleteBuffers(buffers);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDeleteTextures(int... textures) throws GLException
	{
		glDeleteTextures(BufferHelper.makeIntBuffer(textures));
		
	}
	
	public static void glDeleteTextures(IntBuffer textures) throws GLException
	{
		GL11.glDeleteTextures(textures);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDepthFunc(int func) throws GLException
	{
		GL11.glDepthFunc(func);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDepthMask(boolean flag) throws GLException
	{
		GL11.glDepthMask(flag);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDepthRange(float zNear, float zFar) throws GLException
	{
		GL11.glDepthRange(zNear, zFar);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDisable(int cap) throws GLException
	{
		GL11.glDisable(cap);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDrawArrays(GLEnumDrawType type, int first, int count) throws GLException
	{
		glDrawArrays(type.getGLId(), first, count);
		
	}
	
	public static void glDrawArrays(int mode, int first, int count) throws GLException
	{
		GL11.glDrawArrays(mode, first, count);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glDrawElements(GLEnumDrawType mode, int count, int type, int offset) throws GLException
	{
		glDrawElements(mode.getGLId(), count, type, offset);
		
	}
	
	public static void glDrawElements(int mode, int count, int type, int offset) throws GLException
	{
		GL11.glDrawElements(mode, count, type, offset);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glEnable(int cap) throws GLException
	{
		GL11.glEnable(cap);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glFinish()
	{
		GL11.glFinish();
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glFlush()
	{
		GL11.glFlush();
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glFrontFace(int mode) throws GLException
	{
		GL11.glFrontFace(mode);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static int glGenBuffer() throws GLException
	{
		int ret = GL15.glGenBuffers();
		
		RenderHelper.checkForGLError();
		
		return ret;
	}
	
	public static IntBuffer glGenBuffers(int count) throws GLException
	{
		IntBuffer ret = BufferHelper.createIntBuffer(count);
		
		GL15.glGenBuffers(ret);
		
		RenderHelper.checkForGLError();
		
		return ret;
	}
	
	public static int glGenTexture() throws GLException
	{
		int ret = GL11.glGenTextures();
		
		RenderHelper.checkForGLError();
		
		return ret;
	}
	
	public static IntBuffer glGenTextures(int count) throws GLException
	{
		IntBuffer ret = BufferHelper.createIntBuffer(count);
		
		GL11.glGenTextures(ret);
		
		RenderHelper.checkForGLError();
		
		return ret;
	}
	
	public static int glGetError()
	{
		return GL11.glGetError();
	}
	
	public static GLEnumError glGetErrorEnum()
	{
		return GLEnumError.get(glGetError());
	}
	
	public static int glGetInteger(int pname) throws GLException
	{
		int ret = GL11.glGetInteger(pname);
		
		RenderHelper.checkForGLError();
		
		return ret;
	}
	
	public static void glGetInteger(int pname, IntBuffer params) throws GLException
	{
		GL11.glGetInteger(pname, params);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static String glGetString(int name) throws GLException
	{
		String ret = GL11.glGetString(name);
		
		RenderHelper.checkForGLError();
		
		return ret;
	}
	
	public static void glHint(int target, int mode) throws GLException
	{
		GL11.glHint(target, mode);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static boolean glIsBuffer(int buffer)
	{
		return GL15.glIsBuffer(buffer);
	}
	
	public static boolean glIsEnabled(int cap)
	{
		return GL11.glIsEnabled(cap);
	}
	
	public static boolean glIsTexture(int texture)
	{
		return GL11.glIsTexture(texture);
	}
	
	public static void glLogicOp(int op) throws GLException
	{
		GL11.glLogicOp(op);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glPixelStoref(int pname, int param) throws GLException
	{
		GL11.glPixelStoref(pname, param);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glPixelStorei(int pname, int param) throws GLException
	{
		GL11.glPixelStorei(pname, param);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glPointSize(float size) throws GLException
	{
		GL11.glPointSize(size);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) throws GLException
	{
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glScissor(int x, int y, int width, int height) throws GLException
	{
		GL11.glScissor(x, y, width, height);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glStencilFunc(int func, int ref, int mask) throws GLException
	{
		GL11.glStencilFunc(func, ref, mask);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glStencilMask(int mask) throws GLException
	{
		GL11.glStencilMask(mask);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glStencilOp(int fail, int zfail, int zpass) throws GLException
	{
		GL11.glStencilOp(fail, zfail, zpass);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glTexImage2D(GLEnumTexture target, int level, int internalFormat, int width, int height, int border, int format, int type, ByteBuffer pixels) throws GLException
	{
		glTexImage2D(target.gl, level, internalFormat, width, height, border, format, type, pixels);
		
	}
	
	public static void glTexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, ByteBuffer pixels) throws GLException
	{
		GL11.glTexImage2D(target, level, internalFormat, width, height, border, format, type, pixels);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glTexImage3D(GLEnumTexture target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) throws GLException
	{
		glTexImage3D(target.gl, level, internalFormat, width, height, depth, border, format, type, pixels);
		
	}
	
	public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) throws GLException
	{
		GL12.glTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, pixels);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glTexParameterf(GLEnumTexture target, int pname, float param) throws GLException
	{
		glTexParameterf(target.gl, pname, param);
		
	}
	
	public static void glTexParameterf(int target, int pname, float param) throws GLException
	{
		GL11.glTexParameterf(target, pname, param);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glTexParameteri(GLEnumTexture target, int pname, int param) throws GLException
	{
		glTexParameteri(target.gl, pname, param);
		
	}
	
	public static void glTexParameteri(int target, int pname, int param) throws GLException
	{
		GL11.glTexParameteri(target, pname, param);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glTexSubImage2D(GLEnumTexture target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) throws GLException
	{
		glTexSubImage2D(target.gl, level, xoffset, yoffset, width, height, format, type, pixels);
		
	}
	
	public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) throws GLException
	{
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
		
		RenderHelper.checkForGLError();
		
	}
	
	public static void glViewport(Window window) throws GLException
	{
		glViewport(0, 0, window.getWidth(), window.getHeight());
		
	}
	
	public static void glViewport(int x, int y, int width, int height) throws GLException
	{
		assert width > x : "Width is greather than X! This is a bug!";
		assert height > y : "Height is greather than Y! This is a bug!";
		
		GL11.glViewport(x, y, width, height);
		
		RenderHelper.checkForGLError();
		
	}
	
}
