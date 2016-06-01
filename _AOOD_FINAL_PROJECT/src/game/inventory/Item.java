package game.inventory;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import game.graphic.ImageLoader;

public class Item {

	private BufferedImage icon;
	
	public Item()
	{
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
	
}
