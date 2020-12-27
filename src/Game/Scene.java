package Game;

import Game.Listeners.CoinCollectListener;
import Game.Objects.Coin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Scene{

    public int x;
    public int y;
    public int w;

    public ArrayList<Paintable> children = new ArrayList<>();
    public ArrayList<Paintable> childrenQueue = new ArrayList<>(); //children waiting for queue to finish to be added

    public CoinCollected coinCollected = new CoinCollected();

    public Scene(){

    }


    public void add(Paintable o){
        children.add(o);
    }
    public void addToWaitingList(Paintable o){
        childrenQueue.add(o);
    }


    public void paint(Graphics2D g2d){
        for(Paintable child: children){
            child.paint(g2d);
        }
    }

    //Periodically call to update scene
    public void purgeChildren(){
        children.removeIf(child -> child.toDelete);
    }
    public void addChildren(){
        children.addAll(childrenQueue);
        childrenQueue.clear();
    }


    Random rand = new Random();

    //Listeners
    private class CoinCollected implements CoinCollectListener {
        @Override
        public void onCollectCoin(Coin coin) {
            //Adding a coin
            addToWaitingList(new Coin(rand.nextInt(300), rand.nextInt(300)));
        }
    }

}
