package game;

import java.awt.Graphics;

public class Game {

	private boolean playing;
	public static final int TICK = 100;
	private GFrame gf;
	private Graphics graphics;
	private Ticker t;
	private Camera c;

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
	

	public Game() {
		playing = false;
		gf = new GFrame(this);
		gf.setVisible(true);
		graphics = gf.getGraphics();
		c = new Camera(new Location(0,0));
		t = new Ticker(this);
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

	private void renderScreen() {
		t.render(graphics, c);
	}

}
