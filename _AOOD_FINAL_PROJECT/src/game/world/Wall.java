package game.world;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.awt.Graphics;

public class Wall extends WorldObject implements Collidable
{

	private BufferedImage wallTexture;
	
    public Wall(int width, int height, Location l)
    {
    	setLocation(l);
    	setBounds(new Rectangle((int)getLocation().getX(), (int)getLocation().getY(), width, height));
    	try {
    		wallTexture = ImageIO.read(getClass().getResourceAsStream("/images/wall_1.png"));
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void render(Graphics g, int x, int y)
    {
    	if(wallTexture == null)
    		g.fillRect(x, y, (int)bounds.getWidth(),(int)bounds.getHeight());
    	else
    	{
    		//g.drawImage(wallTexture,x, y, (int)bounds.getWidth(),(int)bounds.getHeight(), null);
    		int height = ((bounds.getHeight() < 256) ? (int)bounds.getHeight():256);
    		int width = ((bounds.getWidth() < 256) ? (int)bounds.getWidth():256);
    		int repeatX = (int)bounds.getWidth()/width;
    		int remainderX = (int)bounds.getWidth()%width;
    		int repeatY = (int)bounds.getHeight()/height;
    		int remainderY = (int)bounds.getHeight()%height;
    		height+=(remainderY/repeatY);
    		width+=(remainderX/repeatX);
    		for(int i = 0; i < repeatX; i++)
    		{
    			for (int k = 0; k < repeatY; k++)
    			{
    				g.drawImage(wallTexture, x+(width * i), y+(height*k), width,height,null);
    			}
    		}
    	}
    }

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean collide(Rectangle bounds)
	{
		return (bounds.intersects(getBounds()));
	}

}