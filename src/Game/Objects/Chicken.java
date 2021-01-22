package Game.Objects;

import org.json.simple.JSONObject;

public class Chicken extends ImageObject{
    public Chicken(int x, int y) {
        super(x, y);
        init();
    }

    public Chicken(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "CHICKEN";
        setImage("\\src\\Game\\Resources\\Objects\\chicken.jpeg");
        this.isSolid = false;
    }
}