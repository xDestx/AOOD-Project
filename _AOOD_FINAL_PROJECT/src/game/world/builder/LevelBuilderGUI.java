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

public class LevelBuilderGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5429456408453891258L;
	private Stack<Rectangle> history;
	private JPanel p;
	private Rectangle tempR;
	private Point mouseP;
	
	//I'm gonna try to use this to build everything
	//It will *not* pick textures (lazy)
	public LevelBuilderGUI() {
		super("Level Builder GUI");
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
					g2.fill(r);
				}
				if (tempR != null)
					g2.draw(tempR);
				if (mouseP != null)
				{
					g2.setColor(Color.red);
					g2.fillRect((int)mouseP.getX(), (int)mouseP.getY(), 8, 8);
				}
			}
			
		};
		this.add(p);
		this.setVisible(true);
		history = new Stack<Rectangle>();
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
		p1 = e.getPoint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(p1 != null)
		{
			int w = (int)Math.abs(e.getPoint().getX() - p1.getX());
			int h = (int)Math.abs(e.getPoint().getY() - p1.getY());
			int x = (p1.getX() < e.getPoint().getX()) ? (int)p1.getX():(int)e.getPoint().getX();
			int y = (p1.getY() < e.getPoint().getY()) ? (int)p1.getY():(int)e.getPoint().getY();
			a.setTemp(new Rectangle(x,y,w,h));
			a.repaint();
		//	System.out.println("nice meme dude");
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		a.setMouseP(e.getPoint());
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
			int x = (p1.getX() < p2.getX()) ? (int)p1.getX():(int)p2.getX();
			int y = (p1.getY() < p2.getY()) ? (int)p1.getY():(int)p2.getY();
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
