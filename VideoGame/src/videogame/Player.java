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
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    //animation
    private Animation animationLeft;    //to store the animation for going left
    private Animation animationRight;   //to store the animation for going right
    private Animation animationStanding;//to store the animation for staying still

    /**
     * To build a Player object
     * @param x an int value to get the x coordinate
     * @param y an int value to get the y coordinate
     * @param direction an int value to get the direction
     * @param width an int value to get the width
     * @param height an int value to get the height
     * @param game a game object to get outside elements
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, height, width);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        
        

        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        this.animationStanding = new Animation(Assets.playerStanding, 100);
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
        // moving player depending on flags
        if (game.getKeyManager().left) {
           setX((getX() - 4)*direction);
           //updating animation
           this.animationLeft.tick();
        }else if (game.getKeyManager().right) {
            this.animationRight.tick();
            //updating animation
            setX((getX() + 4)*direction);
        }else{
            //updating animation
            this.animationStanding.tick();
        }
        
        // reset x position and y position if colision with wall
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }

    }

    @Override
    public void render(Graphics g) {
        if (game.getKeyManager().left) {
           g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else if (game.getKeyManager().right) {
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else{
            g.drawImage(animationStanding.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
        
    }
}

