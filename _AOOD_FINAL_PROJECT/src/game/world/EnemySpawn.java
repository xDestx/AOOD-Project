package game.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.GameObject;
import game.Renderable;
import game.entity.Player;
import game.entity.enemy.Enemy;
import game.entity.enemy.EnemyType;
import game.graphic.CircleAnimation;

public class EnemySpawn extends WorldObject implements Spawner, Renderable, Collidable {

	
	private EnemyType e;
	private int level;
	private int interval;
	private int currentTick,radius;
	
	public static int LENGTH = 50;
	
	public EnemySpawn(Location l, int interval, EnemyType e, int radius, int level)
	{
		bounds = new Rectangle((int)l.getX(),(int)l.getY(),EnemySpawn.LENGTH,EnemySpawn.LENGTH);
		this.l = l;
		this.level = level;
		this.radius = radius;
		this.e = e;
		this.interval = interval;
		currentTick = 0;
		this.addAnimation(new CircleAnimation(-1, this, radius, level));
	}
	
	public EnemyType getEnemyType()
	{
		return this.e;
	}
	
	public void setEnemyType(EnemyType e)
	{
		this.e = e;
	}
	
	@Override
	public void render(Graphics g, int xo, int yo) {
		super.render(g, xo, yo);
		Color lastColor = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(xo,yo, (int)getBounds().getWidth(), (int)getBounds().getHeight());
		g.setColor(lastColor);
	}




	@Override
	public void spawn() {
		Enemy enemy = Enemy.create(e);
		if (enemy == null)
		{
			return;
		}
		if(Game.getCurrentGame().getPlayer().getLocation().distance(getLocation()) > radius)
		{
			//System.out.println("Whoops!");
			return;
		}
		double per = (level / (double)Player.MAX_LEVEL);
		//System.out.println(per * 100);
		enemy.setMaxHealth((int)(per * (Enemy.MAX_HP)));
		enemy.setHealth(enemy.getMaxHealth());
		enemy.setStrength((int)(per * (Enemy.MAX_STRENGTH)));
		int signx = (int)(Math.random()*2) == 0 ? -1:1;
		int signy = (int)(Math.random()*2) == 0 ? -1:1;
		int xoff = (int)(Math.random()*100) * signx;
		int yoff = (int)(Math.random()*100) * signy;
		xoff+=100 * signx;
		yoff+=100 * signy;
		enemy.setLocation(new Location(getLocation().getX()+xoff, getLocation().getY()+yoff));
		//System.out.println(enemy.getHealth());
		Game.getCurrentGame().getLevel().addEnemy(enemy);
	}

	@Override
	public void tick() {
		super.tick();
		currentTick++;
		if(currentTick>=interval)
		{
			if((int)(Math.random()*10) > 4)
			{
				spawn();
				currentTick = 0;
			}
		}
	}


	@Override
	public boolean collide(Rectangle r) {
		return r.intersects(getBounds());
	}

	
	
}
