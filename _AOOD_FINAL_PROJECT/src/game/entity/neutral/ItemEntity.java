package game.entity.neutral;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import game.inventory.item.Item;
import game.util.Task;
import game.world.Location;

public class ItemEntity extends Collectible {

	private Item i;
	private boolean pickUp;
	private int coolTicks;
	private int despawnTicks;
	
	public ItemEntity(Location l, Item i) {
		super(l);
		this.i = i;
		despawnTicks = 2 * 60 * Game.TICK;
		//2 Minutes
		pickUp = true;
		coolTicks = 0;
		setBounds(new Rectangle((int)l.getX(),(int)l.getY(),50,50));
	}
	
	public ItemEntity(Location l, Item i, int coolTicks) {
		super(l);
		this.i = i;
		despawnTicks = 2 * 60 * Game.TICK;
		pickUp = true;
		this.coolTicks = coolTicks;
		setBounds(new Rectangle((int)l.getX(),(int)l.getY(),50,50));
	}

	@Override
	public void effect() {
		Game.getCurrentGame().getPlayer().getInventory().addItem(getItem());
		removeTask();
	}

	private void removeTask()
	{
		final Collectible adiosAmigo = this;
		Task t = new Task(0) {
			@Override
			public void run()
			{
				Game.getCurrentGame().getLevel().removeCollectible(adiosAmigo);
			}
		};
		Game.getCurrentGame().addTask(t);
	}
	
	@Override
	public void render(Graphics g, int xo, int yo) {
		g.drawImage(i.getIcon(), xo, yo, 50,50,null);
	}

	@Override
	public void tick() {
		//Check if player picks up
		despawnTicks--;
		if(despawnTicks <= 0)
		{
			removeTask();
		}
		if(coolTicks > 0)
		{
			coolTicks--;
			return;
		}
		if(Game.getCurrentGame().getPlayer().getBounds().intersects(getBounds()))
		{
			if(pickUp && !Game.getCurrentGame().getPlayer().getInventory().isFull())
				effect();
		}
	}
	
	public void setCanPickUp(boolean b)
	{
		this.pickUp = b;
	}
	
	public boolean getCanPickUp()
	{
		return this.pickUp;
	}
	
	public Item getItem()
	{
		return i;
	}

}
