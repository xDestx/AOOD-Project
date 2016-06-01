package game.inventory;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import game.entity.neutral.Collectible;
import game.graphic.ImageLoader;
import game.world.Location;

public class Item extends Collectible {

	private BufferedImage icon;
	
	public Item(Location l)
	{
		super(l);
		try {
			icon = ImageLoader.getImage("flashysuit.png");
		} catch (Exception e) {
			icon = null;
		}
	}
	
	
	public BufferedImage getIcon()
	{
		return this.icon;
	}


	@Override
	public void effect() {
		//Pick up effect (add to inventory)
	}


	@Override
	public void render(Graphics g, int xo, int yo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
