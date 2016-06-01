package game.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Camera;
import game.Game;
import game.GameState;
import game.entity.Player;
import game.world.Location;

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
			if(g.getPlayer().getAttackStyle() == Player.ATTACK_RANGED)
			{
				Location l = new Location(e.getPoint());
				l.setX(l.getX()+g.getCamera().getLocation().getX());
				l.setY(l.getY()+g.getCamera().getLocation().getY());
				l.setX(l.getX()-g.getPlayer().getCenterLocation().getX());
				l.setY(l.getY()-g.getPlayer().getCenterLocation().getY());
				double radians = Math.atan(l.getX()/l.getY());
				if(l.getY() < 0)
					radians+=Math.PI;
			//	System.out.println(radians + "  " + p[0] + "   " + p[1] + "     " + e.getPoint().toString());
				g.getPlayer().attack(radians);
			} else
			{
				g.getPlayer().attack();
			}
		} else
		{
			g.getPlayer().getInventory().mouseClicked(e);
		}
	}
	
}
