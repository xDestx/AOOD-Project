package game.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game;
import game.GameState;

public class CKeyListener extends KeyAdapter {

	private Game g;
	
	public CKeyListener(Game g)
	{
		this.g = g;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(g.getState() == GameState.WORLD)
		{
			keyPressedWorld(e);
		}
	}
	
	
	
	private void keyPressedWorld(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			g.getPlayer().getVelocity().setX(5);
		}
	}
	
	
}
