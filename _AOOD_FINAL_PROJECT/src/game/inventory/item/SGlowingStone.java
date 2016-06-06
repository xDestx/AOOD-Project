package game.inventory.item;

import game.Game;

public class SGlowingStone extends Item {

	public SGlowingStone() {
		super(
				"Strange Glowing Stone",
				"SGlowingStone.png",
				"\"I found it in the woods\"",
				6,
				90,
				(6.0/Game.TICK),
				Item.COMMON);
	}

}
