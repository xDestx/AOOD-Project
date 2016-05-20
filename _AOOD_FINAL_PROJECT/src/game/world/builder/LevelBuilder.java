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
		cc.add(new Wall(1599,300, new Location(0,-300)));
		cc.add(new Wall(380,874, new Location(-380,-300)));
		cc.add(new Wall(1545,93, new Location(-380,574)));
		cc.add(new Wall(183,105, new Location(982,700)));
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
