package game.world.builder;

import game.world.Level;

public class LevelGenerator {

	
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
