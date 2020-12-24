package Game;

import Game.Entities.ExternalPlayer;
import Game.Entities.Player;
import Game.Objects.Coin;
import Game.Objects.Tree;
import Game.Server.Client;
import com.sun.jdi.ThreadReference;
import org.json.simple.JSONObject;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel {

    public int PLAYER_ID = 1;

    public int TICK_SPEED = 50;
    Player player = new Player(PLAYER_ID);

    public boolean MULTIPLAYER_ENABLED = false;
    Client client;

    Scene scene = new Scene();


    HashMap<Integer, ExternalPlayer> externalPlayers = new HashMap<>();


    public Board(){

        timer.start();

        if(MULTIPLAYER_ENABLED){
            client = new Client(PLAYER_ID, "76.176.58.233", 8888);
        }


        packScene();

        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }


    void packScene(){
        Background background = new Background();
        scene.add(background);
        scene.add(new Tree(100,100));
        scene.add(new Tree(140,100));
        scene.add(new Coin(300,300));

    }


    void updatePlayers(){
        JSONObject server_data = client.makeRequest(player.getJSON());
        System.out.println(server_data.keySet());
        for(Object player_id_ : server_data.keySet()){
            int ep_id;
            if((ep_id = Integer.parseInt((String) player_id_)) != PLAYER_ID){

                if(externalPlayers.containsKey(ep_id)){
                    externalPlayers.get(ep_id).setFromJSON((JSONObject) server_data.get(player_id_));
                }else{
                    ExternalPlayer ep = new ExternalPlayer(ep_id);
                    ep.setFromJSON((JSONObject) server_data.get(player_id_));
                    externalPlayers.put(ep_id, ep);
                }
            }
        }
    }


    protected Timer timer = new Timer(TICK_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            player.digest_keys(pressed_keys, scene.children); //controls player action


            if(MULTIPLAYER_ENABLED){
                updatePlayers();
            }


            repaint();
        }
    });


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        scene.paint(g2d);

        player.paint(g2d); //paint player

        for(ExternalPlayer ep: externalPlayers.values()){ //paint any other players
            ep.paint(g2d);
        }


        createBorder(g2d);
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
