package game.inventory;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Item {

	private BufferedImage icon;
	
	public Item()
	{
		try {
			icon = ImageIO.read(getClass().getResourceAsStream("/images/grass_cool.png"));
		} catch (Exception e) {
			icon = null;
		}
	}
	
	
	public BufferedImage getIcon()
	{
		return this.icon;
	}
	
}
