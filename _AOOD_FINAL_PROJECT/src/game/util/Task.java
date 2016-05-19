package game.util;

import game.GameObject;

public class Task extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5187568930309589947L;
	
	private int tickDelay,currentTick;
	
	
	public Task(int tickDelay) {
		this.tickDelay = tickDelay;
		currentTick = 0;
	}

	@Override
	public void tick() {
		if(currentTick == tickDelay)
			run();
		currentTick++;
	}
	
	
	public void run()
	{
		//To be overriden
	}
	
	public boolean done()
	{
		return tickDelay - currentTick <= 0;
	}

}
