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
public class Enemy extends Item {

    private int direction;  // to store the direction
    private Game game;  // to store the game
    private Animation animationLights;    //to store the animation for coronavirus to light up
    Drop drop;  //// to store the drop
    public boolean visible = false;

    /**
     * To build an enemy object
     *
     * @param x an <code>int</code> value to get the x coordinate
     * @param y an <code>int</code> value to get the y coordinate
     * @param direction an <code>int</code> value to get the direction
     * @param width an <code>int</code> value to get the width
     * @param height an <code>int</code> value to get the height
     * @param game a <code>game</code> object to get outside elements
     */
    public Enemy(int x, int y, int direction, int width, int height, Game game, boolean visible) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.visible = visible;
        this.animationLights = new Animation(Assets.enemyLights, 100);
        drop = new Drop(this.x, this.y, 1, 10, 10, game);
    }

    /**
     * To get the direction of the enemy
     *
     * @return an <code>int</code> value with the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * To set direction of enemy
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * To get the enemy's x drop
     *
     * @return an <code>int</code> value with the x
     */
    public int getXDrop() {
        return drop.getX();
    }

    /**
     * To get the enemy's y drop
     *
     * @return an <code>int</code> value with the y
     */
    public int getYDrop() {
        return drop.getY();
    }

    /**
     * To set the enemy's x drop
     *
     * @param newx
     */
    public void setXDrop(int newx) {
        this.drop.x = newx;
    }

    /**
     * To set the enemy's y drop
     *
     * @param newy
     */
    public void setYDrop(int newy) {
        this.drop.y = newy;
    }

    @Override
    public void tick() {

        //updating animation
        this.animationLights.tick();

        //drop
        if (drop.isVisible) {
            setYDrop(getYDrop() + 2);
        } else {
            setXDrop(getX());
            setYDrop(getY());
            //to let the drops fall randomly
            int azar = (int) (Math.random() * ((10 - 1) + 1));
            if (azar == 1) {
                this.drop.isVisible = true;
            }
        }

        //for drops to destroy once they hit the floor
        if (getYDrop() >= game.getHeight() - 85) {
            setXDrop(getX());
            setYDrop(getY());
            this.drop.isVisible = false;
        }

        // reset x position and y position if colision with wall
        //mover en eje x
        if (direction == 1) {
            setX(getX() + 2);
        } else {
            setX(getX() - 2);
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationLights.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
