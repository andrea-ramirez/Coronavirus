/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import static videogame.ReadandWrite.Load;
import static videogame.ReadandWrite.Saved;

/**
 *
 * @author Diego Garza y Andrea Ramírez
 */
public class Game implements Runnable {

    /**
     *
     * create variables
     */
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private LinkedList<Enemy> lista;  //to manage enemys
    private LinkedList<Shot> shots; // to manage shoots
    private int fontSize = 20;     //to store the font size
    private String score = "0";    //to store the score
    private int vidaActual = 5;     //to store the subset of lifes
    private String vidas;            //to store the number of lifes
    private boolean pause = false;   //flag to pause
    private int newVida = 0;       //avance a la nueva vida
    private int malosmuertos = 0;   //enemigos derrotados

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager(this);
    }

    /**
     * To get the player of the game
     *
     * @return an <code>Player</code> value
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        vidas = Integer.toString((int) (Math.random() * ((5 - 3) + 1)) + 3);
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2 - 50, getHeight() - 150, 1, 100, 100, this);
        display.getJframe().addKeyListener(keyManager);
        lista = new LinkedList<Enemy>();
        shots = new LinkedList<Shot>();
        int azar = (int) (Math.random() * ((10 - 8) + 1)) + 8;
        Assets.backSound.setLooping(true);
        Assets.backSound.play();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Enemy enemy = new Enemy(150 + 30 * j, 5 + 30 * i, 1, 20, 20, this, true);
                lista.add(enemy);
            }
        }

    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }

        stop();
    }

    /**
     * To get the KeyManager
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     *
     * play yey sound
     */
    public void yey() {
        Assets.yey.play();
    }

    /**
     *
     * play sneezing sound
     */
    public void sneeze() {
        Assets.sneeze.play();
    }
    
   public void shout() {
       Assets.trumpNo.play();
   }
    //esta funcion sirve para cargar el juego
    public void LoadP() {
        //abre el archivo
        ArrayList<String[]> datos = Load("juego.txt");
        //si hay algo vacia los buenos y los malos
        if (datos.size() > 0) {
            lista.clear();
            shots.clear();
            //recorre el archivo
            for (int i = 0; i < datos.size(); i++) {
                //si es la linea de vidas cambia la vida, score, enemigos vencidos y camino a la nueva vida
                if ("V".equals(datos.get(i)[0])) {
                    vidas = datos.get(i)[1];
                    score = datos.get(i)[2];
                    malosmuertos = Integer.parseInt(datos.get(i)[3]);
                    newVida = Integer.parseInt(datos.get(i)[4]);
                } //si es la linea de player cambia la poscicion
                else if ("P".equals(datos.get(i)[0])) {
                    player.setX(Integer.parseInt(datos.get(i)[1]));
                    player.setY(Integer.parseInt(datos.get(i)[2]));
                } //si es una linea de enemigo 
                else if ("E".equals(datos.get(i)[0])) {
                    //booleana para saber si elenemigo es visible
                    boolean visible;
                    if ("true".equals(datos.get(i)[6])) {
                        visible = true;
                    } else {
                        visible = false;
                    }
                    //crear el nuevo enemigo
                    Enemy en = new Enemy(Integer.parseInt(datos.get(i)[1]), Integer.parseInt(datos.get(i)[2]), Integer.parseInt(datos.get(i)[3]),
                            Integer.parseInt(datos.get(i)[4]), Integer.parseInt(datos.get(i)[5]), this, visible);
                    //booleana para checar si su drop esta visible
                    boolean dropVisible;
                    if ("true".equals(datos.get(i)[9])) {
                        dropVisible = true;
                    } else {
                        dropVisible = false;
                    }
                    //asignar si esta visible
                    en.drop.isVisible = dropVisible;
                    //cambiar x y y del drop
                    en.drop.y = Integer.parseInt(datos.get(i)[7]);
                    en.drop.x = Integer.parseInt(datos.get(i)[8]);
                    lista.add(en);
                    //cargar los tiros del usuario
                } else if ("S".equals(datos.get(i)[0])) {
                    //crear nuevo tiro
                    Shot s = new Shot(Integer.parseInt(datos.get(i)[1]), Integer.parseInt(datos.get(i)[2]), 30, 30, this);
                    boolean shotVisible;
                    //booleana para checar si esta visible
                    if ("true".equals(datos.get(i)[3])) {
                        shotVisible = true;
                    } else {
                        shotVisible = false;
                    }
                    s.visible = shotVisible;
                    shots.add(s);
                }
            }
        }
    }

    public void SaveP() {
        //guardar juego
        Saved("Juego.txt", Integer.parseInt(vidas), Integer.parseInt(score), lista, player, shots, malosmuertos, newVida);
    }

    public void PressSave() {
        //mandar a llamar funcion
        if (keyManager.save) {
            SaveP();
        }
    }

    public void PressLoad() {
        //mandar a llamar cargado de juego
        if (keyManager.load) {
            LoadP();
        }
    }

    //poner pausa al juego
    public void PressPause() {
        //poner pausa
        if (keyManager.pause) {
            pause = true;
            Assets.backSound.play();
        } else {
            //quitar pausa
            pause = false;

        }

    }
    /**
     *  Funcion para contar los tiros activos
     * @return la cantidad de tiros activos
     */
    public int countShoots() {
        //int para contar los tiros
        int counterShoot = 0;
        //recorrer los tiros y si estan activados sumarlos al int
        for (Shot shot : shots) {
            if (shot.visible) {
                counterShoot++;
            }
        }
        return counterShoot;
    }
    /**
     * Funcion para disparar
     * 
     * */
    public void shot() {
        //contar los tiros activos
        int counter = this.countShoots();
        //si hay menos de 3 tiros activos
        if (counter < 3) {
            //crear un tiro nuevo y agregarlo
            Shot shoot = new Shot((player.getX() + (player.getWidth() / 2)), player.getY(), 30, 30, this);
            shots.add(shoot);
            //activar efecto de sonido
            shout();
        }
    }
    /**
     * Funcion para parar juego con mensaje de derrota
     */
    private void Perdiste() {
        //imagen de derrota
        g.drawImage(Assets.trumpOver, player.x, player.y, 100, 100, null);
        //pantalla de derrota
        g.drawImage(Assets.fin, +getWidth() / 4, +100, getWidth() / 2, getHeight() / 2 - 30, null);
        //sonido de derrota empieza y cancion general para
        Assets.backSound.stop();
        Assets.loose.play();
    }

    /**
     *
     * tick game
     */
    private void tick() {
        if (!pause) {
            keyManager.tick();
            // avancing player with colision
            player.tick();
            PressLoad(); //checar si se carga
            PressSave(); //checar si se guarda
            PressPause(); //checar si se pone pausa
            //recorrer el array de tiros
            shots.stream().map((shot) -> {
                //si el tiro salio de la pantalla se toma como que desaparece
                if ((shot.getY() + shot.getHeight()) < 0) {
                    shot.visible = false;
                }
                return shot;
            }).map((shot) -> {
                //se filtra los enemigos que esten colisionando con el tiro , sean visibles y su enemigo sea visible
                lista.stream().filter((enemy) -> (enemy.collision(shot) && enemy.visible && shot.visible)).map((enemy) -> {
                    //se cambia el valor del tiro, enemigo a que desaparecen, se agrega un contador a 
                    shot.visible = false;
                    enemy.visible = false;
                    this.malosmuertos++;
                    //se reproduce sonido de eliminar enemigo
                    Assets.pop.play();
                    //se aumenta uno al contador para la proxima vida
                    newVida++;
                    //si el contador llega a 4
                    if (newVida == 4) {
                        //se aumenta una vida
                        vidas = Integer.toString(Integer.parseInt(vidas) + 1);
                        //se cambia el valor de vida actual restante al maximo
                        vidaActual = 6;
                        //se reinicia el contador para una nueva vida
                        newVida = 0;
                        //se reproduce el sonido de ganar una nueva vida
                        Assets.yey.play();
                    }
                    return enemy;
                    //se recorren los 
                }).forEachOrdered((_item) -> {
                    //se suma el valor del score
                    score = Integer.toString(Integer.parseInt(score) + 30);
                });
                return shot;
            }).filter((shot) -> (shot.visible)).forEachOrdered((shot) -> {
                //se mueven los tiros que son visibles
                shot.tick();
            });
            //se recorren los enemigos
            for (Enemy enemy : lista) {
                //se mueven
                enemy.tick();
                //si el jugador toda una drop delenemigo y esto esta visible
                if (player.collision(enemy.drop) && enemy.drop.isVisible && enemy.visible) {
                     //se cambia el proyectil del enemigo a falso
                    enemy.drop.isVisible = false;
                    //se reproduce el sonido de daño
                    sneeze();
                    //se resta uno a la vida actual
                    if (vidaActual > 0) {
                        vidaActual--;
                    } else {
                        //si ya no queda vida en la vida actual se resta uno a la vida general y se reseta la vida temporal
                        vidas = Integer.toString(Integer.parseInt(vidas) - 1);
                        vidaActual = 6;
                    }
                }
                //si el enemigo esta visible y toca con una pared todos los enemigos bajan uno lugar en y
                if (enemy.visible) {
                    if (enemy.getX() + 10 >= this.getWidth()) {
                        for (Enemy en : lista) {
                            en.setDirection(-1);
                            en.setY(en.getY() + 20);
                        }
                    } else if (enemy.getX() <= -10) {
                        for (Enemy en : lista) {
                            en.setDirection(1);
                            en.setY(en.getY() + 20);
                        }
                    }
                    if (enemy.getY() == this.getHeight() - 80) {
                        Perdiste();
                    }
                }
                // si el jugador toca un enemigo este pierde  una vida pero cuenta como enemigo derrotado
                if (player.collision(enemy) && enemy.visible) {
                    sneeze();
                    vidas = Integer.toString(Integer.parseInt(vidas) - 1);
                    vidaActual = 6;
                    enemy.visible = false;
                    malosmuertos++;
                }
            }
            //si te quedas sin vida se para el juego
            if (Integer.parseInt(vidas) <= 0) {
                render();
                running = false;
            }
            // si matas a todos los enemigos se para el juego
            if (malosmuertos == 24) {
                render();
                running = false;
            }
        } //manter el boton de p funcionando
        else {
            keyManager.pause();
            PressPause();
        }

    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
            g.setColor(Color.red);
            g.drawString("Vidas " + vidas, 10, 20);
            g.drawString("Score " + score, 110, 20);
            //se renderisan los enemigos visibles con sus respectivos drop
            lista.stream().filter((enemy) -> (enemy.visible)).map((enemy) -> {
                enemy.render(g);
                return enemy;
            }).forEachOrdered((enemy) -> {
                enemy.drop.render(g);
            });
            //se renderizan los tiros
            shots.forEach((shot) -> {
                shot.render(g);
            });
            //si se paro el juego por vidas se muestra la pantalla de perder
            if (Integer.parseInt(vidas) <= 0) {
                Perdiste();
            } //si se paro por matar a todos los enemigos se muestra la pantalla de victoria
            else if (malosmuertos == 24) {
                g.drawImage(Assets.ganaste, +getWidth() / 4, +100, getWidth() / 2, getHeight() / 2 - 30, null);
                Assets.backSound.stop();
                Assets.won.play();
            } else {
                player.render(g);
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }

    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {

        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }

}
