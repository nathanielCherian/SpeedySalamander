package Game.Objects;

import Game.Paintable;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ImageObject extends Paintable {


    public BufferedImage objectImage;

    public ImageObject(int x, int y) {
        super(x, y);
    }
    public ImageObject(JSONObject object) {
        super(object);
    }


    void setImage(String filePath){
        try{
            objectImage = ImageIO.read(new File(basepath+filePath));
            this.width = objectImage.getWidth();
            this.height = objectImage.getHeight();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(objectImage, x, y, null);
    }


}
