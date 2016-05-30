package game.entity.enemy;

import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.ai.Mind;
import game.ai.enemy.Shooter;
import game.entity.LivingEntity;
import game.entity.neutral.Projectile;
import game.graphic.PlayerHitAnimation;
import game.world.Location;
import game.world.Vector;

public class Archer extends Enemy {
	
	protected Mind m;
	protected int cooldownTicks;
	//for attacking
	protected int cooldownTicksDefault;
	
	public Archer(Location l, int health, int strength)
	{
		super(l, health, strength, 10);
		m = new Shooter(this);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
		setAttackBounds(new Rectangle((int)(getLocation().getX() + (LivingEntity.WIDTH * 2.5)), ((int)(getLocation().getY() - (LivingEntity.HEIGHT * 2.5))), (int)((LivingEntity.WIDTH * 5) + (LivingEntity.WIDTH)), (int)(LivingEntity.HEIGHT * 5) + (LivingEntity.HEIGHT)));
		cooldownTicksDefault = 100;
		cooldownTicks = cooldownTicksDefault;
	}
	
	public Archer()
	{
		super(new Location(0,0), 100, 10, 10);
		m = new Shooter(this);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
		setAttackBounds(new Rectangle((int)(getLocation().getX() - (LivingEntity.WIDTH * 2.5)), ((int)(getLocation().getY() - (LivingEntity.HEIGHT * 2.5))), (int)((LivingEntity.WIDTH * 5) + (LivingEntity.WIDTH)), (int)(LivingEntity.HEIGHT * 5) + (LivingEntity.HEIGHT)));
		cooldownTicksDefault = 100;
		cooldownTicks = cooldownTicksDefault;
	}

	
	
	@Override
	public void attack() {
		if(cooldownTicks >= cooldownTicksDefault)
		{
			if(Game.getCurrentGame().getPlayer().getBounds().intersects(getAttackBounds()))
			{
				double hypo = 0;
				//Game.getCurrentGame().getPlayer().wasHit(strength);
				Location l = Game.getCurrentGame().getPlayer().getCenterLocation();
				double lx = l.getX()-getCenterLocation().getX();
				double ly = l.getY()-getCenterLocation().getY();
				hypo = Math.sqrt(Math.pow(lx, 2)+Math.pow(ly, 2));
			//	double d = Math.acos(Math.abs(l.getX()-getLocation().getX())/hypo);
				double xp = lx/hypo;
				double yp = ly/hypo;
				/*
				 * Proportional
				 */
				double xv = xp * 10;
				double yv = yp * 10;
				//Total V (Hypotenuse) = 10
				Vector v = new Vector(xv,yv);
				launchProjectile(v,strength);
				//Game.getCurrentGame().getLevel().addProjectile(new Projectile(new Location(getCenterLocation()), v, this, strength));//Total speed of 10 p/t (pixels/tick)
			}
			//addAnimation(new PlayerHitAnimation(this));
			cooldownTicks = 0;
		}
	}
	
	
	public void tick()
	{
		super.tick();
		setAttackBoundsL((int)(getLocation().getX() - (LivingEntity.WIDTH * 2.5)), ((int)(getLocation().getY() - (LivingEntity.HEIGHT * 2.5))));
		m.think();
		cooldownTicks++;
	}

}
