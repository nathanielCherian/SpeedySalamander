package Game;

import java.awt.*;

public class Paintable {

    public static String ID;

    public int x;
    public int y;
    public int width;
    public int height;

    public Boolean toDelete = false;

    public Boolean isSolid = false;
    public Boolean isCollectable = false;

    public Paintable(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g2d){}

    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }
}
