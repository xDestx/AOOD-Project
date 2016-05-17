package game.world;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Collidable {

	boolean collide(Rectangle r);
	void render(Graphics g, int x, int y);
	Rectangle getBounds();
	Location getLocation();
	
}
