package Game.Entities;

import org.json.simple.JSONObject;

public class ExternalPlayer extends Player{

    public ExternalPlayer(int id_) {
        super(id_);
    }

    public ExternalPlayer(int id_, int x, int y) {
        super(id_);
        this.ID = "EPLAYER";
        this.x = x;
        this.y = y;
    }

    public void setFromJSON(JSONObject object){
        this.x = ((Double)object.get("xPos")).intValue();
        this.y = ((Double)object.get("yPos")).intValue();
    }

}
