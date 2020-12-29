package Game.GUI;

import Game.Game;
import Game.Listeners.DamageTakenListener;

import java.awt.*;

public class Border extends BasicElement{
    public Border() {
        super(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    public String STATE = "NORMAL";
    int flashTime = 0;

    public FlashDamageBorder flashDamageBorder = new FlashDamageBorder();

    public void paint(Graphics2D g2d){

        if(STATE == "NORMAL"){
            drawBorder(g2d);

        }else if(STATE == "DAMAGE" && flashTime <= 10){
            drawDamageBorder(g2d);
            flashTime++;
        }else{
            flashTime = 0;
            STATE = "NORMAL";
        }

    }

    void drawBorder(Graphics2D g2d){
        g2d.setColor(new Color(0, 0, 0));
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(elementArea);
    }

    void drawDamageBorder(Graphics2D g2d){
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(30));
        g2d.draw(elementArea);
    }

    private class FlashDamageBorder implements DamageTakenListener{
        @Override
        public void onDamageTaken(int damageTaken) {
            STATE = "DAMAGE";
            flashTime = 0;
        }
    }



}
