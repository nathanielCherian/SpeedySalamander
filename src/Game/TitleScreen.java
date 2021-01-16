package Game;

import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JPanel {

    CardLayout cl;
    JPanel panelCont;

    public TitleScreen(){

        setBackground(Color.GREEN);

        JButton playButton = new JButton("Play!");
        playButton.addActionListener(e -> {
            cl.show(panelCont, "2");
        });
        add(playButton);



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
