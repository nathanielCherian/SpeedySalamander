package Game;

// This file will hold all of the ID's of objects in the game to be checked later


import Game.Objects.Coin;
import Game.Objects.SmallRocks;
import Game.Objects.Tree;
import com.amazonaws.services.dynamodbv2.xspec.B;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.HashMap;

public class Game {

    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;

    public static final String BACKGROUND = "BACKGROUND";
    public static final String PLAYER = "PLAYER";
    public static final String TREE = "TREE";
    public static final String SMALL_ROCKS = "SMALLROCKS";
    public static final String THORN_BUSH = "THORNBUSH";
    public static final String EXTERNAL_PLAYER = "EPLAYER";
    public static final String COIN = "COIN";


    public static final Object getObject(JSONObject object){

        /*
        String id = (String) object.get("ID");
        if (id == BACKGROUND){
            return new Background();
        }

        int x = (int) object.get("xPos");
        int y = (int) object.get("yPos");

        switch (id){
            case TREE:
                return new Tree(x,y);
            case SMALL_ROCKS:
                return new SmallRocks(x,y);
            case THORN_BUSH:
                return new SmallRocks(x,y);
            case COIN:
                return new Coin(x,y);
            default:
                break;
        }

       */

        return new Object();
    }

}
