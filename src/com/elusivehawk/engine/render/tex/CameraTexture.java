
package com.elusivehawk.engine.render.tex;

import com.elusivehawk.engine.render.ICamera;
import com.elusivehawk.engine.render.IDisplay;
import com.elusivehawk.engine.render.RenderContext;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class CameraTexture extends RenderableTexture
{
	private final ICamera cam;
	
	@SuppressWarnings("unqualified-field-access")
	public CameraTexture(ICamera camera)
	{
		cam = camera;
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public CameraTexture(ICamera camera, boolean depth)
	{
		super(depth);
		
		cam = camera;
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public CameraTexture(ICamera camera, IDisplay display)
	{
		super(display);
		
		cam = camera;
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public CameraTexture(ICamera camera, IDisplay display, boolean depth)
	{
		super(display, depth);
		
		cam = camera;
		
	}
	
	@SuppressWarnings("unqualified-field-access")
	public CameraTexture(ICamera camera, int width, int height, boolean depth)
	{
		super(width, height, depth);
		
		cam = camera;
		
	}
	
	@Override
	public void preRender(RenderContext rcon, double delta)
	{
		this.cam.preRender(rcon, delta);
		
	}
	
	@Override
	public void renderTexture(RenderContext rcon)
	{
		rcon.renderGame(this.cam);
		
	}
	
}
