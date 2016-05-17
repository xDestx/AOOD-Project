package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Camera extends WorldObject
{
    
    private Rectangle bounds;

    public Camera(Location l)
    {
        setLocation(l);
        bounds = new Rectangle((int)l.getX(),(int)l.getY(),GFrame.WIDTH , GFrame.HEIGHT);
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
}
