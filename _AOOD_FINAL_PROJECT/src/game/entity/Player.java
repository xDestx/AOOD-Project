package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.graphic.PlayerHitAnimation;
import game.util.Task;
import game.world.Location;

public class Player extends LivingEntity {
	
	
	private boolean vertMod,horizMod,deathSchedule;
	private int xp, strength;
	private int lastLevel;
	public static final int MAX_LEVEL = 20, MAX_HEALTH = 5000, MAX_STRENGTH = 550;
	//Check if horizontal / vertical keys are being held.
	
	public Player(Location l, int health) {
		super(l);
		this.health = health;
		strength = 10;
		deathSchedule = false;
		lastLevel = 0;
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
		lastLevel = getLevel();
	}
	
	public void addXP(int xp)
	{
		this.xp+=xp;
		if(getLevel() > lastLevel)
		{
			//Level up!
		//	this.setHealth(this.getMaxHealth());
			lastLevel = getLevel();
			setStats(lastLevel);
			System.out.println("Level up: " + getLevel() + "!");
		}
	}
	
	private void setStats(int level)
	{
		this.setMaxHealth((int)((double)MAX_HEALTH * (double)((double)level / (double)MAX_LEVEL)));
		this.setHealth(this.getMaxHealth());
		this.setStrength((int)((double)MAX_STRENGTH * ((double)level / (double)MAX_LEVEL)));
	}
	
	public int getStrength()
	{
		return this.strength;
	}
	
	public void setStrength(int str)
	{
		this.strength = str;
	}
	
	public int getLevel()
	{
		for (int i = Player.MAX_LEVEL; i >= 1; i--)
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
				e.wasHit(strength, this);
			}
		}
		addAnimation(new PlayerHitAnimation(this));
	}
	
	@Override
	public void tick() {
		super.tick();
		movement();
		if (this.isDead() && !deathSchedule){
			deathSchedule=true;
			Game.getCurrentGame().addTask(new Task(120) {
				@Override
				public void run()
				{
					if(isDead())
					{
						try {
							Thread.sleep(1000);
							System.exit(0);
						} catch (Exception e)
						{
						
						}
					} else
					{
						System.out.println("this lucky ass");
						//Lucky ass
						deathSchedule=false;
					}
				}
			});
		}
	}
	
	public int getXPForLevel(int i)
	{
		return (i*100 + ((i-1) * 10));
	}
	
	private void drawXPBarAndLevel(Graphics g, int xo, int yo)
	{
		Color gold = new Color(237,202,0);
		int length = (int)(getBounds().getWidth()*.6);
		int height = (int)(getBounds().getHeight()*.1);
		int x = xo - (int)(.5 * length) + (int)(getBounds().getWidth()/2);
		int y = yo + (int)(.66 * getBounds().getHeight());
		g.setColor(Color.cyan);
		g.drawRect(x-1, y-1, length+1, height+1);
		g.setColor(Color.white);
		g.fillRect(x, y, length, height);
		g.setColor(gold);
		double per = (double)getXP()/(double)getXPForLevel(getLevel()+1);
		g.fillRect(x, y, (int)(length*per), height);
		x+=(int)(.5*length)-10;
		y+=height;
		g.setColor(Color.red);
		g.drawString(""+(int)(per*100)+"%", x, y);
		//Bar ^^ Level vv
		g.setColor(gold);
		y = yo + (int)(.95 * getBounds().getHeight());
		x = xo + (int)(getBounds().getWidth()/2) - 5;
		g.drawString(""+getLevel(), x, y);
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(xo, yo, WIDTH, HEIGHT);
		renderAnimations(g,xo,yo);
		drawHealthBar(g,xo,yo);
		drawXPBarAndLevel(g,xo,yo);
		g.setColor(c);
	}





}