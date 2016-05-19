package game.ai.enemy;

import game.Game;
import game.ai.Mind;
import game.entity.Enemy;
import game.entity.Player;

public class Zombie implements Mind {

	private Enemy e;
	
	public Zombie(Enemy e) {
		this.e = e;
	}

	@Override
	public void think() {
		//Zombie just goes straight for player
		
		
		//Find direction
		Player p = Game.getCurrentGame().getPlayer();
		
		double x = p.getBounds().getCenterX();
		double y = p.getBounds().getCenterY();
	//	double dist = Math.sqrt((Math.pow(x-e.getLocation().getX(), 2) + Math.pow(y - e.getLocation().getY(), 2)));
		double xdist = x - e.getBounds().getCenterX();
		double ydist = y - e.getBounds().getCenterY();
		int vel = 2;
		if(xdist > 0)
		{
			e.getVelocity().setX(vel);
		} else if (xdist < 0)
		{
			e.getVelocity().setX(vel * -1);
		} else
		{
			e.getVelocity().setX(0);
		}
		
		if(ydist > 0)
		{
			e.getVelocity().setY(vel);
		} else if (ydist < 0)
		{
			e.getVelocity().setY(vel * -1);
		} else
		{
			e.getVelocity().setY(0);
		}
		//double degree = Math.asin((y-e.getLocation().getY())/dist);
		
	}

}
