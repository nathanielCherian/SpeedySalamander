package Game;

import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JPanel {

    CardLayout cl;
    JPanel panelCont;

    private Board board;
    public void setBoard(Board board){
        this.board = board;
    }


    public TitleScreen(){

        setBackground(Color.GREEN);
        setLayout(new FlowLayout());

        JButton playButton = new JButton("Play Singleplayer!");
        playButton.addActionListener(e -> {
            board.initializeBoard();
            cl.show(panelCont, "2");
        });
        add(playButton);


        JTextField ipField = new JTextField("76.176.58.233");
        ipField.addActionListener(e->{
            String ip = ipField.getText();
            if(ip != null){
                board.setMultiplayerData(ip, 8888);
                board.initializeBoard();
                cl.show(panelCont, "2");
            }
        });
        add(ipField);



        /*
        JButton multiplayerButton = new JButton("Multiplayer Button!");
        playButton.addActionListener(e -> {
            cl.show(panelCont, "3");
        });
        add(multiplayerButton);
         */
    }

    void setCardLayout(CardLayout cl, JPanel panelCont){
        this.cl = cl;
        this.panelCont = panelCont;
    }
}
