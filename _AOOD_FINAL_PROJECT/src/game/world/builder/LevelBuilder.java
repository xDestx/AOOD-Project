package game.world.builder;

import java.util.ArrayList;

import game.Game;
import game.entity.enemy.EnemyType;
import game.entity.enemy.SmartArcher;
import game.entity.neutral.ItemEntity;
import game.inventory.item.SoulStone;
import game.world.Collidable;
import game.world.EnemySpawn;
import game.world.Level;
import game.world.Location;
import game.world.Wall;

public class LevelBuilder {

	private static boolean init = false;
	
	private static ArrayList<Level>levels;
	
	
	
	public static void init()
	{
		init = true;
		levels = new ArrayList<Level>();
		//Build all levels
		createLevelOne();
	}
	
	private static void createLevelOne()
	{

		ArrayList<Collidable> cc = new ArrayList<Collidable>();
		cc.add(new Wall(1600,300, new Location(0,-300)));
		cc.add(new Wall(800,800, new Location(-800,-300)));
		cc.add(new Wall(1600,300, new Location(-800,500)));
		cc.add(new Wall(900,600, new Location(-90,800)));
		cc.add(new Wall(650,1020, new Location(1600,-300)));
		cc.add(new Wall(1900,400, new Location(-90,1400)));
		cc.add(new Wall(470,360, new Location(1800,1400)));
		cc.add(new Wall(800,660, new Location(2250,720)));
		cc.add(new Wall(790,420, new Location(2250,1375)));
		cc.add(new Wall(790,770, new Location(2250,-50)));
		Level level = new Level(0, cc);
		
		level.addEnemy(new SmartArcher());

		//level.addEnemy(new Stick(new Location(1900,1200), 100, 10));
		//level.addEnemy(new Stick(new Location(1800,1300), 100, 10));
		level.addCollectible(new ItemEntity(new Location(1800,1300), new SoulStone()));
		EnemyType[] types = {EnemyType.SMART_ARCHER,EnemyType.ARCHER,EnemyType.STICK};
		level.addWorldObject(new EnemySpawn(new Location(1500,1100), 3*Game.TICK, types , 400,  1));

		levels.add(level);
	}
	
	public static Level getLevel(int i)
	{
		if(!init)
			init();
		for (Level l : levels)
		{
			if(l.getId() == i)
				return l;
		}
		return null;
	}

}
