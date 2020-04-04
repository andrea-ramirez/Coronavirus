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

    private int direction;
    private Game game;
    private Animation animationLights;    //to store the animation for going left
    Drop drop;
    
    /**
     * To build an enemy object
     * @param x an int value to get the x coordinate
     * @param y an int value to get the y coordinate
     * @param direction an int value to get the direction
     * @param width an int value to get the width
     * @param height an int value to get the height
     * @param game a game object to get outside elements
     */
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        this.animationLights = new Animation(Assets.enemyLights, 100);
        drop = new Drop(this.x,this.y,1,10,10,game);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    

    public int getXDrop(){
        return drop.getX();
    }
    
    public int getYDrop(){
        return drop.getY();
    }
    
    public void setXDrop(int newx){
        this.drop.x = newx;
    }
    
    public void setYDrop(int newy){
        this.drop.y = newy;
    }
    

    @Override
    public void tick() {

        //updating animation
        this.animationLights.tick();
        
        //drop
        if(drop.isVisible){
            setYDrop(getYDrop() + 1);
        }else{
            setXDrop(getX());
            setYDrop(getY());
            int azar = (int) (Math.random() * ((10 - 1) + 1));
            if(azar == 1){
                this.drop.isVisible = true;
            }
        }
        
        if(getYDrop() >= game.getHeight() - 85){
            setXDrop(getX());
            setYDrop(getY());
            this.drop.isVisible = false;
        }
        
        
        // reset x position and y position if colision with wall
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
            direction = -1;
            setY(getY() +5);
        }
        else if (getX() <= -30) {
            setX(-30);
            direction = 1;
            setY(getY() +5);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
        
        //mover en eje x
        if(direction == 1){
            setX(getX()+ 2);
        }else{
            setX(getX() - 2);
        }
        
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationLights.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
