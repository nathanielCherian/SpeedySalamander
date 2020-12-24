package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends Paintable{

    public BufferedImage backgroundTile;

    public Background(){
        super(0,0);

        try{
            _loadBackgroundTile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void _loadBackgroundTile() throws IOException {
        String base_path = new File("").getAbsolutePath();
        backgroundTile = ImageIO.read(new File(base_path+"\\src\\Game\\Resources\\Environment\\grass_background_256.png"));
    }


    public void paint(Graphics2D g2d) {
        g2d.drawImage(backgroundTile, 0, 0, null);
        g2d.drawImage(backgroundTile, 256, 0, null);
        g2d.drawImage(backgroundTile, 0, 256, null);
        g2d.drawImage(backgroundTile, 256, 256, null);

    }

}
