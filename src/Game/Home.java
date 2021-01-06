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

    public Home(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Game.WIDTH+18,Game.HEIGHT+42));

        add(new Board());

        pack();
    }
}
