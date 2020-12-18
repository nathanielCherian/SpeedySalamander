package Game;

import Server.Client;
import org.json.simple.JSONObject;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board extends JPanel {

    public int TICK_SPEED = 50;

    public int PLAYER_ID = 1;
    Player player = new Player(PLAYER_ID);
    Client client = new Client(PLAYER_ID, "76.176.58.233", 8888);

    HashMap<Integer, ExternalPlayer> externalPlayers = new HashMap<>();

    public Board(){

        timer.start();

        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }


    protected Timer timer = new Timer(TICK_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            player.digest_keys(pressed_keys); //controls player action

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

            repaint();
        }
    });


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.paint(g2d);

        for(ExternalPlayer ep: externalPlayers.values()){
            ep.paint(g2d);
        }
        Random rand = new Random();
        g.setColor(new Color(rand.nextInt(1), rand.nextInt(255), rand.nextInt(255)));
        g2d.setStroke(new BasicStroke(3));
        g.drawRect(2,0,600, 600);
    }

    Set<Integer> pressed_keys = new HashSet<>();
    public class GameKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public synchronized void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == 87){ // KEY: W
                pressed_keys.add(e.getKeyCode());
                //s.state = "NORTH";

            }else if(e.getKeyCode() == 83){ // KEY: S
                pressed_keys.add(e.getKeyCode());
                //s.state = "SOUTH";

            }else if(e.getKeyCode() == 68){ // KEY: D
                pressed_keys.add(e.getKeyCode());
                //s.state = "EAST";

            }else if(e.getKeyCode() == 65){ // KEY: A
                pressed_keys.add(e.getKeyCode());
                //s.state = "WEST";

            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            pressed_keys.remove(e.getKeyCode());
        }

    }
}
