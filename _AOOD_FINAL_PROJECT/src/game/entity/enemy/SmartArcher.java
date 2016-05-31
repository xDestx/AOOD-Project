package game.entity.enemy;

import game.Game;
import game.entity.neutral.Projectile;
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
				/*//Game.getCurrentGame().getPlayer().wasHit(strength);
				Location l = Game.getCurrentGame().getPlayer().getCenterLocation();
				Location firingLocation = new Location(l);
				double div = (l.distance(getCenterLocation())/(Game.TICK*10));
				if (div > 3)
				{
					div = 3;
				}
				double div = 2;
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
				/*double xv = xp * 10;
				double yv = yp * 10;
				//Total V (Hypotenuse) = 10
				Vector v = new Vector(xv,yv);
				Game.getCurrentGame().getLevel().addProjectile(new Projectile(new Location(getCenterLocation()), v, this, strength));//Total speed of 10 p/t (pixels/tick)*/
				
				//shoots normally if player not moving;
				double hypo = 0;
				Location l = Game.getCurrentGame().getPlayer().getCenterLocation();
				double lx = l.getX()-getCenterLocation().getX();
				double ly = l.getY()-getCenterLocation().getY();
				hypo = Math.sqrt(Math.pow(lx, 2)+Math.pow(ly, 2));
				double xp = lx/hypo;
				double yp = ly/hypo;
				double xv = xp * 10;
				double yv = yp * 10;
				
				//finds predicted location of player
				Vector player = Game.getCurrentGame().getPlayer().getVelocity();
				double xd = l.getX() - getCenterLocation().getX();
				double yd = l.getY() - getCenterLocation().getY();
				double predictlx = (player.getX() * 30);
				double predictly = (player.getY() * 30);
				
				double finalHypo = Math.sqrt(Math.pow(lx, 2)+Math.pow(ly, 2));
				//double d = Math.acos(Math.abs(l.getX()-getLocation().getX())/hypo);
				double predictxp = predictlx/finalHypo;
				double predictyp = predictly/finalHypo;
				double predictxv = predictxp * 10;
				double predictyv = predictyp * 10;
				Vector v = new Vector(xv + predictxv,yv + predictyv);
				//Vector v = new Vector(xv, yv);
				launchProjectile(v,strength);
			}
			//addAnimation(new PlayerHitAnimation(this));
			cooldownTicks = 0;
		}
	}

}
