package game.inventory.item;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import game.Game;
import game.graphic.ImageLoader;

public abstract class Item {

	private BufferedImage icon;
	private static Font[] fonts = 
			{
				new Font("Arial",Font.PLAIN,14),
				new Font("Arial",Font.ITALIC,14),
				new Font("Times New Roman",Font.PLAIN, 14),
				new Font("Times New Roman", Font.ITALIC, 14),
				new Font("Times New Roman", Font.BOLD, 16)
			};
	private static Color[] colors = 
		{
			Color.WHITE,
			Color.CYAN,
			Color.MAGENTA,
			Color.ORANGE,
			Color.RED
		};
	private String name, lore, buffs, biggest;
	private int hB,sB;
	private int infoWidth,infoHeight,infoOffset;
	private int rarity;
	private double rB;
	public static final int COMMON = 0,UNCOMMON = 1,RARE = 2,LEGENDARY = 3,GOD = 4;
	
	/*
	 * Regen shall be measured in heal / t
	 * rB = 5;
	 * every 1/t h
	 * 
	 * 5 / Game.TICK = tps
	 */
	
	public Item(String name, String path, String lore, int strBoost, int healthBoost, double regenBoost, int rarity)
	{
		this.name = name;
		infoWidth = -1;
		infoHeight = -1;
		infoOffset = -1;
		this.lore = lore;
		this.hB = healthBoost;
		this.rB = regenBoost;
		this.sB = strBoost;
		this.rarity = rarity;
		buffs = "+" + this.hB + "HP  +" + this.sB + "STR  +" + (this.rB*Game.TICK) + "RGN";
		biggest = (buffs.length() > name.length()) ? buffs:name;
		biggest = (biggest.length() > lore.length()) ? biggest:lore;
		
		try {
			icon = ImageLoader.getImage(path);
		} catch (Exception e) {
			icon = null;
		}
	}
	
	public String getBiggest()
	{
		return this.biggest;
	}
	
	public String getBuffString()
	{
		return this.buffs;
	}
	
	public int getRarity()
	{
		return this.rarity;
	}

	public String getLore()
	{
		return this.lore;
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
	
	public static Font getFont(int rarity)
	{
		rarity = (rarity > 4) ? 4:rarity;
		return fonts[rarity];
	}
	
	public static Color getColor(int rarity)
	{
		rarity = (rarity > 4) ? 4:rarity;
		return colors[rarity];
	}
	
	public static Item createRandomItem(int level)
	{
		/*
		 * Range 1 0>4
		 * Range 2 5>9
		 * Range 3 9>14
		 * Range 4 15>19
		 * Range 5 20>infinity
		 */
		Class<?>[] items;
		Class<?>[] itemRange1 = {SoulStone.class, CursedKnife.class, AdventurersSword.class};
		Class<?>[] itemRange2 = {SoulStone.class, CursedKnife.class, RustedShears.class, AdventurersSword.class, ElvenBow.class};
		Class<?>[] itemRange3 = {SoulStone.class, CursedKnife.class, RustedShears.class, PossessedSword.class, AdventurersSword.class, ElvenBow.class};
		Class<?>[] itemRange4 = {SoulStone.class, CursedKnife.class, RustedShears.class, PossessedSword.class, ShadowScythe.class, AdventurersSword.class, ElvenBow.class};
		Class<?>[] itemRange5 = {SoulStone.class, CursedKnife.class, RustedShears.class, PossessedSword.class, ShadowScythe.class, AdventurersSword.class, ElvenBow.class};
		if(level < 5)
		{
			items = itemRange1;
		} else if (level < 10)
		{
			items = itemRange2;
		} else if (level < 15)
		{
			items = itemRange3;
		} else if (level < 20)
		{
			items = itemRange4;
		} else
		{
			items = itemRange5;
		}
		int i = (int)(Math.random()*items.length);
		try {
			return (Item)items[i].newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Item createRandomItem()
	{
		Class<?>[] items = {SoulStone.class, CursedKnife.class, RustedShears.class, PossessedSword.class, ShadowScythe.class};
		int i = (int)(Math.random()*items.length);
		try {
			return (Item)items[i].newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setInfoWidth(int width) {
		this.infoWidth = width;
	}
	
	public void setInfoHeight(int height)
	{
		this.infoHeight = height+35;
	}
	
	public void setInfoOffset(int offset)
	{
		this.infoOffset = offset;
	}
	
	public int getInfoHeight()
	{
		return this.infoHeight;
	}

	public int getInfoWidth()
	{
		return this.infoWidth;
	}
	
	public int getInfoOffset()
	{
		return this.infoOffset;
	}
}
