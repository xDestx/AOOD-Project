package game.world.room;

import java.util.ArrayList;

import game.entity.enemy.Chest;
import game.entity.enemy.EnemyType;
import game.inventory.Inventory;
import game.inventory.item.Item;
import game.world.EnemySpawn;
import game.world.Location;

public class EnemyRoom extends Room {

	public EnemyRoom(EnemyType[] types, Location l, int width, int height, int spawnInt, int level) {
		super(l,width,height);
		
		Location esl = new Location(0,0);
		esl.setX(esl.getX()+(width/2));
		esl.setY(esl.getY()+(height/2));
		
		
		int radius = (width > height) ? (height/2) : (width/2);
		EnemySpawn es = new EnemySpawn(esl, spawnInt, types, radius, level);
		this.addWorldObject(es);
		
		
		ArrayList<Item> coolInv = Inventory.getLootItems(20);
		Location cl = new Location(esl);
		cl.setX(esl.getX()+(width/4));;
		Chest c = new Chest(new Location(cl), coolInv);
		
		
		//c.getLocation().setX(c.getLocation().getX()+(width/4));
		//c.getBounds().setLocation(c.getLocation().toPoint());
		
		
		this.addEntity(c);
	}

}
