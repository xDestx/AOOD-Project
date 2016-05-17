package game;

import java.awt.Rectangle;
import java.io.Serializable;

public abstract class GameObject implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5426752996704196830L;
	protected Location l;
	protected Rectangle bounds;
	
	public void setBounds(Rectangle r)
	{
		this.bounds = r;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public Location getLocation()
	{
		return this.l;
	}
	
	public abstract void tick();

}