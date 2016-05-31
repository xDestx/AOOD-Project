package game.world;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.Camera;
import game.GFrame;
import game.Game;
import game.GameObject;
import game.Renderable;
import game.entity.enemy.Enemy;
import game.entity.neutral.Collectible;
import game.entity.neutral.Projectile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Level extends GameObject implements Renderable {

	public static int HEIGHT = 13500, WIDTH = 24000;
	
	/*
	 * Idea: Procedural Generation to generate worlds using a seed
	 */
	
	//Collidables please stay unchanged
	private boolean enemiesUsed, collectiblesUsed,projectilesUsed,worldObjectsUsed;
	private ArrayList<Collidable> listOfCollidables;
	private ArrayList<Enemy> enemies;
	private ArrayList<Collectible> allCollectibles;
	private ArrayList<Projectile> projectiles;
	private ArrayList<WorldObject> worldObjects;
	private ArrayList<Enemy> toE;
	private ArrayList<Collectible> toC;
	private ArrayList<Projectile> toP;
	private ArrayList<WorldObject> toW;
	private int id;
	private BufferedImage bi;
	private GroundPattern pattern;
	private Location l;

	public Level(int idNumber) {
		listOfCollidables = new ArrayList<Collidable>();
		id = idNumber;
		l = new Location(0, 0);
		toE = new ArrayList<Enemy>();
		toP = new ArrayList<Projectile>();
		toW = new ArrayList<WorldObject>();
		toC = new ArrayList<Collectible>();
		enemiesUsed = false;
		collectiblesUsed = false;
		projectilesUsed = false;
		worldObjectsUsed = false;
		enemies = new ArrayList<Enemy>();
		allCollectibles = new ArrayList<Collectible>();
		projectiles = new ArrayList<Projectile>();
		try {
			bi = ImageIO.read(getClass().getResourceAsStream("/images/grass_cool.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		pattern = new GroundPattern();
		worldObjects = new ArrayList<WorldObject>();
	}

	public Level(int idNumber, BufferedImage b) {
		listOfCollidables = new ArrayList<Collidable>();
		enemies = new ArrayList<Enemy>();
		allCollectibles = new ArrayList<Collectible>();
		enemiesUsed = false;
		toE = new ArrayList<Enemy>();
		toP = new ArrayList<Projectile>();
		toW = new ArrayList<WorldObject>();
		toC = new ArrayList<Collectible>();
		collectiblesUsed = false;
		projectilesUsed = false;
		worldObjectsUsed = false;
		id = idNumber;
		bi = b;
		projectiles = new ArrayList<Projectile>();
		pattern = new GroundPattern();
		worldObjects = new ArrayList<WorldObject>();
	}

	public Level(int idNumber, ArrayList<Collidable> cs) {
		listOfCollidables = cs;
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		allCollectibles = new ArrayList<Collectible>();
		toE = new ArrayList<Enemy>();
		toP = new ArrayList<Projectile>();
		toW = new ArrayList<WorldObject>();
		toC = new ArrayList<Collectible>();
		id = idNumber;
		enemiesUsed = false;
		collectiblesUsed = false;
		projectilesUsed = false;
		worldObjectsUsed = false;
		try {
			bi = ImageIO.read(getClass().getResourceAsStream("/images/grass_cool.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		pattern = new GroundPattern();
		worldObjects = new ArrayList<WorldObject>();
	}

	public Level(int idNumber, BufferedImage b, ArrayList<Collidable> cs) {
		listOfCollidables = cs;
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		enemiesUsed = false;
		toE = new ArrayList<Enemy>();
		toP = new ArrayList<Projectile>();
		toW = new ArrayList<WorldObject>();
		toC = new ArrayList<Collectible>();
		collectiblesUsed = false;
		projectilesUsed = false;
		worldObjectsUsed = false;
		allCollectibles = new ArrayList<Collectible>();
		id = idNumber;
		bi = b;
		worldObjects = new ArrayList<WorldObject>();
		pattern = new GroundPattern();
	}

	public int getId() {
		return id;
	}


	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}

	public ArrayList<Collidable> getListOfCollidables() {
		ArrayList<Collidable> newList = new ArrayList<Collidable>();
		newList.addAll(listOfCollidables);
		enemiesUsed = true;
		for(Enemy e : enemies)
		{
			if (e instanceof Collidable)
			{
				newList.add((Collidable)e);
			}
		}
		enemiesUsed = false;
		
		collectiblesUsed = true;
		for(Collectible e : allCollectibles)
		{
			if (e instanceof Collidable)
			{
				newList.add((Collidable)e);
			}
		}
		collectiblesUsed = false;
		
		worldObjectsUsed = true;
		for(WorldObject e : worldObjects)
		{
			if (e instanceof Collidable)
			{
				newList.add((Collidable)e);
			}
		}
		worldObjectsUsed = false;
		
		return newList;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void addProjectile(Projectile o)
	{
		if(projectilesUsed)
			toP.add(o);
		else
			this.projectiles.add(o);
	}
	
	public void removeProjectile(Projectile o)
	{
		this.projectiles.remove(o);
	}
	
	public ArrayList<WorldObject> getWorldObjects()
	{
		return worldObjects;
	}

	public ArrayList<Collectible> getAllCollectibles() {
		return allCollectibles;
	}

	public void addEnemy(Enemy e) {
		if(enemiesUsed)
			toE.add(e);
		else
			enemies.add(e);
	}
	
	public void addWorldObject(WorldObject o)
	{
		if(worldObjectsUsed)
			toW.add(o);
		else
			this.worldObjects.add(o);
	}

	public void addCollectible(Collectible c) {
		if(collectiblesUsed)
			toC.add(c);
		else
			allCollectibles.add(c);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		if (bi != null) {
			int startX = 0 - ((int) Game.getCurrentGame().getCamera().getLocation().getX() % bi.getWidth())
					- bi.getWidth();
			int startY = 0 - ((int) Game.getCurrentGame().getCamera().getLocation().getY() % bi.getHeight())
					- bi.getHeight();
			int cX = startX;
			int cY = startY;
			while (cX < GFrame.WIDTH || cY < GFrame.HEIGHT) {
				g.drawImage(bi, cX, cY, null);
				cX += bi.getWidth();
				if (cX >= GFrame.WIDTH) {
					cY += bi.getWidth();
					if (cY >= GFrame.HEIGHT) {
						break;
					} else {
						cX = startX;
					}
				}
			}
		} else {
			int startX = 0 - ((int) Game.getCurrentGame().getCamera().getLocation().getX() % 100) - 100;
			int startY = 0 - ((int) Game.getCurrentGame().getCamera().getLocation().getY() % 100) - 100;
			int cX = startX;
			int cY = startY;
			while (cX < GFrame.WIDTH || cY < GFrame.HEIGHT) {
				pattern.render(g, cX, cY);
				cX += 100;
				if (cX >= GFrame.WIDTH) {
					cY += 100;
					if (cY >= GFrame.HEIGHT) {
						break;
					} else {
						cX = startX;
					}
				}
			}
		}

		// Draw WorldObjects
		
		renderCollides(g, x, y);
		enemiesUsed = true;
		renderEnemies(g, x, y);
		enemiesUsed = false;
		worldObjectsUsed = true;
		renderWorldObjects(g,x,y);
		worldObjectsUsed = false;
		collectiblesUsed = true;
		renderCollectibles(g, x, y);
		collectiblesUsed = false;
		projectilesUsed = true;
		renderProjectiles(g,x,y);
		projectilesUsed = false;
	}

	private void renderCollectibles(Graphics g, int xo, int yo) {
		Camera c = Game.getCurrentGame().getCamera();
		for (Collectible r : getAllCollectibles()) {
			if (r.getBounds().intersects(c.getViewBounds())) {
				int[] xy = Camera.calculateOffset(r.getLocation());
				r.render(g, xy[0], xy[1]);
			}
		}
	}
	
	private void renderProjectiles(Graphics g, int xo, int yo) {
		Camera c = Game.getCurrentGame().getCamera();
		for (Projectile r : projectiles) {
			if (r.getBounds().intersects(c.getViewBounds())) {
				int[] xy = Camera.calculateOffset(r.getLocation());
				r.render(g, xy[0], xy[1]);
			}
		}
	}
	
	private void renderWorldObjects(Graphics g, int xo, int yo) {
		Camera c = Game.getCurrentGame().getCamera();
		for (WorldObject r : getWorldObjects()) {
			if (r.getBounds().intersects(c.getViewBounds())) {
				int[] xy = Camera.calculateOffset(r.getLocation());
				r.render(g, xy[0], xy[1]);
			}
		}
	}


	private void renderEnemies(Graphics g, int xo, int yo) {
		Camera c = Game.getCurrentGame().getCamera();
		for (Enemy r : getEnemies()) {
			if (r.getBounds().intersects(c.getViewBounds())) {
				int[] xy = Camera.calculateOffset(r.getLocation());
				r.render(g, xy[0], xy[1]);
			}
		}
	}

	private void renderCollides(Graphics g, int xo, int yo) {
		Camera c = Game.getCurrentGame().getCamera();
		for (Collidable r : getListOfCollidables()) {
			if (r.getBounds().intersects(c.getViewBounds())) {
				int[] xy = Camera.calculateOffset(r.getLocation());
				r.render(g, xy[0], xy[1]);
			}
		}
	}

	public void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	public void removeWorldObject(WorldObject e) {
		worldObjects.remove(e);
	}

	public void removeCollectible(Collectible e) {
		allCollectibles.remove(e);
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return l;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick() {
		tickEnemies();
		tickWObjs();
		
		// tickCollides();
		tickCollects();
		tickProjectiles();
	}
	
	private void tickProjectiles()
	{
		projectilesUsed = true;
		for (Projectile p : getProjectiles())
		{
			p.tick();
		}
		projectilesUsed = false;
		for(Projectile p : toP)
		{
			getProjectiles().add(p);
		}
		toP.clear();
	}

	private void tickEnemies() {
		enemiesUsed = true;
		for (Enemy go : getEnemies()) {
			go.tick();
		}
		enemiesUsed = false;
		for (Enemy e : toE)
		{
			getEnemies().add(e);
		}
		toE.clear();
	}
	
	private void tickWObjs() {
		worldObjectsUsed = true;
		for (WorldObject go : getWorldObjects()) {
			go.tick();
		}
		worldObjectsUsed = false;
		for(WorldObject wo : toW)
		{
			getWorldObjects().add(wo);
		}
		toW.clear();
	}

	private void tickCollects() {
		collectiblesUsed = true;
		for (Collectible go : getAllCollectibles()) {
			go.tick();
		}
		collectiblesUsed = false;
		for(Collectible c : toC)
		{
			getAllCollectibles().add(c);
		}
		toC.clear();
	}

}
