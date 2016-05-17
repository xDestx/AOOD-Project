package game.world;

import java.awt.Color;
import java.awt.Graphics;

import game.Renderable;

public class GroundPattern {

	private int[] xpoints;
	private int[] ypoints;
	
	public GroundPattern()
	{
		xpoints = new int[10];
		ypoints = new int[10];
		for (int i = 0; i < 10; i++)
		{
			xpoints[i] = (int)(Math.random() * 100);
			ypoints[i] = (int)(Math.random() * 100);
		}
	}
	
	
	public void render(Graphics g, int x, int y)
	{
		Color last = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 100, 100);
		g.setColor(new Color(0,0,0));
		for(int i = 0; i < 10; i++)
		{
			g.fillRect(xpoints[i], ypoints[i], 1, 1);
		}
		g.setColor(last);
	}


}
