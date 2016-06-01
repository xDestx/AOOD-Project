package game.inventory;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import game.entity.neutral.Collectible;
import game.graphic.ImageLoader;
import game.world.Location;

public class Item {

	private BufferedImage icon;
	private String name;
	
	public Item(String name, String path)
	{
		this.name = name;
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
	
	public String toString()
	{
		return name;
	}

}
