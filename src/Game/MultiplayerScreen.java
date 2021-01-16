package Game;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MultiplayerScreen extends JPanel {

    public MultiplayerScreen(){

        JButton playButton = new JButton("Connect to Server!");
        add(playButton);



        //Needed after panel switch
        this.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentShown( ComponentEvent e ) {
                MultiplayerScreen.this.requestFocusInWindow();
            }
        });

    }





}
