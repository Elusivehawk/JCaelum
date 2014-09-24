
package com.elusivehawk.engine.render.opengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.elusivehawk.engine.assets.Asset;
import com.elusivehawk.engine.assets.IAssetReceiver;
import com.elusivehawk.engine.render.RenderContext;
import com.elusivehawk.engine.render.RenderHelper;
import com.elusivehawk.engine.render.Shader;
import com.elusivehawk.engine.render.Shaders;
import com.elusivehawk.util.ArrayHelper;
import com.elusivehawk.util.IDirty;
import com.elusivehawk.util.IPopulator;
import com.google.common.collect.Lists;

/**
 * 
 * A class to help work with OpenGL's program system.
 * 
 * @author Elusivehawk
 */
public final class GLProgram implements IGLBindable, IAssetReceiver, IDirty
{
	private final Shaders shaders = new Shaders();
	private final HashMap<VertexBuffer, List<Integer>> vbos = new HashMap<VertexBuffer, List<Integer>>();
	
	private int id = 0, vba = 0;
	private boolean bound = false, relink = true;
	
	public GLProgram(){}
	
	public GLProgram(IPopulator<GLProgram> pop)
	{
		pop.populate(this);
		
	}
	
	public GLProgram(Shader[] sh)
	{
		if (!ArrayHelper.isNullOrEmpty(sh))
		{
			for (Shader s : sh)
			{
				attachShader(s);
				
			}
			
		}
		
	}
	
	@Override
	public boolean isDirty()
	{
		return this.shaders.isDirty();
	}
	
	@Override
	public void setIsDirty(boolean b)
	{
		this.shaders.setIsDirty(b);
		
	}
	
	@Override
	public synchronized void onAssetLoaded(Asset a)
	{
		this.shaders.onAssetLoaded(a);
		
	}
	
	@Override
	public void delete(RenderContext rcon)
	{
		if (this.bound)
		{
			this.unbind(rcon);
			
		}
		
		IGL2 gl2 = rcon.getGL2();
		
		rcon.getGL3().glDeleteVertexArrays(this.vba);
		
		this.shaders.deleteShaders(rcon, this);
		
		gl2.glDeleteProgram(this);
		
	}
	
	@Override
	public boolean bind(RenderContext rcon)
	{
		if (this.id == 0)
		{
			this.id = rcon.getGL2().glCreateProgram();
			
			if (!this.relink(rcon))
			{
				return false;
			}
			
			rcon.registerCleanable(this);
			
			RenderHelper.checkForGLError(rcon);
			
		}
		
		if (this.vba == 0)
		{
			this.vba = rcon.getGL3().glGenVertexArrays();
			
		}
		
		if (this.relink || this.shaders.isDirty())
		{
			if (!this.relink(rcon))
			{
				return false;
			}
			
		}
		
		if (!this.bind0(rcon))
		{
			this.unbind(rcon);
			
			return false;
		}
		
		return true;
	}
	
	private boolean bind0(RenderContext rcon)
	{
		if (this.bound)
		{
			return true;
		}
		
		int bp = rcon.getGL1().glGetInteger(GLConst.GL_CURRENT_PROGRAM);
		
		if (bp == this.id)
		{
			return true;
		}
		
		if (bp != 0)
		{
			return false;
		}
		
		rcon.getGL2().glUseProgram(this);
		
		rcon.getGL3().glBindVertexArray(this.vba);
		
		if (!this.vbos.isEmpty())
		{
			for (Entry<VertexBuffer, List<Integer>> entry : this.vbos.entrySet())
			{
				entry.getKey().bind(rcon);
				
				if (!entry.getValue().isEmpty())
				{
					for (int attrib : entry.getValue())
					{
						rcon.getGL2().glEnableVertexAttribArray(attrib);
						
					}
					
				}
				
			}
			
		}
		
		this.bound = true;
		
		return true;
	}
	
