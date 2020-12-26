package Game.Objects;

import Game.Listeners.Listener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Coin extends AnimatedObject {


    public Coin(int x, int y) {
        super(x, y);
        setImageStates();
        this.ID = "COIN";

        this.isCollectable = true;
    }


    @Override
    public void setImageStates() {
        imageStates = new BufferedImage[9];
        this.totalStates = 18;

        try{
            File[] files = new File(basepath+"\\src\\Game\\Resources\\Objects\\Coin").listFiles();
            for(int i=0;i<files.length;i++){
                imageStates[i] =ImageIO.read(files[i]);
            }

            this.width = imageStates[0].getWidth();
            this.height = imageStates[0].getHeight();

        }catch (IOException e){
            e.printStackTrace();
        }

        //System.out.println(imageStates);
    }



}
