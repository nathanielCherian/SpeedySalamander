package Game;

import Game.Sounds.Sound;
import com.amazonaws.services.dynamodbv2.xspec.S;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    public static void main(String[] args){
        try{
            Home frame = new Home();
            frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    JPanel panelCont = new JPanel(new CardLayout());

    TitleScreen titleScreen = new TitleScreen();
    Board board = new Board();

    CardLayout cl = new CardLayout();


    public Home(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Game.WIDTH+18,Game.HEIGHT+42));

        panelCont.setLayout(cl);
        titleScreen.setCardLayout(cl, panelCont);
        panelCont.add(titleScreen, "1");
        panelCont.add(board, "2");
        cl.show(panelCont, "1");


        //add(new Board());


        add(panelCont);
        pack();
    }


}
