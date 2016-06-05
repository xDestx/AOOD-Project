package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.GFrame;
import game.Game;
import game.entity.enemy.Enemy;
import game.graphic.CircleAnimation;
import game.graphic.PlayerHitAnimation;
import game.graphic.TextAnimation;
import game.util.Task;
import game.world.Location;
import game.world.Vector;

public class Player extends LivingEntity {
	
	
	private boolean vertMod,horizMod,deathSchedule,bonusShown;
	private int xp;
	private final static int LEVEL_XP = 100;
	private CircleAnimation rangedAnimation;
//	private TextAnimation textAnimation;
	private int lastLevel;
	private int attackStyle;
	public static final int ATTACK_RANGED = 1, ATTACK_MELEE = 0;
	public static final int MAX_LEVEL = 20, MAX_HEALTH = 5000, MAX_STRENGTH = 550;
	//Check if horizontal / vertical keys are being held.
	
	public Player(Location l, int health) {
		super(l);
		bonusRegen = 0;
		bonusHp = 0;
		bonusShown = false;
		bonusStr = 0;
		this.health = health;
		strength = 10;
		attackStyle = Player.ATTACK_MELEE;
		rangedAnimation = null;
		//textAnimation = null;
		deathSchedule = false;
		lastLevel = 1;
		this.maxHealth = health;
		xp = 0;
	}
	
	public int getXP()
	{
		return this.xp;
	}
	
	public void setBonusShown(boolean b)
	{
		this.bonusShown = b;
	}
	
	public boolean getBonusShown()
	{
		return this.bonusShown;
	}
	
	public void setXP(int xp)
	{
		this.xp=xp;
		lastLevel = getLevel();
	}
	
	public void enableRangedAnimation()
	{
		if (rangedAnimation == null)
		{
			rangedAnimation = new CircleAnimation(-1, this, getBounds().getWidth()*1.25, getLevel(), Color.BLUE, 12, this.level*2);
			this.addAnimation(rangedAnimation);
		}
	}
	
	public void disableRangedAnimation()
	{
		if (rangedAnimation != null)
		{
			animations.remove(rangedAnimation);
			rangedAnimation = null;
		}
	}
	
	public void setAttackStyle(int style)
	{
		this.attackStyle = style;
		if(style == Player.ATTACK_RANGED)
			enableRangedAnimation();
		else
			disableRangedAnimation();
	}
	
	public void addXP(int xp)
	{
		this.xp+=xp;
		System.out.println(xp + " !!");
		if(getLevel() > lastLevel)
		{
			//Level up!
			lastLevel = getLevel();
			setStats(lastLevel);
			if(getAttackStyle() == Player.ATTACK_RANGED)
			{
				disableRangedAnimation();
				enableRangedAnimation();
			}
			System.out.println("Level up: " + getLevel() + "!");
			this.addAnimation(new TextAnimation(3 * Game.TICK, "Level up (" + lastLevel + ")!"));
		}
	}
	
	
	
	private void setStats(int level)
	{
		System.out.println("Da ding!");
		this.setMaxHealth((int)((double)MAX_HEALTH * (double)((double)level / (double)MAX_LEVEL)));
		this.setHealth(this.getMaxHealth());
		this.setStrength((int)((double)MAX_STRENGTH * ((double)level / (double)MAX_LEVEL)));
	}
	
	
	public int getLevel()
	{
		for (int i = Player.MAX_LEVEL; i >= 1; i--)
		{
			//System.out.println(getXP() + "  " + getXPForLevel(i));
			if(getXP() >= getXPForLevel(i))
			{
				return i;
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

	public int getAttackStyle()
	{
		return this.attackStyle;
	}

	public void attack(double angle)
	{
		Vector v = new Vector(angle, 10);
		launchProjectile(v,(int)(getStrength()*.45));
	}
	
	@Override
	public void attack()
	{
		for (Enemy e : Game.getCurrentGame().getEnemies()){
			if (this.getAttackBounds().intersects(e.getBounds())){
				e.wasHit((int)(getStrength()), this);
			}
		}
		addAnimation(new PlayerHitAnimation(this));
	}
	
	
	@Override
	public void tick() {
		movement();
		if (this.isDead() && !deathSchedule){
			deathSchedule=true;
			this.addAnimation(new TextAnimation(Game.TICK * 3, "Absolutely annihilated (you died)"));
			Game.getCurrentGame().addTask(new Task(Game.TICK * 2) {
				@Override
				public void run()
				{
						try {
							Thread.sleep(1000);
							System.exit(0);
						} catch (Exception e)
						{
						
						}
					
				}
			});
		}
		super.tick();
	}
	
	public int getXPForLevel(int i)
	{
		if (i <= 0)
			return 0;
		int amt = Player.LEVEL_XP;
		for (int k = 0; k < i-1; k++)
		{
			amt+=(amt*1.25);
		}
		return amt;
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
		
		/*
		 * Figure this shit out jesus
		 */
		double top = (double)(getXP() - getXPForLevel(getLevel()-1));
		double bottom =	(double)(getXPForLevel(getLevel()+1) - getXPForLevel(getLevel()));
		System.out.println(top+ "  " + bottom);
		double per = top/bottom;
		
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
		getInventory().render(g);
		renderBonus(g);
		g.setColor(c);
	}

	private void renderBonus(Graphics g)
	{
		if(!bonusShown)
			return;
		int bonusWidth = 500;
		int bonusHeight = 325;
		Color str = Color.ORANGE;
		Color hp = Color.RED;
		Color rg = Color.PINK;
		int x = (int)((GFrame.WIDTH/2)-(bonusWidth/2));
		int y = (int)((GFrame.HEIGHT/2)-(bonusHeight/2));
		g.setColor(Color.black);
		g.fillRect(x, y, bonusWidth, bonusHeight-30);
		
		//Health v Bonus Health
		int barWidth = 300;
		int barStart = x+(bonusWidth/2)-barWidth/2;
		int barYStart = bonusHeight/8;
		
		g.setColor(Color.GREEN);
		g.fillRect(barStart, (y+(1*barYStart)), barWidth, 50);
		double per = ((double)maxHealth/(double)getMaxHealth());
		int amt = (int)(barWidth * per);
		g.setColor(hp);
		g.fillRect(barStart, (y+(1*barYStart)), amt, 50);
		
		g.setColor(Color.black);
		g.drawString("+"+(getMaxHealth()-maxHealth) + "hp  [" + getMaxHealth() + "HP]", (int)(barStart*1.25), (y+(1*barYStart))+25);
		
		g.setColor(Color.green);
		g.fillRect(barStart, (y+(3*barYStart)), barWidth, 50);
		per = ((double)strength/(double)getStrength());
		amt = (int)(barWidth * per);
		g.setColor(str);
		g.fillRect(barStart, (y+(3*barYStart)), amt, 50);
		
		g.setColor(Color.black);
		g.drawString("+"+(getStrength()-strength) + "str  [" + getStrength() + "STR]", (int)(barStart*1.25), (y+(3*barYStart))+25);
		
		g.setColor(rg);
		g.fillRect(barStart, (y+(5*barYStart)), barWidth, 50);
		
		g.setColor(Color.black);
		g.drawString("+" + (int)(bonusRegen*Game.TICK) + "rgn", (int)(barStart*1.25), (y+(5*barYStart))+25);
		
	}

}