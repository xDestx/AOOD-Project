package game.world.builder;

import java.util.ArrayList;

import game.entity.Collectible;
import game.entity.Enemy;
import game.entity.HealthPack;
import game.entity.Stick;
import game.world.Collidable;
import game.world.Level;
import game.world.Location;
import game.world.Wall;

public class LevelBuilder {

	private static boolean init = false;
	
	private static ArrayList<Level>levels;
	
	
	public static void main(String[] args)
	{
		LevelBuilderGUI a = new LevelBuilderGUI();
	}
	
	
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
		cc.add(new Wall(1599,232, new Location(0,26)));
		cc.add(new Wall(185,619, new Location(1407,257)));
		cc.add(new Wall(1073,75, new Location(0,560)));
		cc.add(new Wall(1408,46, new Location(0,840)));
		cc.add(new Wall(21,645, new Location(0,248)));
		Level level = new Level( 0, cc);
		//Enemy e = new Stick(new Location(100,300), 10, 10);
		//level.addEnemy(e);
		Collectible hp = new HealthPack(new Location(100,300), 50);
		level.addCollectible(hp);
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
