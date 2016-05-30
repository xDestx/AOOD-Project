package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.entity.Player;
import game.world.Location;
import game.world.WorldObject;

public class Camera extends WorldObject
{
	
	private Game g;
	private boolean locked;
    
    public Camera(Location l, Game g)
    {
        setLocation(l);
        locked = true;
        this.g = g;
        setBounds(new Rectangle((int)l.getX(),(int)l.getY(),GFrame.WIDTH , GFrame.HEIGHT));
    } 
    
    public Rectangle getViewBounds()
    {
        return this.bounds;
    }
    
    @Deprecated
    public void render(Graphics g, int x, int y)
    {
    }

    private void move()
    {
		getLocation().setX(g.getPlayer().getLocation().getX()+Player.WIDTH/2-GFrame.WIDTH/2);
		getLocation().setY(g.getPlayer().getLocation().getY()+Player.HEIGHT/2-GFrame.HEIGHT/2);
		getViewBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
    }
    
    public void setLocked(boolean b)
    {
    	this.locked = b;
    }
    
    public boolean getLocked()
    {
    	return this.locked;
    }
    
	@Override
	public void tick() {
		if(locked)
			move();
	}
	
	
	public static int[] calculateOffset(Location l)
	{
		int[] xy = new int[2];
		xy[0] = ((int) (l.getX() - Game.getCurrentGame().getCamera().getLocation().getX()));
		xy[1] = (int) (l.getY() - Game.getCurrentGame().getCamera().getLocation().getY());
		return xy;
	}
	
	public static int[] createOffset(Location l)
	{
		int[] xy = new int[2];
		xy[0] = ((int) (l.getX() + Game.getCurrentGame().getCamera().getLocation().getX()));
		xy[1] = (int) (l.getY() + Game.getCurrentGame().getCamera().getLocation().getY());
		return xy;
	}
	
}
