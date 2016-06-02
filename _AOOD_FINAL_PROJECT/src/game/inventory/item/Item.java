package game.inventory.item;

import java.awt.image.BufferedImage;

import game.entity.LivingEntity;
import game.graphic.ImageLoader;

public abstract class Item {

	private BufferedImage icon;
	private String name;
	private int hB,sB;
	private double rB;
	
	/*
	 * Regen shall be measured in heal / t
	 * rB = 5;
	 * every 1/t h
	 * 
	 * 5 / Game.TICK = tps
	 */
	
	public Item(String name, String path, int strBoost, int healthBoost, double regenBoost)
	{
		this.name = name;
		this.hB = healthBoost;
		this.rB = regenBoost;
		this.sB = strBoost;
		try {
			icon = ImageLoader.getImage(path);
		} catch (Exception e) {
			icon = null;
		}
	}
	
	public int getHealthBoost()
	{
		return hB;
	}
	
	public int getStrengthBoost()
	{
		return this.sB;
	}
	
	public double getRegenBoost()
	{
		return this.rB;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public BufferedImage getIcon()
	{
		return this.icon;
	}
	
	public String toString()
	{
		return name;
	}
	
	
	public static Item createRandomItem()
	{
		Class<?>[] items = {SoulStone.class};
		int i = (int)(Math.random()*items.length);
		try {
			return (Item)items[i].newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
