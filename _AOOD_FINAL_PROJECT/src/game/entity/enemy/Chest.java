package game.entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.inventory.Inventory;
import game.inventory.item.Item;
import game.world.Location;

public class Chest extends Enemy {

	public Chest(Location l, ArrayList<Item> items) {
		super(l, 100, 0, 0);
		inventory = new Inventory(this,items);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void movement()
	{
		
	}
	
	@Override
	protected void drawBody(Graphics g, int xo, int yo)
	{
		if(isDead())
			g.setColor(Color.RED);
		else
			g.setColor(Color.YELLOW);
		g.fillRect(xo, yo, Enemy.WIDTH, Enemy.HEIGHT);
	}
	
	
	
}
