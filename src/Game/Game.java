package Game;

// This file will hold all of the ID's of objects in the game to be checked later


import Game.Entities.ExternalPlayer;
import Game.Objects.*;
import org.json.simple.JSONObject;

public class Game {

    public static final int WIDTH = 512;
    public static final int HEIGHT = 512;

    public static final String BACKGROUND = "BACKGROUND";
    public static final String PLAYER = "PLAYER";
    public static final String EXTERNAL_PLAYER = "EPLAYER";
    public static final String TREE = "TREE";
    public static final String SMALL_ROCKS = "SMALLROCKS";
    public static final String THORN_BUSH = "THORNBUSH";
    public static final String BENNYFIRE = "BENNYFIRE";
    public static final String COIN = "COIN";


    public static final Paintable getObject(JSONObject object){

        String id = (String) object.get("G_ID");
        switch (id){
            case BACKGROUND:
                return new Background();
            case EXTERNAL_PLAYER:
                return new ExternalPlayer(object);
            case TREE:
                return new Tree(object);
            case SMALL_ROCKS:
                return new SmallRocks(object);
            case THORN_BUSH:
                return new ThornBush(object);
            case COIN:
                return new Coin(object);
            case BENNYFIRE:
                return new BennyFire(object);
            default:
                break;
        }

        return null;
    }

}
