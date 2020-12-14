package Game;

import Server.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Board extends JPanel {

    public int TICK_SPEED = 50;

    Player player = new Player(1);
    Client client = new Client(1, "192.168.1.22");


    public Board(){




        timer.start();

        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }


    protected Timer timer = new Timer(TICK_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            player.digest_keys(pressed_keys); //controls player action

            System.out.println(client.makeRequest(player.getJSON()));

            repaint();
        }
    });


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.paint(g2d);
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
