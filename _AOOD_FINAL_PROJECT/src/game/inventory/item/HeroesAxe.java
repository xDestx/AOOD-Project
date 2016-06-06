package game.inventory.item;

import game.Game;

public class HeroesAxe extends Item {

	public HeroesAxe() {
		super("Hero's Axe", "HeroAxe.png", "Axe go down, head fly up", 32, 225, (12.0/(double)Game.TICK),Item.RARE);
	}

}
