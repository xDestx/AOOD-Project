package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.graphic.PlayerHitAnimation;
import game.world.Location;

public class Player extends LivingEntity {
	
	
	private boolean vertMod,horizMod;
	private int xp;
	//Check if horizontal / vertical keys are being held.
	public static int getMaxLevel()
	{
		return 20;
	}
	
	public Player(Location l, int health) {
		super(l);
		this.health = health;
		this.maxHealth = health;
		xp = 0;
	}
	
	public int getXP()
	{
		return this.xp;
	}
	
	public void setXP(int xp)
	{
		this.xp=xp;
	}
	
	public void addXP(int xp)
	{
		this.xp+=xp;
	}
	
	public int getLevel()
	{
		for (int i = Player.getMaxLevel(); i >= 1; i--)
		{
			if(getXP() / (i*100 + ((i-1) * 10)) >= 1)
			{
				return i+1;
			}
		}
		return 1;
	}
	
	public void setVert(boolean b)
	{
		this.vertMod = b;
	}
	
	public void setHoriz(boolean b)
	{
		this.horizMod = b;
	}
	
	public boolean getVert()
	{
		 return this.vertMod;
	}
	
	public boolean getHoriz()
	{
		return this.horizMod;
	}


	@Override
	public void attack()
	{
		for (Enemy e : Game.getCurrentGame().getEnemies()){
			if (this.getAttackBounds().intersects(e.getBounds())){
				e.wasHit(10);
			}
		}
		addAnimation(new PlayerHitAnimation(this));
	}
	
	@Override
	public void tick() {
		super.tick();
		movement();
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		renderAnimations(g,xo,yo);
		drawHealthBar(g,xo,yo);
		g.setColor(c);
	}





}