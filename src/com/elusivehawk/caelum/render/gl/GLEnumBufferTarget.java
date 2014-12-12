
package com.elusivehawk.caelum.render.gl;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public enum GLEnumBufferTarget
{
	GL_ARRAY_BUFFER(GLConst.GL_ARRAY_BUFFER, GLConst.GL_ARRAY_BUFFER_BINDING),
	GL_ATOMIC_COUNTER_BUFFER(GLConst.GL_ATOMIC_COUNTER_BUFFER, GLConst.GL_ATOMIC_COUNTER_BUFFER_BINDING),
	GL_COPY_READ_BUFFER(GLConst.GL_COPY_READ_BUFFER, GLConst.GL_COPY_READ_BUFFER_BINDING),
	GL_COPY_WRITE_BUFFER(GLConst.GL_COPY_WRITE_BUFFER, GLConst.GL_COPY_WRITE_BUFFER_BINDING),
	GL_DISPATCH_INDIRECT_BUFFER(GLConst.GL_DISPATCH_INDIRECT_BUFFER, GLConst.GL_DISPATCH_INDIRECT_BUFFER_BINDING),
	GL_DRAW_INDIRECT_BUFFER(GLConst.GL_DRAW_INDIRECT_BUFFER, GLConst.GL_DRAW_INDIRECT_BUFFER_BINDING),
	GL_ELEMENT_ARRAY_BUFFER(GLConst.GL_ELEMENT_ARRAY_BUFFER, GLConst.GL_ELEMENT_ARRAY_BUFFER_BINDING),
	GL_PIXEL_PACK_BUFFER(GLConst.GL_PIXEL_PACK_BUFFER, GLConst.GL_PIXEL_PACK_BUFFER_BINDING),
	GL_PIXEL_UNPACK_BUFFER(GLConst.GL_PIXEL_UNPACK_BUFFER, GLConst.GL_PIXEL_UNPACK_BUFFER_BINDING),
	GL_QUERY_BUFFER(GLConst.GL_QUERY_BUFFER, GLConst.GL_QUERY_BUFFER_BINDING),
	GL_SHADER_STORAGE_BUFFER(GLConst.GL_SHADER_STORAGE_BUFFER, GLConst.GL_SHADER_STORAGE_BUFFER_BINDING),
	GL_TEXTURE_BUFFER(GLConst.GL_TEXTURE_BUFFER, GLConst.GL_TEXTURE_BUFFER_DATA_STORE_BINDING),
	GL_TRANSFORM_FEEDBACK_BUFFER(GLConst.GL_TRANSFORM_FEEDBACK_BUFFER, GLConst.GL_TRANSFORM_FEEDBACK_BUFFER_BINDING),
	GL_UNIFORM_BUFFER(GLConst.GL_UNIFORM_BUFFER, GLConst.GL_UNIFORM_BUFFER_BINDING);
	
	private final int glId, bindId;
	
	@SuppressWarnings("unqualified-field-access")
	GLEnumBufferTarget(int gl, int bind)
	{
		glId = gl;
		bindId = bind;
		
	}
	
	public int getGLId()
	{
		return this.glId;
	}
	
	public int getBindID()
	{
		return this.bindId;
	}
	
}
