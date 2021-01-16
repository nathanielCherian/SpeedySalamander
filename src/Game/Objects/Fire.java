package Game.Objects;

import org.json.simple.JSONObject;

public class Fire extends ImageObject{
    public Fire(int x, int y) {
        super(x, y);
        init();
    }

    public Fire(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "FIRE";
        setImage("\\src\\Game\\Resources\\Objects\\pixelfirenew.png");
        this.isSolid = false;
    }
}