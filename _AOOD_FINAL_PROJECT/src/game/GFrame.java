package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

public class GFrame extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3393797115664849839L;
	public static final int WIDTH = 1280, HEIGHT = 720;
    private Game g;
	private Canvas canvas;
	private BufferedImage im;
	
    public GFrame(Game g)
    {
    	this.setSize(GFrame.WIDTH, GFrame.HEIGHT);
    	this.setResizable(false);
    	this.g = g;
    	canvas = new Canvas();
    	im = new BufferedImage(GFrame.WIDTH,GFrame.HEIGHT,BufferedImage.TYPE_INT_RGB);
    	this.add(canvas);
    }
    
    
    public void render(Ticker t)
    {
    	BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null)
		{
			canvas.createBufferStrategy(2);
			return;
		}
		Graphics gr = bs.getDrawGraphics();
		gr.drawImage(im,0,0,null);
		gr.setColor(Color.white);
		gr.fillRect(0, 0, GFrame.WIDTH, GFrame.HEIGHT);
		
    	t.render(gr, g.getCamera());
    	bs.show();
    	bs.getDrawGraphics().dispose();
    	
    }
    
    public Canvas getCanvas()
    {
    	return canvas;
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponents(g);
    }
    
    
    
    
    
}
