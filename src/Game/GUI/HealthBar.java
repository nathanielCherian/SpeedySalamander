package Game.GUI;

import Game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HealthBar extends BasicElement{

    BufferedImage heart;
    int heartCount = 3;


    //Constrained to the bottom left
    public HealthBar(int leftX, int bottomY) {
        super(leftX, Game.HEIGHT-bottomY, 200,50);
        heart = loadImage("\\src\\Game\\Resources\\GUI\\heart.png");
    }


    public void paint(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.black);
        g2d.fill(elementArea);

        for(int i=0;i<heartCount;i++){
            int y = (this.y+(this.height - heart.getHeight())/2);
            g2d.drawImage(heart, this.x+(20*(i+1))+(i*heart.getWidth()), y, null);
        }


    }


}
