package game.world.builder;

import java.util.ArrayList;

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
