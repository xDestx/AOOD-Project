package game.inventory.item;

import game.Game;

public class ElvenBow extends Item {

	public ElvenBow() {
		super("Elven Bow", "ElvenBow.png", "Made in china", 17, 137, (12.0/(double)Game.TICK), Item.UNCOMMON);
	}

}
