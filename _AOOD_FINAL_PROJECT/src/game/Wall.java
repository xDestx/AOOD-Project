package game;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Wall extends WorldObject implements Collidable
{

    private Rectangle bounds;
    
    public Wall(int width, int height)
    {
        bounds = new Rectangle((int)getLocation().getX(), (int)getLocation().getY(), width, height);
    }
    
    public void render(Graphics g, int x, int y)
    {
        
    }

}