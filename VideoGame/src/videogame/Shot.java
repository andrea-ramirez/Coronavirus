/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author diego
 */
public class Shot extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;
    public boolean visible;

    /**
     *
     * @param width
     * @param height
     * @param game
     * @param player
     */
    public Shot(int x, int y, int width, int height, Game game) {
        super(x, y, height, width);
        this.width = width;
        this.height = height;
        this.game = game;
        this.visible = true;
    }

    public int getWidth() {
        return width;
    }

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
