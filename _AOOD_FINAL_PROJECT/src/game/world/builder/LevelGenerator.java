package game.world.builder;

import java.util.ArrayList;

import game.world.Level;
import game.world.Location;
import game.world.dungeon.Dungeon;

public class LevelGenerator {

	public static void main(String[] args)
	{
		for (int i = 0; i < 10; i++)
		{
			int seed = LevelGenerator.generateSeed();
			while(seed == 0)
				seed = LevelGenerator.generateSeed();
			double num = (seed%(seed/256));
			num/=(seed*.000001);
			int MIN = (int) (Math.pow(1800, 2));
			int MAX = (int) (Math.pow(1800*4, 2));
			num = (MAX-MIN) * num;
			num+=MIN;
			System.out.println("Area: " + (int)(num/10));
			num = Math.sqrt(num);
			System.out.println(num + "   " + seed);
		}
	}
	
	public static int generateSeed()
	{
		int x = 0;
		String s = "";
		for (int i = 0; i < 9; i++)
		{
			s+=(int)(Math.random() * 9)+1;
		}
		x = Integer.parseInt(s);
		return x;
	}
	
	public static Level generateLevel(int seed)
	{
		/*
		 * To do: Everything
		 */
		Level finalLevel = new Level(seed);
		LevelGenerator.generateDungeons(finalLevel, seed);
		LevelGenerator.generateNature(finalLevel, seed);
		LevelGenerator.generatePassiveMobs(finalLevel, seed);
		LevelGenerator.correctErrors(finalLevel, seed);
		
		return finalLevel;
	}
	
	
	/*
	 * Dungeons are generated first, attempted even spacing
	 * Next, Nature (trees, water, whatever) is generated next, also evenly spaced between dungeons
	 * After, passive mobs spawn points are placed around based on nature surrounding
	 * Then, aggressive mobs spawn points are placed in specific natural settings
	 * Finally, any errors that can be found are corrected and the level is completely built
	 */
	
	
	private static void generateDungeons(Level l, int seed)
	{
		int numOfDungeons = (seed%(int)(seed/(10000000)))/2;
		if (numOfDungeons < 5)
		{
			numOfDungeons = 5;
		} else if (numOfDungeons > 25)
		{
			numOfDungeons = 25;
		}
		final int MIN_SIZE = 1800;
		final int MAX_SIZE = 1800*4;
		int remainingArea = (int) (Level.HEIGHT * Level.WIDTH * 0.5);
		//Half of the remaining area is dedicated to everything else
		//50% of the total area is dedicated to dungeons, but dungeons may not use all of this area
		ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
		
		for (int i = 0; i < numOfDungeons; i++)
		{
			Dungeon d = generateDungeon(seed,remainingArea,MAX_SIZE,MIN_SIZE);
			if (d != null)
			{
				dungeons.add(d);
				remainingArea-=d.getArea();
			}
			if (remainingArea < (Math.pow(MIN_SIZE, 2)))
				break;
		}
		
	}
	
	
	private static Dungeon generateDungeon(int seed, int remainingArea, int MAX_SIZE, int MIN_SIZE)
	{
		int x_location;
		int y_location;
		double length = (seed%(seed/256));
		length/=(seed*.000001);
		length = (remainingArea - MIN_SIZE) * length;
		length+=MIN_SIZE;
		length = Math.sqrt(length);
//		x__location = 
		//Dungeon d = new Dungeon(new Location(x,y), (int)length,(int)length);		
		
		
		return null;
	}

	
	
	
	
	
	
	private static void generateNature(Level l, int seed)
	{
				
	}
	
	private static void generatePassiveMobs(Level l, int seed)
	{
		
	}
	
	private static void correctErrors(Level l, int seed)
	{
		
	}
	
}
