package game.inventory.item;

import game.Game;
import game.entity.LivingEntity;

public class SoulStone extends Item {

	public SoulStone() {
		super("Soul Stone", "SoulStone.png", "A stone with an embedded soul, providing strength", 1, 10, (2.0/(double)Game.TICK), Item.RARE);
	}
	
	


}
