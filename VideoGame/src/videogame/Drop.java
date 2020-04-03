/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class Drop extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;
    

    /**
     * To build a Player object
     * @param x an int value to get the x coordinate
     * @param y an int value to get the y coordinate
     * @param direction an int value to get the direction
     * @param width an int value to get the width
     * @param height an int value to get the height
     * @param game a game object to get outside elements
     */
    public Drop(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, height, width);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int newx){
        this.x = newx;
    }

    public void setY(int newy){
        this.y = newy;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    @Override
    public void tick() {
        setY(getY() + 1);
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.drop, getX(), getY(), getWidth(), getHeight(), null);
    }
}