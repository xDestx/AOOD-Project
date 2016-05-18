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
    	g.setColor(Color.red);
    	g.drawRect(x,y,GFrame.WIDTH,GFrame.HEIGHT);
    	g.setColor(Color.black);
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
	
	
}
