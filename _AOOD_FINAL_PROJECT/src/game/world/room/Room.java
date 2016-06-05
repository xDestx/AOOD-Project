package game.world.room;

import java.util.ArrayList;

import game.entity.LivingEntity;
import game.world.Collidable;
import game.world.EnclosedArea;
import game.world.Location;
import game.world.Wall;
import game.world.WorldObject;

public class Room extends EnclosedArea {

	protected ArrayList<Collidable> walls;
	protected ArrayList<WorldObject> worldObjects;
	protected ArrayList<LivingEntity> entities;
	
	public Room(Location l, int width, int height) {
		super(l, width, height);
		worldObjects = new ArrayList<WorldObject>();
		walls = new ArrayList<Collidable>();
		entities = new ArrayList<LivingEntity>();
		/*LEFT WALL TO ENTERANCE*/walls.add(new Wall(100, (height/2)-(200/2), new Location(l)));
		/*LEFT WALL FROM ENTERANCE*/walls.add(new Wall(100, (height/2)-(200/2), new Location(l.getX(),l.getY()+(height/2)+(200/2))));
		/*TOP WALL*/walls.add(new Wall(width,100,new Location(l)));
		/*BOTTOM WALL*/walls.add(new Wall(width,100,new Location(l.getX(), l.getY()+height-100)));
		/*RIGHT WALL*/walls.add(new Wall(100, height, new Location(l.getX()+width-100,l.getY())));
	}
	
	
	public void addEntity(LivingEntity c)
	{
		c.getLocation().setX(c.getLocation().getX()+getLocation().getX());
		c.getLocation().setY(c.getLocation().getY()+getLocation().getY());
		c.getBounds().setLocation((int)(c.getLocation().getX()),(int)(c.getLocation().getY()));
		entities.add(c);
	}
	
	public ArrayList<LivingEntity> getEntities()
	{
		return this.entities;
	}

	
	public void addCollidiable(Collidable c)
	{
		c.getLocation().setX(c.getLocation().getX()+getLocation().getX());
		c.getLocation().setY(c.getLocation().getY()+getLocation().getY());
		c.getBounds().setLocation((int)(c.getLocation().getX()),(int)(c.getLocation().getY()));
		walls.add(c);
	}
	
	public void addWorldObject(WorldObject c)
	{
		c.getLocation().setX(c.getLocation().getX()+getLocation().getX());
		c.getLocation().setY(c.getLocation().getY()+getLocation().getY());
		c.getBounds().setLocation((int)(c.getLocation().getX()),(int)(c.getLocation().getY()));
		worldObjects.add(c);
	}
	
	public ArrayList<WorldObject> getWorldObjects()
	{
		return this.worldObjects;
	}
	
	public ArrayList<Collidable> getCollidables()
	{
		return walls;
	}
	
	private void relocate(Collidable c, double x, double y)
	{
		c.getLocation().setX(c.getLocation().getX()+x);
		c.getLocation().setY(c.getLocation().getY()+y);
		c.getBounds().setLocation((int)(c.getLocation().getX()),(int)(c.getLocation().getY()));
	}
	
	private void relocate(WorldObject c, double x, double y)
	{
		c.getLocation().setX(c.getLocation().getX()+x);
		c.getLocation().setY(c.getLocation().getY()+y);
		c.getBounds().setLocation((int)(c.getLocation().getX()),(int)(c.getLocation().getY()));
	}
	
	private void relocate(LivingEntity c, double x, double y)
	{
		c.moveX(x);
		c.moveY(y);
	}
	
	public void setLocation(Location newLocation)
	{
		for (Collidable c : walls)
		{
			relocate(c,-1*getLocation().getX(),-1*getLocation().getY());
		}
		for (WorldObject c : worldObjects)
		{
			relocate(c,-1*getLocation().getX(),-1*getLocation().getY());
		}
		for (LivingEntity c : entities)
		{
			relocate(c,-1*getLocation().getX(),-1*getLocation().getY());
		}
		this.l = newLocation;
		for (Collidable c : walls)
		{
			relocate(c,getLocation().getX(),getLocation().getY());
		}
		for (WorldObject c : worldObjects)
		{
			relocate(c,getLocation().getX(),getLocation().getY());
		}
		for (LivingEntity c : entities)
		{
			relocate(c,getLocation().getX(),getLocation().getY());
		}
	}
	

}
