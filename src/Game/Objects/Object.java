package Game.Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Object {

    public int x;
    public int y;
    public int width;
    public int height;
    public String basepath = new File("").getAbsolutePath();
    public BufferedImage objectImage;

    public Object(int x, int y){
        this.x = x;
        this.y = y;
    }

    void setImage(String filePath){
        try{
            objectImage = ImageIO.read(new File(basepath+filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(objectImage, x, y, null);
    }


    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }
}
