package game.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game;
import game.GameState;

public class CMouseListener extends MouseAdapter {

	private Game g;
	
	public CMouseListener(Game g) {
		this.g = g;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(g.getState() == GameState.WORLD)
		{
			mousePressedWorld(e);
		}
	}

	
	private void mousePressedWorld(MouseEvent e)
	{
		if(!g.getPlayer().getInventory().isOpen())
		{
			g.getPlayer().attack();
		}
	}
	
}
