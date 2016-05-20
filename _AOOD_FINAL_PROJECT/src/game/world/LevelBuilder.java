package game.world;

import java.util.ArrayList;

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
		cc.add(new Wall(2500,500,new Location(0,-500)));
		cc.add(new Wall(900,1400, new Location(-900,-500)));
		cc.add(new Wall(1600,10,new Location(0,890)));
		cc.add(new Wall(900,900, new Location(800,890)));
		Level levelOne = new Level(0,cc);
		levels.add(levelOne);
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
