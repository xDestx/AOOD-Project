package game;

import java.awt.Graphics;

public interface Renderable {

	public void render(Graphics g, int xo, int yo);
	public Location getLocation();
	
}
