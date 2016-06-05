package game.inventory.item;

import game.Game;

public class ShadowScythe extends Item {

	public ShadowScythe() {
		super("Shadow Scythe", "ShadowScythe.png","Uses life force to sustain its power", 500, -750, (-1.0/(double)Game.TICK), Item.LEGENDARY);
	}

}
