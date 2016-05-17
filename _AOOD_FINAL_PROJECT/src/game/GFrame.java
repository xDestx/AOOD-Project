package game;

import javax.swing.JFrame;

public class GFrame extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3393797115664849839L;
	public static final int WIDTH = 1280, HEIGHT = 720;
    private Game g;
	
    public GFrame(Game g)
    {
    	this.setSize(GFrame.WIDTH, GFrame.HEIGHT);
    	this.setResizable(false);
    	this.g = g;
    }
    
    
}
