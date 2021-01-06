package Game;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Paintable {

    public String ID; //All objects must be registered with ID
    public String MULTIPLAYER_ID;

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

    public void paintBox(Graphics2D g2d){
        g2d.draw(getBoundingBox());
    }

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

    public JSONObject toJSON(){
        JSONObject object = new JSONObject();
        object.put("G_ID", ID); //game id
        object.put("M_ID", MULTIPLAYER_ID); //multiplayer id
        object.put("xPos", x);
        object.put("yPos", y);
        return object;
    }

    public static Paintable createFromJSON(JSONObject object){
        return null;
    }


}
