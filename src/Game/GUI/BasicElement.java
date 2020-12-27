package Game.GUI;

import Game.Paintable;

import java.awt.*;
import java.io.File;

public abstract class BasicElement extends Paintable {

    public String basepath = new File("").getAbsolutePath();

    public Rectangle elementArea;

    public BasicElement(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.elementArea = getBoundingBox();
    }

    public void paint(Graphics2D g2d) {
        g2d.draw(elementArea);
    }


}
