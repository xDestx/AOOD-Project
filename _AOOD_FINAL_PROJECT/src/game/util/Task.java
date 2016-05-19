package game.util;

import game.GameObject;

public class Task extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5187568930309589947L;
	
	private int tickDelay,currentTick;
	private boolean ran;
	
	public Task(int tickDelay) {
		this.tickDelay = tickDelay;
		currentTick = 0;
		ran = false;
	}

	@Override
	public void tick() {
		if(currentTick >= tickDelay)
		{
			run();
		}
		currentTick++;
	}
	
	
	public void run()
	{
		//To be overriden
	}
	
	public boolean done()
	{
		return tickDelay - currentTick + 1 <= 0;
	}

}
