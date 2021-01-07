package Playgrounds;

import javax.swing.JFrame;

public class Playground2
{
    public static void main(String[] args)
    {

        JFrame frame = new JFrame();


        frame.setSize(1920,1080);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        PinballPanel panel = new PinballPanel();


        frame.add(panel);

        frame.setVisible(true);


    }
}
