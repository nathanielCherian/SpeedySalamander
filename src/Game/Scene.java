package Game;

import Game.Entities.Player;
import Game.GUI.BasicElement;
import Game.Listeners.CoinCollectListener;
import Game.Objects.Coin;
import Game.Sounds.Sound;
import com.amazonaws.services.dynamodbv2.xspec.S;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Scene{

    public int x;
    public int y;

    public ArrayList<Paintable> children = new ArrayList<>();
    public ArrayList<Paintable> childrenQueue = new ArrayList<>(); //children waiting for queue to finish to be added

    public Player player;
    public Paintable background;
    public ArrayList<BasicElement> guiElements = new ArrayList<>();


    //Listeners
    public CoinCollected coinCollected = new CoinCollected();


    boolean DEBUG = false;

    public Scene(){

    }


    public void add(Paintable o){
        children.add(o);
    } //adding children directly
    //adding children while still in game loop, avoid concurrent modification error
    public void addToWaitingList(Paintable o){ childrenQueue.add(o); }

    public void addPlayer(Player p){
        player = p;
    }
    public void addBackground(Paintable o){
        background = o;
    }
    public void addGUIElement(BasicElement element){
        guiElements.add(element);
    }


    public void paint(Graphics2D g2d){

        //All paintables must be drawn in this order

        background.paint(g2d);

        for(Paintable child: children){
            child.paint(g2d);

            if (DEBUG){
                child.paintBox(g2d);
            }
        }

        player.paint(g2d);
        if (DEBUG){
            player.paintBox(g2d);
        }

        for(BasicElement element: guiElements){
            element.paint(g2d);

            if (DEBUG){
                element.paintBox(g2d);
            }
        }

    }

    //Periodically call to update scene
    public void purgeChildren(){
        children.removeIf(child -> child.toDelete);
    }
    public void addQueuedChildren(){
        children.addAll(childrenQueue);
        childrenQueue.clear();
    }


    public void fillSceneFromJSON(JSONObject object){

    }

    public JSONObject toJSON(){

        JSONObject scene = new JSONObject();

        scene.put("background", background.ID);
        scene.put("mainPlayer", player.toJSON());

        JSONArray childrenArray = new JSONArray();
        for(Paintable child: children){
            childrenArray.add(child.toJSON());
        }

        scene.put("children", childrenArray);

        return scene;
    }





    Random rand = new Random();
    //Listeners
    private class CoinCollected implements CoinCollectListener {
        @Override
        public void onCollectCoin(Coin coin) {
            //Adding a coin

            Sound.setPlaySound("\\src\\Game\\Resources\\Sounds\\Coin\\coin.wav");
            addToWaitingList(new Coin(rand.nextInt(300), rand.nextInt(300)));
        }
    }

}
