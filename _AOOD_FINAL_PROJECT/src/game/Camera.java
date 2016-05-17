package game;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.world.Location;
import game.world.WorldObject;

public class Camera extends WorldObject
{
	
	private Game g;
    
    public Camera(Location l, Game g)
    {
        setLocation(l);
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
        return;
    }

    private void move()
    {
		getLocation().set(g.getPlayer().getLocation());
		getViewBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
    }
    
	@Override
	public void tick() {
		move();
	}
	
	
}
