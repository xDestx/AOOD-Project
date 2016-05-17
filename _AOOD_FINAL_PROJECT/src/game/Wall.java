package game;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Wall extends WorldObject implements Collidable
{

    public Wall(int width, int height, Location l)
    {
    	setLocation(l);
    	setBounds(new Rectangle((int)getLocation().getX(), (int)getLocation().getY(), width, height));
    }
    
    public void render(Graphics g, int x, int y)
    {
    	g.fillRect(x, y, (int)bounds.getWidth(),(int)bounds.getHeight());
    }

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}