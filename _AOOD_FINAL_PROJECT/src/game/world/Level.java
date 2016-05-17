package game.world;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.GFrame;
import game.Game;
import game.GameObject;
import game.Renderable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Level implements Renderable {
	
	//To implement serializable later
	
    private ArrayList<Collidable> listOfCollidables;
    private int id;
    private BufferedImage bi;
    private Location l;
    
    public Level(int idNumber){
        listOfCollidables = new ArrayList<Collidable>();
        id = idNumber;
        l = new Location(0,0);
        try
        {
        	bi = ImageIO.read(getClass().getResourceAsStream("/images/grass.png"));
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    public Level(int idNumber, BufferedImage b){
        listOfCollidables = new ArrayList<Collidable>();
        id = idNumber;
        bi = b;
    }
    
    public int getId(){
        return id;
    }
    
    public ArrayList<Collidable> getListOfCollidables(){
        return listOfCollidables;
    }

	@Override
	public void render(Graphics g, int x, int y) {
		if(bi!=null)
		{
			int startX = 0-((int)Game.getCurrentGame().getCamera().getLocation().getX()%bi.getWidth())-bi.getWidth();
			int startY = 0-((int)Game.getCurrentGame().getCamera().getLocation().getY()%bi.getHeight())-bi.getHeight();
			int cX = startX;
			int cY = startY;
			while (cX < GFrame.WIDTH || cY < GFrame.HEIGHT)
			{
				g.drawImage(bi, cX, cY, null);
				cX+=bi.getWidth();
				if(cX >= GFrame.WIDTH)
				{
					cY+=bi.getWidth();
					if(cY >= GFrame.HEIGHT)
					{
						break;
					} else
					{
						cX=startX;
					}
				}
			}
		}
		
		//Draw WorldObjects
		
	}


	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return l;
	}


    
   
    
}
