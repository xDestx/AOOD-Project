package game.world.dungeon;

import game.world.EnclosedArea;
import game.world.Location;

public class Dungeon extends EnclosedArea {

	public Dungeon(Location l, int width, int height) {
		super(l, width, height);
		//Dungeons extend down and right 
	}

}
