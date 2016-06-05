package game.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.TextLayout;

import game.GFrame;
import game.Game;
import game.world.Location;

public class TextAnimation extends Animation {

	private String text;
	private Font font;
	private Color drawColor;
	private boolean built;
	private TextLayout textBox;
	
	public TextAnimation(int maxFrames, String text) {
		super(maxFrames);
		this.text = text;
		built = false;
		drawColor = Color.black;
	}

	private void build(Graphics g)
	{
		font = new Font("Impact",Font.PLAIN, 12);
		Graphics2D g2 = (Graphics2D)g;
		textBox = new TextLayout(text, font, g2.getFontRenderContext());
		while(textBox.getBounds().getWidth() < GFrame.WIDTH-200)
		{
			font = new Font(font.getName(), font.getStyle(), font.getSize()+1);
			g.setFont(font);
			textBox = new TextLayout(text, g.getFont(), g2.getFontRenderContext());
		}
		while(textBox.getBounds().getWidth() > GFrame.WIDTH-200)
		{
			font = new Font(g.getFont().getFontName(), g.getFont().getStyle(), g.getFont().getSize()-1);
			g.setFont(font);
			textBox = new TextLayout(text, g.getFont(), g2.getFontRenderContext());
		}
		built = true;
	}
	
	@Override
	public void render(Graphics g, int xo, int yo) {
		if(!built)
			build(g);
		Color last = g.getColor();
		Font lastFont = g.getFont();
		g.setColor(drawColor);
		Graphics2D g2 = (Graphics2D)g;
		g.setFont(font);
		
		
		g2.drawString(text, (int)((GFrame.WIDTH/2) - (textBox.getBounds().getWidth()/2)), (int)((GFrame.HEIGHT/2)));
		
		g.setColor(last);
		g.setFont(lastFont);
	}
	
	/*
	 * Nothing should call these
	 */
	
	/*
	 * Null pointer death right here
	 */

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	protected void changeFrame() {
		int stage = 0;
		/*
		 * Stages
		 * 0 Fade in
		 * 1 Constant
		 * 2 Fade out
		 */
		double per = (getFrame() / getTotalFrames());
		if(per < .125)
		{
			stage = 0;
		} else if (per < .885)
		{
			stage = 1;
		} else
		{
			stage = 2;
		}
		int r = 195;
		int g = 24;
		int b = 154;
		int a;
		if (stage == 0)
		{
			double maxFrames = (int)(.125 * getTotalFrames());
			a = (int)(((double)getFrame()/maxFrames) * (255.0));
			if (a >= 255)
				a = 254;
		} else if (stage == 2)
		{
			double maxFrames = (int)(.115 * getTotalFrames());
			double cFrame = getTotalFrames() - getFrame();
			a = (int)((cFrame/maxFrames) * (255.0));
			if (a >= 255)
				a = 254;
		} else {
			a = 255;
		}
		
		drawColor = new Color(r,g,b,a);
	}


}
