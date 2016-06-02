package game.world.dungeon;

import java.util.ArrayList;

import game.world.Collidable;
import game.world.EnclosedArea;
import game.world.Level;
import game.world.Location;
import game.world.Wall;

public class Dungeon extends EnclosedArea {

	private ArrayList<Collidable> walls;
	
	public Dungeon(Location l, int width, int height) {
		super(l, width, height);
		walls = new ArrayList<Collidable>();
	}
	
	public void addColldiable(Collidable c)
	{
		c.getLocation().setX(c.getLocation().getX()+getLocation().getX());
		c.getLocation().setY(c.getLocation().getY()+getLocation().getY());
		c.getBounds().setLocation((int)(c.getLocation().getX()+getLocation().getX()),(int)(c.getLocation().getY()+getLocation().getY()));
		walls.add(c);
	}
	
	public void addToLevel(Level l)
	{
		for (Collidable c : walls)
		{
			l.addCollidable(c);
		}
	}

	
	public static Dungeon createDungeon()
	{
		//Top left
		Location dungeonLocation = new Location(0,0);
		int width = 6000;
		int height = 6000;
		Dungeon d = new Dungeon(dungeonLocation,width,height);
		//When adding to a dungeon, you are adding relative to its location
		/*LEFT WALL TO ENTERANCE*/d.addColldiable(new Wall(100, (height/2)-(200/2), new Location(dungeonLocation)));
		/*LEFT WALL FROM ENTERANCE*/d.addColldiable(new Wall(100, (height/2)-(200/2), new Location(dungeonLocation.getX(),dungeonLocation.getY()+(height/2)+(200/2))));
		/*TOP WALL*/d.addColldiable(new Wall(width,100,new Location(dungeonLocation)));
		/*BOTTOM WALL*///d.addColldiable(new Wall(width,100,));
		
		
		return d;
	}





}
