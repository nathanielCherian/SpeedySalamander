package Game;

import Game.Entities.Player;
import Game.GUI.Border;
import Game.GUI.HealthBar;
import Game.Multiplayer.Client;
import Game.Multiplayer.TempClientListener;
import Game.Objects.Coin;
import Game.Objects.SmallRocks;
import Game.Objects.ThornBush;
import Game.Objects.Tree;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;

public class Board extends JPanel {

    public int PLAYER_ID = 1;

    public int TICK_SPEED = 50;
    Player player = new Player(PLAYER_ID);

    public boolean MULTIPLAYER_ENABLED = true;
    Client client = new Client(); // Daemon Thread not started yet

    Scene scene = new Scene();



    public Board(){

        scene.setClient(client);
        if(MULTIPLAYER_ENABLED){
            client.setPLAYER_ID(PLAYER_ID);
            client.setInitialSceneListener(scene.initialSceneListener);
            client.startClient("127.0.0.1", 8888, new TempClientListener());
        }

        packScene();


        timer.start();


        addKeyListener(new GameKeyListener());
        setFocusable(true);




        //Needed after panel switch
        this.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentShown( ComponentEvent e ) {
                Board.this.requestFocusInWindow();
            }
        });

    }


    void packScene(){

        //add Background
        scene.addBackground(new Background());
        scene.addPlayer(player);

        if(!MULTIPLAYER_ENABLED){ //THIS IS TEMP
            //Add objects
            scene.add(new Tree(100,100));
            scene.add(new Tree(140,100));
            scene.add(new SmallRocks(250,200));
            scene.add(new ThornBush(50,50));
            scene.add(new Coin(300,300));

        }



        //Add GUI Elements
        Border b = new Border();
        scene.addGUIElement(b);
        HealthBar hb = new HealthBar(25, 75);
        scene.addGUIElement(hb);


        //register listeners for scene
        player.addCoinCollectListener(scene.coinCollected);
        player.addDamageTakenListener(hb.decreaseHealthBar);
        player.addDamageTakenListener(b.flashDamageBorder);
    }




    protected Timer timer = new Timer(TICK_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            scene.purgeChildren(); //Clearing deleted objects
            scene.addQueuedChildren(); //Adding objects waiting in queue

            player.digest_keys(pressed_keys); //controls player action
            player.checkCollisions(scene.children);


            repaint();
        }
    });


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        scene.paint(g2d);



    }



    void createBorder(Graphics2D g2d){
        //random color for border
        Random rand = new Random();
        g2d.setColor(new Color(0, 0, 0));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(2,2,510, 510);
        //border creation
    }


    Set<Integer> pressed_keys = new HashSet<>();
    public class GameKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public synchronized void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == 87) { // KEY: W
                pressed_keys.add(e.getKeyCode());
                //s.state = "NORTH";
            } else if (e.getKeyCode() == 83) { // KEY: S
                pressed_keys.add(e.getKeyCode());
                //s.state = "SOUTH";

            } else if (e.getKeyCode() == 68) { // KEY: D
                pressed_keys.add(e.getKeyCode());
                //s.state = "EAST";

            } else if (e.getKeyCode() == 65) { // KEY: A
                pressed_keys.add(e.getKeyCode());
                //s.state = "WEST";
            } else if (e.getKeyCode() == 16) { //KEY: SHIFT
                pressed_keys.add((e.getKeyCode()));
            }

        }
        @Override
        public void keyReleased(KeyEvent e) {
            pressed_keys.remove(e.getKeyCode());
        }

    }
}
