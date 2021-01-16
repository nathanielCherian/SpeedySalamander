package Game.Objects;

import org.json.simple.JSONObject;

public class BennyFire extends ImageObject{
    public BennyFire(int x, int y) {
        super(x, y);
        init();
    }

    public BennyFire(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "BENNYFIRE";
        setImage("\\src\\Game\\Resources\\Objects\\pixelfirenew.png");
        this.isSolid = false;
    }
}