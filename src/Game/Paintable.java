package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Paintable {

    public String ID; //All objects must be registered with ID

    public int x;
    public int y;
    public int width;
    public int height;

    public Boolean toDelete = false;

    public Boolean isSolid = false;
    public Boolean isCollectable = false;

    public Paintable(){
        //Empty constructor
    }

    public Paintable(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g2d){}

    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }


    public String basepath = new File("").getAbsolutePath();
    public BufferedImage loadImage(String filePath){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(basepath+filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

}
