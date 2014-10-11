
package com.elusivehawk.engine.lwjgl;

import static com.elusivehawk.engine.input.EnumMouseClick.DOWN;
import static com.elusivehawk.engine.input.EnumMouseClick.DRAG;
import static com.elusivehawk.engine.input.EnumMouseClick.UP;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import com.elusivehawk.engine.CaelumEngine;
import com.elusivehawk.engine.CaelumException;
import com.elusivehawk.engine.input.EnumMouseClick;
import com.elusivehawk.engine.render.IDisplay;
import com.elusivehawk.util.EnumLogType;
import com.elusivehawk.util.Logger;
import com.elusivehawk.util.math.Vector;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class LWJGLMouse extends com.elusivehawk.engine.input.Mouse
{
	protected EnumMouseClick[]
			buttons = null,
			oldButtons = null;
	protected final Vector
			mousePos = new Vector(2),
			mousePosDelta = new Vector(2);
	
	protected float wheel = 0f;
	
	@Override
	public void close()
	{
		Mouse.destroy();
		
	}
	
	@Override
	public Vector getMousePos()
	{
		return this.mousePos;
	}
	
	@Override
	public Vector getMousePosDelta()
	{
		return this.mousePosDelta;
	}
	
	@Override
	public EnumMouseClick[] getClickStatus()
	{
		return this.buttons;
	}
	
	@Override
	public EnumMouseClick[] getOldClickStatus()
	{
		return this.oldButtons;
	}
	
	@Override
	public int getButtonCount()
	{
		return this.buttons.length;
	}
	
	@Override
	public float getWheelScroll()
	{
		return this.wheel;
	}
	
	@Override
	public void setGrabMouse(boolean grab)
	{
		Mouse.setGrabbed(grab);
		
	}
	
	@Override
	public boolean initiateInput()
	{
		super.initiateInput();
		
		try
		{
			Mouse.create();
			
		}
		catch (Exception e)
		{
			Logger.log().err(e);
			
			return false;
		}
		
		if (!Mouse.isCreated())
		{
			return false;
		}
		
		this.buttons = new EnumMouseClick[Mouse.getButtonCount()];
		this.oldButtons = new EnumMouseClick[Mouse.getButtonCount()];
		
		for (int b = 0; b < this.buttons.length; b++)
		{
			this.buttons[b] = EnumMouseClick.UP;
			this.oldButtons[b] = EnumMouseClick.UP;
			
		}
		
		return true;
	}
	
	@Override
	protected boolean poll()//TODO Buffer mouse movements, clicks, etc.
	{
		if (!Mouse.isCreated())
		{
			try
			{
				Mouse.create();
				
			}
			catch (LWJGLException e)
			{
				e.printStackTrace();
				
			}
			
			if (!Mouse.isCreated())
			{
				throw new CaelumException("Cannot poll mouse: It wasn't created!");
			}
			
			Logger.log().log(EnumLogType.WARN, "Mouse recreated; Let's not do anything stupid again...");
			
		}
		
		IDisplay display = CaelumEngine.display();
		boolean ret = false;
		int b;
		
		while (Mouse.next())
		{
			this.mousePos.set(((float)Mouse.getEventX() / (float)display.getWidth()), 1.0f - ((float)Mouse.getEventY() / (float)display.getHeight()));
			this.mousePosDelta.set(((float)Mouse.getEventDX() / (float)display.getWidth()), 1.0f - ((float)Mouse.getEventDY() / (float)display.getHeight()));
			this.wheel = (float)Mouse.getEventDWheel() / (float)display.getHeight();
			
			if (this.mousePos.isDirty() || this.mousePosDelta.isDirty())
			{
				ret = true;
				
			}
			
			b = Mouse.getEventButton();
			
			if (b == -1)
			{
				continue;
			}
			
			EnumMouseClick cur = this.buttons[b];
			
			if (Mouse.getEventButtonState())
			{
				switch (cur)
				{
					case DOWN:
						if (this.mousePos.isDirty())
						{
							this.oldButtons[b] = DOWN;
							this.buttons[b] = DRAG;
						}
						break;
					case UP:
						this.oldButtons[b] = UP;
						this.buttons[b] = DOWN;
						ret = true;
						break;
				}
				
			}
			else if (this.buttons[b].isDown())
			{
				this.oldButtons[b] = cur;
				this.buttons[b] = UP;
				
				ret = true;
				
			}
			
		}
		
		return ret;
	}
	
	@Override
	protected void postUpdate()
	{
		this.mousePos.setIsDirty(false);
		this.mousePosDelta.setIsDirty(false);
		
	}
	
}