package game.world.builder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Camera;
import game.world.Location;

public class LevelBuilderGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5429456408453891258L;
	private Stack<Rectangle> history;
	private JPanel p;
	private Rectangle tempR;
	private Point mouseP;
	private Camera c;
	
	//I'm gonna try to use this to build everything
	//It will *not* pick textures (lazy)
	public LevelBuilderGUI() {
		super("Level Builder GUI");
		c = new Camera(new Location(0,0), null);
		this.setSize(1600,900);
		this.setResizable(false);
		RectangleKeys rk = new RectangleKeys(this);
		this.addKeyListener(rk);
		RectangleMouse rm = new RectangleMouse(this);
		this.addMouseListener(rm);
		this.addMouseMotionListener(rm);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponents(g);
				Graphics2D g2 = (Graphics2D)g;
				for (Rectangle r : history)
				{
					if(r.getBounds().intersects(c.getViewBounds()))
					{
						int x = ((int)(r.getBounds().getLocation().getX() - c.getLocation().getX()));
						int y = (int)(r.getBounds().getLocation().getY() - c.getLocation().getY());
						g2.fillRect(x,y,(int)r.getWidth(),(int)r.getHeight());
					}
					
				}
				if (tempR != null)
					g2.draw(tempR);
				if (mouseP != null)
				{
					g2.setColor(Color.red);
					g2.fillRect((int)mouseP.getX(), (int)mouseP.getY(), 8, 8);
					g2.drawString((mouseP.getX() + c.getLocation().getX())+ ", " + (mouseP.getY() + c.getLocation().getY()), (int)mouseP.getX(), (int)mouseP.getY());
				}
			}
			
		};
		this.add(p);
		this.setVisible(true);
		history = new Stack<Rectangle>();
	}

	public Camera getCamera()
	{
		return c;
	}
	
	public JPanel getJPanel()
	{
		return p;
	}
	
	public Rectangle getTemp()
	{
		return tempR;
	}
	
	public void setMouseP(Point p)
	{
		this.mouseP = p;
	}
	
	public void setTemp(Rectangle r)
	{
		this.tempR = r;
	}
	
	public void addRectangle(Rectangle r)
	{
		this.history.add(r);
		for (Rectangle rec: history)
		{
			if (rec!= null)
				System.out.println(rec.toString());
		}
		System.out.println("\n\n");
	}
	
	public void undo()
	{
		if(history.size() > 0)
			history.pop();
		for (Rectangle rec: history)
		{
			if (rec!= null)
				System.out.println(rec.toString());
		}
		System.out.println("\n\n");
		this.repaint();
	}
	
	public void repaint()
	{
		super.repaint();
	}
	
	public void export()
	{
		String print = "";
		print+="ArrayList<Collidable> cc = new ArrayList<Collidable>();\n";
		for (Rectangle r : history)
		{
			print+="cc.add(new Wall(" + (int)r.getWidth() + "," + (int)r.getHeight() + ", new Location(" + (int)r.getX() + "," + (int)r.getY() +")));\n";
		}
		print+="Level level = new Level( ??, cc);\n";
		System.out.println("\n"+print);
	}

}

class RectangleKeys extends KeyAdapter {
	
	private LevelBuilderGUI a;
	
	public RectangleKeys(LevelBuilderGUI a)
	{
		this.a = a;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			a.export();
		} else if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown())
		{
			a.undo();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			a.getCamera().getLocation().setX(a.getCamera().getLocation().getX()+5);
			a.getCamera().getBounds().setLocation(a.getCamera().getLocation().toPoint());
			a.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			a.getCamera().getLocation().setX(a.getCamera().getLocation().getX()-5);
			a.getCamera().getBounds().setLocation(a.getCamera().getLocation().toPoint());
			a.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			a.getCamera().getLocation().setY(a.getCamera().getLocation().getY()-5);

			a.getCamera().getBounds().setLocation(a.getCamera().getLocation().toPoint());
			a.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			a.getCamera().getLocation().setY(a.getCamera().getLocation().getY()+5);

			a.getCamera().getBounds().setLocation(a.getCamera().getLocation().toPoint());
			a.repaint();
		}
	}
	
}


class RectangleMouse extends MouseAdapter {
	
	private Point p1, p2;
	private LevelBuilderGUI a;
	
	public RectangleMouse(LevelBuilderGUI a)
	{
		this.a = a;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (p2 != null)
			p2 = null;
		p1 = new Point(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(p1 != null)
		{
			int w = (int)Math.abs(e.getPoint().getX() - p1.getX());
			int h = (int)Math.abs(e.getPoint().getY() - p1.getY());
			int x = (p1.getX() < e.getPoint().getX()) ? (int)p1.getX():(int)e.getPoint().getX();
			int y = (p1.getY()-25 < e.getPoint().getY()-25) ? (int)p1.getY()-25:(int)e.getPoint().getY()-25;
			a.setTemp(new Rectangle(x,y,w,h));
			a.setMouseP(new Point(e.getX(), e.getY()-25));
			a.repaint();
		//	System.out.println("nice meme dude");
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		a.setMouseP(new Point(e.getX(), e.getY()-25));
		a.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(p1 == null)
			return;
		p2 = e.getPoint();
		if (p1 != null && p2 != null)
		{
			int w = (int)Math.abs(p2.getX() - p1.getX());
			int h = (int)Math.abs(p2.getY() - p1.getY());
			int x = (p1.getX() < p2.getX()) ? (int)p1.getX()+(int)a.getCamera().getLocation().getX():(int)p2.getX()+(int)a.getCamera().getLocation().getX();
			int y = (p1.getY()-25 < p2.getY()-25) ? (int)p1.getY()+(int)a.getCamera().getLocation().getY()-25:(int)p2.getY()+(int)a.getCamera().getLocation().getY()-25;
			if (w == 0 || h == 0)
			{
				p1 = null;
				p2 = null;
				return;
			}
			a.addRectangle(new Rectangle(x,y,w,h));
			a.setTemp(null);
			a.repaint();
		}
	}
	
}
