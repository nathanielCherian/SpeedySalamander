package Game.Objects;

import org.json.simple.JSONObject;

public class SmallRocks extends ImageObject{
    public SmallRocks(int x, int y) {
        super(x, y);
        init();
    }

    public SmallRocks(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "SMALLROCKS";
        setImage("\\src\\Game\\Resources\\Objects\\small_rocks.png");
        this.isSolid = false;
    }


}
