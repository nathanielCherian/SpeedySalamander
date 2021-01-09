package Game;

import Game.Multiplayer.Client;
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

    //Painters
    public void paint(Graphics2D g2d){}
    public void paintBox(Graphics2D g2d){
        g2d.draw(getBoundingBox());
    }

    //Shape
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }



    //File Loaders
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


    //JSON's
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


    //Linking Client object to all inherited objects
    private Client client;
    private Client.ClientUpdateListener clientUpdateListener;
    public void setClient(Client client) {
        this.client = client;
        this.clientUpdateListener = client.clientUpdateListener; //linking listener
    }

    public void onCreate(){
        clientUpdateListener.onCreate(this);
    }

    public void onDelete(){
        toDelete = true;
        clientUpdateListener.onDelete(this);
    }

    public void onChange(){
        clientUpdateListener.onChange(this);
    }


}
