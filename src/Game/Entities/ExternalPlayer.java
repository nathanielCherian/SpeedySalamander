package Game.Entities;

import org.json.simple.JSONObject;

public class ExternalPlayer extends Player{

    public ExternalPlayer(JSONObject object){
        super(object);
        init();
    }


    private void init(){
        this.ID = "EPLAYER";
        createAvatarImage();
    }
}
