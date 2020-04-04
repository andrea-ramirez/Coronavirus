/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image

        public static BufferedImage fin;// to store the gameOver image
    public static BufferedImage drop;   // to store the drop image
    public static BufferedImage lysol;  // to store the shot image
    public static BufferedImage ganaste;    // to store the winner image
    public static SoundClip backSound;  // to store the background music
    public static SoundClip sneeze; // to store the sneezing sound
    public static SoundClip yey;    // to store the yey sound
    public static SoundClip won;    // to store the winner sound
    public static SoundClip loose;  // to store the loser sound
    public static SoundClip trumpNo;    // to store trump saying no sound
    public static SoundClip pop;    // to store the pop sound
    
    //animation of trump
    public static BufferedImage sprites;        //to store the sprites
    public static BufferedImage playerLeft[];       //pictures to go left
    public static BufferedImage playerRight[];      //pictures to go right
    public static BufferedImage playerStanding[];   //pictures to stay standing
    public static BufferedImage spritesOver;    //to store the sprite for trump when gaeme is over
    public static BufferedImage trumpOver;      //picture when game is over

    //animation de coronavirus
    public static BufferedImage spritesEnemy;       //to store the sprites of coronavirus
    public static BufferedImage enemyLights[];        //pictures for coronavirus to light up

    /**
     * initializing the images and sounds of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/hospital.png");
        player = ImageLoader.loadImage("/images/trump.png");
        fin = ImageLoader.loadImage("/images/gameOver.png");
        drop = ImageLoader.loadImage("/images/drop.png");
        lysol = ImageLoader.loadImage("/images/lysol.png");
        ganaste = ImageLoader.loadImage("/images/ganaste.png");
        backSound = new SoundClip("/sounds/back.wav");
        sneeze = new SoundClip("/sounds/sneeze.wav");
        yey = new SoundClip("/sounds/bueno.wav");
        won = new SoundClip("/sounds/won.wav");
        loose = new SoundClip("/sounds/perdiste.wav");
        trumpNo = new SoundClip("/sounds/trumpNo.wav");
        pop = new SoundClip("/sounds/pop.wav");

        //getting the player sprites from the picture
        sprites = ImageLoader.loadImage("/images/trump.png");
        //creating array of images before animations of player
        SpreadSheet spreadsheet = new SpreadSheet(sprites);
        playerStanding = new BufferedImage[6];
        playerRight = new BufferedImage[6];
        playerLeft = new BufferedImage[6];
        // croping the pictures from the sheet into the array
        for (int i = 0; i < 6; i++) {
            playerStanding[i] = spreadsheet.crop(i * 100, 0, 100, 100);
            playerRight[i] = spreadsheet.crop(i * 100, 100, 100, 100);
            playerLeft[i] = spreadsheet.crop(i * 100, 300, 100, 100);
        }
        spritesOver = ImageLoader.loadImage("/images/trumpOver.png");
        SpreadSheet spreadsheetOver = new SpreadSheet(spritesOver);
        trumpOver = spreadsheetOver.crop(300, 0, 100, 100);

        //getting the coronavirus sprites from the picture
        spritesEnemy = ImageLoader.loadImage("/images/corona.png");
        //creating array of images before animations of enemy
        SpreadSheet sEnemy = new SpreadSheet(spritesEnemy);
        enemyLights = new BufferedImage[2];
        // croping the pictures from the sheet into the array
        enemyLights[0] = sEnemy.crop(0, 0, 256, 248);
        enemyLights[1] = sEnemy.crop(256, 0, 256, 248);
    }

}
