package game.ai.enemy;

import game.Game;
import game.ai.Mind;
import game.entity.Enemy;
import game.entity.Player;
import game.util.Task;

public class Shooter implements Mind{
	
	private Enemy e;
	private boolean removedSelf;
	
	public Shooter(Enemy e)
	{
		this.e = e;
		removedSelf = false;
	}
	
	public void think()
	{
		if(e.isDead())
		{
			deadThoughts();
			return;
		}
		movementThought();
		attackingThought();
	}
	
	private void attackingThought()
	{
		Player p = Game.getCurrentGame().getPlayer();
		
		if (p.getBounds().intersects(e.getAttackBounds()))
		{
			e.attack();
		}
	}
	
	private void deadThoughts()
	{
		e.getVelocity().set(0);
		if(!removedSelf)
		{
			Game.getCurrentGame().addObject(new Task(200) {
				@Override
				public void run()
				{
					Game.getCurrentGame().getLevel().removeEnemy(e);
				}
			});
			removedSelf = true;
		}
	}
	
	private void movementThought() {
		Player p = Game.getCurrentGame().getPlayer();

		double x = p.getBounds().getCenterX();
		double y = p.getBounds().getCenterY();
		double xdist = x - e.getBounds().getCenterX();
		double ydist = y - e.getBounds().getCenterY();
		int vel = 2;
		if (xdist > 1000) {
			e.getVelocity().setX(vel);
		} else if (xdist < -1000) {
			e.getVelocity().setX(vel * -1);
		} else {
			e.getVelocity().setX(0);
		}

		if (ydist > 1000) {
			e.getVelocity().setY(vel);
		} else if (ydist < -1000) {
			e.getVelocity().setY(vel * -1);
		} else {
			e.getVelocity().setY(0);
		}
	}

}