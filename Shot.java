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
public class Shot extends Item {
    
    private int direction; // to store the direction
    private int width;  // to store the width
    private int height; // to store the height
    private Game game;  //to store the game
    public boolean visible; //flag to know if shot exists

    /**
     * To build a shot object
     *
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param direction an <code>int</code> value to get the direction
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game a <code>game</code> object to get outside elements
     */
    public Shot(int x, int y, int width, int height, Game game) {
        super(x, y, height, width);
        this.width = width;
        this.height = height;
        this.game = game;
        this.visible = true;
    }

    /**
     * To get the width of the shot
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the shot
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }



    @Override
    public void tick() {
        setY(getY() - 5);
    }

    @Override
    public void render(Graphics g) {
        if (this.visible) {
            g.drawImage(Assets.lysol, getX(), getY(), this.getWidth(), this.getHeight(), null);
        }
    }
}
