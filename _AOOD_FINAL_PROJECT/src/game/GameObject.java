package game;

import java.io.Serializable;

public abstract class GameObject implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5426752996704196830L;
	protected Location l;
	
	public Location getLocation()
	{
		return this.l;
	}
	
	public abstract void tick();

}