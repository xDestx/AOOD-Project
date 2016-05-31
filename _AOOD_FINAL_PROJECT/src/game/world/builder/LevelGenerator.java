package game.world.builder;

import java.util.ArrayList;
import java.util.Random;

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
		Random r = new Random(seed);
		LevelGenerator.generateDungeons(finalLevel, r);
		LevelGenerator.generateNature(finalLevel, r);
		LevelGenerator.generatePassiveMobs(finalLevel, r);
		LevelGenerator.correctErrors(finalLevel, r);
		
		return finalLevel;
	}
	
	
	/*
	 * Dungeons are generated first, attempted even spacing
	 * Next, Nature (trees, water, whatever) is generated next, also evenly spaced between dungeons
	 * After, passive mobs spawn points are placed around based on nature surrounding
	 * Then, aggressive mobs spawn points are placed in specific natural settings
	 * Finally, any errors that can be found are corrected and the level is completely built
	 */
	
	
	private static void generateDungeons(Level l, Random r)
	{
		int numOfDungeons = r.nextInt(20) + 5;
		final int MIN_SIZE = 1800;
		final int MAX_SIZE = 1800*4;
		int remainingArea = (int) (Level.HEIGHT * Level.WIDTH * 0.5);
		//Half of the remaining area is dedicated to everything else
		//50% of the total area is dedicated to dungeons, but dungeons may not use all of this area
		ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
		
		for (int i = 0; i < numOfDungeons; i++)
		{
			Dungeon d = generateDungeon(r,remainingArea,MAX_SIZE,MIN_SIZE);
			if (d != null)
			{
				dungeons.add(d);
				remainingArea-=d.getArea();
			}
			if (remainingArea < (Math.pow(MIN_SIZE, 2)))
				break;
		}
		
	}
	
	
	private static Dungeon generateDungeon(Random r, int remainingArea, int MAX_SIZE, int MIN_SIZE)
	{
		int x_location;
		int y_location;
		double length;
		int c = 0;
		do
		{
			length = r.nextInt(MAX_SIZE-MIN_SIZE) + MIN_SIZE;
			c++;
			if(c == 50)
			{
				return null;
			}
		} while(length > remainingArea);
		length = Math.sqrt(length);
		x_location = r.nextInt(Level.WIDTH-10)+10;
		y_location = r.nextInt((int)(Level.HEIGHT-10-Math.sqrt(MAX_SIZE)))+10;
		Dungeon d = new Dungeon(new Location(x_location,y_location), (int)length, (int)length);
		
		
		return d;
	}

	
	
	
	
	
	
	private static void generateNature(Level l, Random r)
	{
				
	}
	
	private static void generatePassiveMobs(Level l, Random r)
	{
		
	}
	
	private static void correctErrors(Level l, Random r)
	{
		
	}
	
}
