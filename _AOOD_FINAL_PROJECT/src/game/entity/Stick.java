package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.graphic.Animation;
import game.graphic.PlayerHitAnimation;
import game.util.Task;
import game.world.Location;

public class Stick extends Enemy{

	private int cooldownTicks;
	//for attacking
	private int cooldownTicksDefault;
	
	public Stick(Location l, int health, int strength){
		super(l,  health,  strength);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
		cooldownTicksDefault = 100;
		cooldownTicks = cooldownTicksDefault;
	}
	
	public void addAnimation(final Animation a)
	{
		if(!toAnimationsUsed)
			this.toAnimations.add(a);
		else {
			Game.getCurrentGame().addObject(new Task(1)
					{
				@Override
				public void run()
				{
					addAnimation(a);
				}
					});
		}
	}

	@Override
	public void attack() {
		if(cooldownTicks >= cooldownTicksDefault)
		{
			if(Game.getCurrentGame().getPlayer().getBounds().intersects(getAttackBounds()))
			{
				Game.getCurrentGame().getPlayer().wasHit(strength);
			}
			addAnimation(new PlayerHitAnimation(this));
			cooldownTicks = 0;
		}
	}
	

	
	public void tick()
	{
		super.tick();
		cooldownTicks++;
	}
	



}
