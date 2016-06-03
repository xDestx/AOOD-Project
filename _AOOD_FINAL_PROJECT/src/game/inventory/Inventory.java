package game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.GFrame;
import game.Game;
import game.entity.LivingEntity;
import game.entity.neutral.ItemEntity;
import game.inventory.item.EmptyItem;
import game.inventory.item.Item;
import game.world.Location;

public class Inventory {

	private boolean isOpen;
	private ArrayList<Item> items;
	private BufferedImage itemsImage;
	private boolean modifiable;
	private LivingEntity owner;
	
	public Inventory(LivingEntity owner) {
		isOpen = false;
		this.owner = owner;
		modifiable = true;
		items = new ArrayList<Item>();
		for (int i = 0; i < 20; i++)
			items.add(i, new EmptyItem());
		drawItemsImage();
	}
	
	public Inventory(LivingEntity owner, ArrayList<Item> items)
	{
		this.owner = owner;
		this.isOpen = false;
		this.modifiable = false;
		ArrayList<Item> temp = new ArrayList<Item>();
		for(int i = 0; i < 20; i++)
		{
			Item item = (i > items.size()-1) ? null:items.get(i);
			if (item == null)
					temp.add(new EmptyItem());
			else
				temp.add(items.get(i));
		}
		items = temp;
		this.items = items;
		drawItemsImage();
	}
	
	private void notifyChanged()
	{
		owner.inventoryUpdated();
	}
	
	public void setModifiable(boolean b)
	{
		this.modifiable = b;
	}
	
	public boolean getModifiable()
	{
		return this.modifiable;
	}
	
	public boolean isFull()
	{
		return this.getItems().size() >= 20;
	}
	
	private void drawItemsImage()
	{
		itemsImage = new BufferedImage(GFrame.WIDTH-40, GFrame.HEIGHT-40, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 20; i++) {
			// Do things here!
			int xo = 0;
			int yo = 0;
			int col = i % 5; // 0 1 2 3 4
			int row = i / 5; // 0 1 2 3
			itemsImage.getGraphics().drawImage(items.get(i).getIcon(), (int) (xo + (col * (GFrame.WIDTH - 40) / 5.0)),
					(int) (yo + (row * (GFrame.HEIGHT - 40) / 4.0)), (int) ((GFrame.WIDTH - 40) / 5.0),
					(int) ((GFrame.HEIGHT - 40) / 4.0), null);
		}
		itemsImage.getGraphics().dispose();
	}

	public void setOpen(boolean o) {
		this.isOpen = o;
		notifyChanged();
	}

	public boolean isOpen() {
		return isOpen;
	}
	
	public void addItem(Item item)
	{
		for (int i = 0; i < 20; i++)
		{
			if(items.get(i).getName().equals(""))
			{
				items.add(i, item);
				break;
			}
		}
		drawItemsImage();
		notifyChanged();
	}
	
	public Item removeItem(Item i)
	{
		int size1 = items.size();
		items.remove(i);
		if(size1 != items.size())
		{
			drawItemsImage();
		}
		notifyChanged();
		return i;
	}

	public Item removeItem(int pos)
	{
		Item i = items.get(pos);
		items.set(pos, new EmptyItem());
		drawItemsImage();
		notifyChanged();
		return i;
	}
	
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			//Assuming this is right click?
			if(!getModifiable())
				return;
			Point p = e.getPoint();
			int col = (int) ((p.getX()-20)/((GFrame.WIDTH - 40) / 5.0));
			int row = (int) ((p.getY()-5)/((GFrame.HEIGHT-40) / 4.0));
			if (col > 4 || row > 3)
				return;
			int v = row*5 + col;
			//Gonna place randomly on ground somehow
			Item adiosAmigo = removeItem(v);
			dropItem(adiosAmigo);
		}
		
	}
	
	public ArrayList<Item> getItems()
	{
		ArrayList<Item> items = new ArrayList<Item>();
		for(Item i : this.items)
		{
			if(i.getName().equals(""))
				continue;
			items.add(i);
		}
		return items;
	}
	
 	private void dropItem(Item i)
	{
		if (i.getName().equals(""))
			return;
		ItemEntity ie = new ItemEntity(new Location(Game.getCurrentGame().getPlayer().getLocation()), i, 3*Game.TICK);
		Game.getCurrentGame().getLevel().addCollectible(ie);
		notifyChanged();
	}
	
	/*
	 * Fixed position
	 */
	public void render(Graphics g) {
		if(!isOpen)
			return;
		Color last = g.getColor();
		g.setColor(new Color(0, 0, 0, 127));
		g.fillRect(10, 0, GFrame.WIDTH - 20, GFrame.HEIGHT - 10);
		g.drawImage(itemsImage, 20, 10, null);
		g.setColor(Color.gray);
		g.drawRect(20, 10, GFrame.WIDTH - 40, GFrame.HEIGHT - 40);
		

		// Vertical lines
		for (int i = 0; i < 5; i++) {
			double xo = 20 + ((i + 1) * ((GFrame.WIDTH - 40) / 5.0));
			g.drawLine((int) xo, 10, (int) xo, GFrame.HEIGHT - 10);
		}
		// Horizontal lines
		for (int i = 0; i < 4; i++) {
			double yo = 10 + ((i + 1) * ((GFrame.HEIGHT - 40) / 4.0));
			g.drawLine(20, (int) yo, GFrame.WIDTH - 20, (int) yo);
		}
		g.setColor(last);
	}

}
