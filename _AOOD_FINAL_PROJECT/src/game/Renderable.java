package game;

import java.awt.Graphics;

import game.world.Location;

public interface Renderable {

	public void render(Graphics g, int xo, int yo);
	public Location getLocation();
	
}
