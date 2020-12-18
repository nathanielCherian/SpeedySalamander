package Game;

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
        setPreferredSize(new Dimension(621,644));

        add(new Board());

        pack();
    }


}