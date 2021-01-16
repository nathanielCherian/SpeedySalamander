package Game;

import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JPanel {

    CardLayout cl;
    JPanel panelCont;
    JPanel newpanelCont;

    public TitleScreen(){

        setBackground(Color.GREEN);
        JButton instructionsButton = new JButton("Instructions");
        JButton singleplayerButton = new JButton("Single player");
        JButton multiplayerButton = new JButton("Multiplayer");
        JButton playButton = new JButton("Play!");
        playButton.addActionListener(e -> {
            cl.show(panelCont, "2");
        });
        instructionsButton.addActionListener(e -> {
            cl.show(panelCont, "2");
        });
        singleplayerButton.addActionListener(e -> {
            cl.show(panelCont, "2");
        });
        multiplayerButton.addActionListener(e -> {
            cl.show(panelCont, "2");
        });
        add(playButton);
        add(singleplayerButton);
        add(multiplayerButton);
        add(instructionsButton);

    }

    void setCardLayout(CardLayout cl, JPanel panelCont){
        this.cl = cl;
        this.panelCont = panelCont;
    }
}
