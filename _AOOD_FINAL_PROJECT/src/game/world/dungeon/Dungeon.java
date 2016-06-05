package game.world.dungeon;

import java.util.ArrayList;

import game.Game;
import game.entity.LivingEntity;
import game.entity.enemy.Enemy;
import game.entity.enemy.EnemyType;
import game.world.Collidable;
import game.world.EnclosedArea;
import game.world.Level;
import game.world.Location;
import game.world.WorldObject;
import game.world.room.EnemyRoom;
import game.world.room.Room;

public class Dungeon extends EnclosedArea {
	
	private ArrayList<Room> rooms;
	private Room mainRoom;
	
	/*
	 * What is a dungeon?
	 * 
	 * A dungeon is an enclosed area with many rooms, including _Loot_ rooms, _Enemy_ rooms, _Empty_ rooms, and _Boss_ rooms.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	public Dungeon(Location l, int width, int height) {
		super(l, width, height);
		rooms = new ArrayList<Room>();
		this.mainRoom = new Room(l,width,height);
		this.rooms.add(mainRoom);
		//Main room is the enclosing room
	}
	
	public void addRoom(Room r)
	{
		this.rooms.add(r);
	}
	
	public void addToLevel(Level l)
	{
		for (Room r : rooms)
		{
			for (Collidable c : r.getCollidables())
			{
				l.addCollidable(c);
			}
			for(WorldObject o : r.getWorldObjects())
			{
				l.addWorldObject(o);
			}
			for (LivingEntity e : r.getEntities())
			{
				if((e instanceof Enemy))
					l.addEnemy((Enemy)e);
			}
		}
	}

	
	public static Dungeon createDungeon()
	{
		//Top left
		Location dungeonLocation = new Location(0,0);
		int width = 6000;
		int height = 6000;
		Dungeon d = new Dungeon(dungeonLocation,width,height);
		EnemyRoom er = new EnemyRoom(new EnemyType[] {EnemyType.SMART_ARCHER, EnemyType.STICK, EnemyType.ARCHER}, new Location(dungeonLocation), 2500, 1500, Game.TICK * 2, 2);
		er.setLocation(new Location(er.getLocation().getX()+1000,er.getLocation().getY()+1000));
		d.addRoom(er);
		return d;
	}





}
