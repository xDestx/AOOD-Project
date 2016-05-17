package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Camera extends WorldObject
{
    
    public Camera(Location l)
    {
        setLocation(l);
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

	@Override
	public void tick() {
		getLocation().setX(getLocation().getX() + 1);
		getViewBounds().setLocation((int)getLocation().getX(), (int)getLocation().getY());
	}
	
	
}
