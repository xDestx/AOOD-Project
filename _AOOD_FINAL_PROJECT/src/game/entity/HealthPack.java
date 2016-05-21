package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.world.Location;

public class HealthPack extends Collectible{
	protected int healAmt;
	protected static final int WIDTH = 50, HEIGHT = 50;
	
	public HealthPack(Location l, int healAmt)
	{
		super(l);
		this.healAmt = healAmt;
	}

	public void effect()
	{
		Game.getCurrentGame().getPlayer().setHealth(Game.getCurrentGame().getPlayer().getHealth() + healAmt);
	}
	
	public void render(Graphics g, int xo, int yo)
	{
		Color block = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		g.setColor(block);
	}
	
	public void tick(){
		//nothing yet
	}
}
