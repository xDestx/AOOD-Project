package game.world;

import javax.swing.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.awt.Rectangle;

public class Level implements Serializable{
	
    private ArrayList<Collidable> listOfCollidables;
    private int id;
    
    public Level(int idNumber){
        listOfCollidables = new ArrayList<Collidable>();
        id = idNumber;
    }
    
    public int getId(){
        return id;
    }
    
    public ArrayList<Collidable> getListOfCollidables(){
        return listOfCollidables;
    }
    
    public void render(Rectangle bounds)
    { 
    }
    
}
