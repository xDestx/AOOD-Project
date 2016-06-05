package game.inventory.item;

import game.Game;

public class RustedShears extends Item {

	public RustedShears() {
		super("Rusted Shears", "RustedShears.png", "They can cut just about anything, just don't try to get a haircut...", 42, -100, (0.5/(double)Game.TICK),Item.UNCOMMON);
	}

}
