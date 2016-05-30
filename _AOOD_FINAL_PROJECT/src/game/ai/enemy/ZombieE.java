package game.ai.enemy;

import game.Game;
import game.ai.Mind;
import game.entity.Player;
import game.entity.enemy.Enemy;
import game.util.Task;

public class ZombieE implements Mind {

	private Enemy e;

	private boolean removedSelf;
	
	
	//Zombie with eyes
	public ZombieE(Enemy e) {
		this.e = e;
		removedSelf = false;
	}

	@Override
	public void think() {
		// Zombie just goes straight for player
		//Tries to go around walls
		if(e.isDead())
		{
			//deadThoughts();
			return;
		}
		movementThought();
		attackingThought();

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
			if(e.getLastDamagingEntity().equals(Game.getCurrentGame().getPlayer()))
				((Player)e.getLastDamagingEntity()).addXP(e.getKillXP());
			removedSelf = true;
		}
	}
	
	private void attackingThought()
	{
		Player p = Game.getCurrentGame().getPlayer();
		
		if (p.getBounds().intersects(e.getAttackBounds()))
		{
			e.attack();
		}
	}

	private void movementThought() {
		Player p = Game.getCurrentGame().getPlayer();

		double x = p.getBounds().getCenterX();
		double y = p.getBounds().getCenterY();
		double xdist = x - e.getBounds().getCenterX();
		double ydist = y - e.getBounds().getCenterY();
		int vel = 2;
		if (xdist > 0) {
			e.getVelocity().setX(vel);
		} else if (xdist < 0) {
			e.getVelocity().setX(vel * -1);
		} else {
			e.getVelocity().setX(0);
		}

		if (ydist > 0) {
			e.getVelocity().setY(vel);
		} else if (ydist < 0) {
			e.getVelocity().setY(vel * -1);
		} else {
			e.getVelocity().setY(0);
		}
	}

}