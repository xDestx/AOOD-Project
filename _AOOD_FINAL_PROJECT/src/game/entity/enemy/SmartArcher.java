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
