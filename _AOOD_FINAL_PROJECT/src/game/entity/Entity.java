package game.entity;

import java.awt.Graphics;

import game.GameObject;
import game.Renderable;


public abstract class Entity extends GameObject implements Renderable
{

    public abstract void render(Graphics g, int xo, int yo);
    
}
