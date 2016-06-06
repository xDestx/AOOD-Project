package game.inventory.item;

import game.Game;

public class EnchantedStick extends Item {

	public EnchantedStick() {
		super(
				"Enchanted Stick",
				"EnchantedStick.png",
				"Alakazam-o",
				14,
				189,
				(13.0/(double)Game.TICK),
				Item.UNCOMMON);
	}

}
