/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videogame;

import java.awt.image.BufferedImage;
/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage fin;
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static SoundClip ow;
    public static SoundClip yey;
    
    
    //animation
    public static BufferedImage sprites;        //to store the sprites
    public static BufferedImage playerLeft[];       //pictures to go left
    public static BufferedImage playerRight[];      //pictures to go right
    public static BufferedImage playerStanding[];   //pictures to stay standing
    
    //animation de los buenos
    public static BufferedImage spritesBuenos;      //to store sprites of Buenos
    public static BufferedImage buenosRight[];      //pictures for buenos to go right
    
    //animation de enemy
    public static BufferedImage spritesEnemy;       //to store the sprites of enemy
    public static BufferedImage enemyLights[];        //pictures for enemy to go left

    /**
     * initializing the images and sounds of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/hospital.png");
        player = ImageLoader.loadImage("/images/trump.png");
        fin = ImageLoader.loadImage("/images/seacabo.jpg");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        ow = new SoundClip("/sounds/preview.wav");
        yey = new SoundClip("/sounds/bueno.wav");
        
        //getting the player sprites from the picture
        sprites = ImageLoader.loadImage("/images/trump.png");
        //creating array of images before animations of player
        SpreadSheet spreadsheet = new SpreadSheet(sprites);
        playerStanding = new BufferedImage[6];
        playerRight = new BufferedImage[6];
        playerLeft = new BufferedImage[6];
        // croping the pictures from the sheet into the array
        for(int i = 0; i < 6; i++){
            playerStanding[i] = spreadsheet.crop(i*100,0,100,100);
            playerRight[i] = spreadsheet.crop(i*100,100,100,100);
            playerLeft[i] = spreadsheet.crop(i*100,300,100,100);
        }
        
        //getting the sprites from the picture
        spritesBuenos = ImageLoader.loadImage("/images/mariposa.png");
        //creating array of images before animations of buenos
        SpreadSheet sBuenos = new SpreadSheet(spritesBuenos);
        buenosRight = new BufferedImage[14];
        // croping the pictures from the sheet into the array
        for(int i = 0; i < 14; i++){
            buenosRight[i] = sBuenos.crop(i*70,0,70,70);
        }
        
        /**
        //getting the sprites from the picture
        spritesEnemy = ImageLoader.loadImage("/images/dragon.png");
        //creating array of images before animations of enemy
        SpreadSheet sEnemy = new SpreadSheet(spritesEnemy);
        enemyLeft = new BufferedImage[11];
        // croping the pictures from the sheet into the array
        for(int i = 0; i < 11; i++){
            enemyLeft[i] = sEnemy.crop(i*2626/11,1435-(2626/11),2626/11,2626/11);
        }
        * */
        
        
        //getting the coronavirus sprites from the picture
        spritesEnemy = ImageLoader.loadImage("/images/corona.png");
        //creating array of images before animations of enemy
        SpreadSheet sEnemy = new SpreadSheet(spritesEnemy);
        enemyLights = new BufferedImage[2];
        // croping the pictures from the sheet into the array
        enemyLights[0] = sEnemy.crop(0,0,256,248);
        enemyLights[1] = sEnemy.crop(256,0,256,248);
    }

}
