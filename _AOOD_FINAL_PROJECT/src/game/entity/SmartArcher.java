package game.entity;

import game.Game;
import game.world.Location;
import game.world.Vector;

public class SmartArcher extends Archer {

	@Override
	public void attack()
	{
		if(cooldownTicks >= cooldownTicksDefault)
		{
			if(Game.getCurrentGame().getPlayer().getBounds().intersects(getAttackBounds()))
			{
				//Game.getCurrentGame().getPlayer().wasHit(strength);
				Location l = Game.getCurrentGame().getPlayer().getCenterLocation();
				Location firingLocation = new Location(l);
				double div = (l.distance(getCenterLocation())/(Game.TICK*10));
				if (div > 3)
				{
					div = 3;
				}
				System.out.println(div + " !");
				firingLocation.setX(Game.getCurrentGame().getPlayer().getVelocity().getX()*(Game.TICK * div));
				firingLocation.setY(Game.getCurrentGame().getPlayer().getVelocity().getY()*(Game.TICK * div));
				double lx = firingLocation.getX()-getCenterLocation().getX();
				double ly = firingLocation.getY()-getCenterLocation().getY();
				double hypo = firingLocation.distance(getCenterLocation());
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
				Game.getCurrentGame().getLevel().addProjectile(new Projectile(new Location(getCenterLocation()), v, this, strength));//Total speed of 10 p/t (pixels/tick)
			}
			//addAnimation(new PlayerHitAnimation(this));
			cooldownTicks = 0;
		}
	}

}
