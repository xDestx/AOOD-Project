package game;

import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.entity.Enemy;
import game.entity.Player;
import game.entity.Stick;
import game.listener.CKeyListener;
import game.world.Collidable;
import game.world.Level;
import game.world.Location;
import game.world.Wall;

public class Game {

	private boolean playing;
	public static final int TICK = 100;
	private GFrame gf;
	private Graphics graphics;
	private Ticker t;
	private Camera c;
	private GameState gs;
	private Player p;
	private Stick e;
	private Level currentLevel;
	private ArrayList<Enemy> enemies;

	//aaaa
	private static Game game;
	
	public static void main(String[] args) {
		game = new Game();
		game.play();
	}
	
	public static Game getCurrentGame()
	{
		return game;
	}
	

	public Game() {
		playing = false;
		gf = new GFrame(this);
		gf.setVisible(true);
		graphics = gf.getGraphics();
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = new Camera(new Location(0,0),this);
		t = new Ticker(this);
		p = new Player(new Location(GFrame.WIDTH/2,GFrame.HEIGHT/2), 100);
		e = new Stick(new Location(GFrame.WIDTH - 100, GFrame.HEIGHT - 100), 100, 100);
		this.addObject(p);
		this.addObject(e);
		t.addObject(c);
		enemies = new ArrayList<Enemy>();
		//idk where to put this line ^, but prob not here
		enemies.add(e);
		gs = GameState.WORLD;
		ArrayList<Collidable> cc = new ArrayList<Collidable>();
		cc.add(new Wall(1600,10,new Location(0,0)));
		cc.add(new Wall(10,900, new Location(0,0)));
		cc.add(new Wall(1600,10,new Location(0,900)));
		cc.add(new Wall(10,900, new Location(1600,10)));
		cc.add(new Wall(200,200, new Location(300,300)));
		currentLevel = new Level(0,cc);
		gf.getCanvas().addKeyListener(new CKeyListener(this));
	}
	
	public Camera getCamera()
	{
		return c;
	}
	
	public void setLevel(Level l)
	{
		this.currentLevel = l;
	}
	
	public Level getLevel()
	{
		return this.currentLevel;
	}
	
	public void addObject(GameObject o)
	{
		t.addObject(o);
	}
	
	public void addRenderable(Renderable r)
	{
		t.addRenderable(r);
	}

	public void play() {
		// Game loop
		playing = true;
		long last = System.nanoTime();
		double ns = 1000000000 / TICK;
		double d = 0;
		int k = 0;
		int f = 0;
		long s = 0;
		while (playing) {
			long n = System.nanoTime();
			d += (n - last) / ns;
			s += (n - last);
			last = n;
			if (d >= 1) {
				gameTick();
				k++;
				d--;
			}

			renderScreen();

			f++;
			if (s >= 1000000000) {
				System.out.println("Ticks: " + k + " | Fps: " + f);
				f = 0;
				s = 0;
				k = 0;
			}

		}
		System.exit(0);
	}

	private void gameTick() {
		t.tick();
	}

	public Player getPlayer()
	{
		return p;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	private void renderScreen() {
		gf.render(t);
		//t.render(graphics, c);
		//gf.repaint();
	}

	public GameState getState()
	{
		return this.gs;
	}
	
}