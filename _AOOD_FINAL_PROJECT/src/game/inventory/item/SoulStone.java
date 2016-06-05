package game.inventory.item;

import game.Game;
import game.entity.LivingEntity;

public class SoulStone extends Item {

	public SoulStone() {
		super(
				/*Name*/"Soul Stone", 
				/*Image*/"SoulStone.png", 
				/*Lore*/"A stone with an embedded soul, providing strength",
				/*Str*/1, 
				/*Hp*/10, 
				/*Rgn*/(2.0/(double)Game.TICK), 
				/*Rarity*/Item.COMMON);
	}
	
	


}
