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

    private int direction; // to store the direction
    private int width;  // to store the width
    private int height; // to store the height
    private Game game;  //to store the game
    public boolean isVisible; //flag to know if drop exists
    private int azar;
    

    /**
     * To build a Drop object
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param direction an <code>int</code> value to get the direction
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game a <code>game</code> object to get outside elements
     */
    public Drop(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, height, width);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.isVisible = false;
        this.azar = (int) (Math.random() * ((3 - 1) + 1));
        
    }
    
    /**
     * To get the direction of the drop
     * @return an <code>int</code> value with the direction
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * To get the width of the drop
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the drop
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * To get the x of the drop
     * @return an <code>int</code> value with the x
     */
    public int getX(){
        return x;
    }
    
    /**
     * To get the y of the drop
     * @return an <code>int</code> value with the y
     */
    public int getY(){
        return y;
    }
    
    /**
     * To set x of drop
     * @param newx 
     */
    public void setX(int newx){
        this.x = newx;
    }

    /**
     * To set y of drop
     * @param newy 
     */
    public void setY(int newy){
        this.y = newy;
    }
    
    /**
     * To set direction of drop
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * To set width of drop
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * To set height of drop
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    
    @Override
    public void tick() {
        //update is in enemy
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.drop, getX(), getY(), getWidth(), getHeight(), null);
    }
}