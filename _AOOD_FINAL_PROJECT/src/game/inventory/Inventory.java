package game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.GFrame;
import game.Renderable;
import game.world.Location;

public class Inventory {
	
	private boolean isOpen;
	private Item[] items;

	public Inventory() {
		isOpen = false;
		items = new Item[20];
		for(int i = 0; i < 20; i++)
		{
			items[i] = null;
		}
	}
	
	public boolean isOpen()
	{
		return isOpen;
	}
	/*
	 * Fixed position
	 */
	public void render(Graphics g)
	{
		Color last = g.getColor();
		g.setColor(new Color(0,0,0,127));
		g.fillRect(10, 10, GFrame.WIDTH-20, GFrame.HEIGHT-20);
		g.setColor(Color.gray);
		g.drawRect(20,20, GFrame.WIDTH-40, GFrame.HEIGHT-40);
		//Vertical lines
		for (int i = 0; i < 5; i++)
		{
			double xo = 20 + ((i+1)*((GFrame.WIDTH-40)/5.0));
			g.drawLine((int)xo, 20, (int)xo, GFrame.HEIGHT-20);
		}
		//Horizontal lines
		for(int i = 0; i < 4; i++)
		{
			double yo = 20 + ((i+1)*((GFrame.HEIGHT-40)/4.0));
			g.drawLine(20, (int)yo, GFrame.WIDTH-20, (int)yo);
		}
		//items
		for (int i = 0; i < 20; i++)
		{
			//Do things here!
		}
	}

}
