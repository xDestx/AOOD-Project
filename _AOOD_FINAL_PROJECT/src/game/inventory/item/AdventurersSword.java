package game.inventory.item;

import game.Game;

public class AdventurersSword extends Item {

	public AdventurersSword() {
		super("Adventurer's Sword", "AdventureSword.png", "Taken right from the hands of your favorite shounen protagonist", 7, 35, (3.0/Game.TICK), Item.COMMON);
	}

}
