/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class ReadandWrite {

    //use it to save game
    public static void Saved(String strFileName, int vidas, int score, LinkedList<Enemy> enemies, Player player, LinkedList<Shot> shot, int muertos, int vidaP) {
        try {
            //write every  enemy, score, goodgu, player required attributes the first letter its a identifier
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            writer.println("V" + "/" + vidas + "/" + score + "/" + muertos + "/" + vidaP);
            writer.println("P" + "/" + player.getX() + "/" + player.getY());
            for (Enemy enemy : enemies) {
                writer.println("E" + "/" + enemy.getX() + "/" + enemy.getY() + "/" + enemy.getDirection() + "/" + enemy.getHeight() + "/" + enemy.getWidth()
                        + "/" + enemy.visible + "/" + enemy.drop.getY() + "/" + enemy.drop.getX() + "/" + enemy.drop.isVisible);
            }
            for (Shot sh : shot) {
                writer.println("S" + "/" + sh.getX() + "/" + sh.getY() + "/" + sh.visible);
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }

    }

    //use it to load game
    public static ArrayList<String[]> Load(String strFileName) {
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            //load document and to a 2d array with each required attribute
            while ((line = reader.readLine()) != null) {
                datos = line.split("/");
                data.add(datos);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
        return data;
    }
}
