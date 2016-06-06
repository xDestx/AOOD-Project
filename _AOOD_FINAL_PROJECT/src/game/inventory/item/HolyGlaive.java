package game.inventory.item;

import game.Game;

public class HolyGlaive extends Item {

	public HolyGlaive() {
		
		super(
				"Holy Glaive",
				"HolyGlaive.png",
				"Blessed by a wandering saint and bestowed as a gift to a vanquisher of evil",
				50,
				390,
				(25.0/Game.TICK),
				Item.LEGENDARY
			);
		
	}

}
