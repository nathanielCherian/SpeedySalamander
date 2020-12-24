package Game.Objects;

import Game.Paintable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class AnimatedObject extends Paintable {

    int state = 0;
    int totalStates;
    BufferedImage[] imageStates;
    public String basepath = new File("").getAbsolutePath();


    public AnimatedObject(int x, int y) {
        super(x, y);
    }

    public void setImageStates(){ }

    public void paint(Graphics2D g2d){
        state += 1;
        state %= totalStates;

        g2d.drawImage(imageStates[state/2], x, y, null);

    }

}
