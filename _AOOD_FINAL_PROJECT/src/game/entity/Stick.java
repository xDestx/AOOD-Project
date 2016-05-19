package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.world.Location;

public class Stick extends Enemy{
	
	private int strength;
	
	public Stick(Location l, int health, int strength){
		super(l,  health,  strength);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	



}