	private boolean relink(RenderContext rcon)
	{
		if (!this.shaders.attachShaders(rcon, this))
		{
			return false;
		}
		
		IGL2 gl2 = rcon.getGL2();
		
		gl2.glLinkProgram(this);
		gl2.glValidateProgram(this);
		
		this.relink = false;
		this.shaders.setIsDirty(false);
		
		return true;
	}
	
	@Override
	public void unbind(RenderContext rcon)
	{
		if (!this.bound)
		{
			return;
		}
		
		if (!this.vbos.isEmpty())
		{
			for (Entry<VertexBuffer, List<Integer>> entry : this.vbos.entrySet())
			{
				if (entry.getValue() != null)
				{
					for (int a : entry.getValue())
					{
						rcon.getGL2().glDisableVertexAttribArray(a);
						
					}
					
				}
				
				entry.getKey().unbind(rcon);
				
			}
			
		}
		
		rcon.getGL3().glBindVertexArray(0);
		
		rcon.getGL2().glUseProgram(0);
		
		this.bound = false;
		
	}
	
	@Override
	public int hashCode()
	{
		return this.getId();
	}
	
	public void attachVBO(VertexBuffer vbo, int... attribs)
	{
		List<Integer> valid = Lists.newArrayList();
		
		for (int a : attribs)
		{
			boolean found = false;
			
			for (List<Integer> l : this.vbos.values())
			{
				if (l.contains(a))
				{
					found = true;
					
				}
				
			}
			
			if (!found)
			{
				valid.add(a);
				
			}
			
		}
		
		this.vbos.put(vbo, valid);
		
	}
	
	public synchronized boolean attachShader(Shader sh)
	{
		if (this.shaders.addShader(sh))
		{
			this.relink = true;
			return true;
		}
		
		return false;
	}
	
	public void attachUniform(RenderContext rcon, String name, FloatBuffer info, EnumUniformType type)
	{
		if (!this.bound)
		{
			return;
		}
		
		int loc = rcon.getGL2().glGetUniformLocation(this.id, name);
		
		if (loc != 0)
		{
			type.loadUniform(rcon, loc, info);
			
		}
		
	}
	
	public void attachUniform(RenderContext rcon, String name, IntBuffer info, EnumUniformType type)
	{
		if (!this.bound)
		{
			return;
		}
		
		int loc = rcon.getGL2().glGetUniformLocation(this.id, name);
		
		if (loc != 0)
		{
			type.loadUniform(rcon, loc, info);
			
		}
		
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int shaderCount()
	{
		return this.shaders.getShaderCount();
	}
	
	private static interface IUniformType
	{
		public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf);
		
		public void loadUniform(RenderContext rcon, int loc, IntBuffer buf);
		
	}
	
	public static enum EnumUniformType implements IUniformType
	{
		ONE
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniform1f(loc, buf.get());
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf)
			{
				rcon.getGL2().glUniform1i(loc, buf.get());
				
			}
			
		},
		TWO
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniform2f(loc, buf.get(), buf.get());
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf)
			{
				rcon.getGL2().glUniform2i(loc, buf.get(), buf.get());
				
			}
			
		},
		THREE
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniform3f(loc, buf.get(), buf.get(), buf.get());
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf)
			{
				rcon.getGL2().glUniform3i(loc, buf.get(), buf.get(), buf.get());
				
			}
			
		},
		FOUR
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniform4f(loc, buf.get(), buf.get(), buf.get(), buf.get());
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf)
			{
				rcon.getGL2().glUniform4i(loc, buf.get(), buf.get(), buf.get(), buf.get());
				
			}
			
		},
		M_TWO
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniformMatrix2fv(loc, 1, false, buf);
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf){}
			
		},
		M_THREE
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniformMatrix3fv(loc, 1, false, buf);
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf){}
			
		},
		M_FOUR
		{
			@Override
			public void loadUniform(RenderContext rcon, int loc, FloatBuffer buf)
			{
				rcon.getGL2().glUniformMatrix4fv(loc, 1, false, buf);
				
			}
			
			@Override
			public void loadUniform(RenderContext rcon, int loc, IntBuffer buf){}
			
		};
		
	}
	
}
