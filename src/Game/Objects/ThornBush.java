package Game.Objects;

import org.json.simple.JSONObject;

public class ThornBush extends ImageObject{
    public ThornBush(int x, int y) {
        super(x, y);
        init();
    }

    public ThornBush(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "THORNBUSH";
        setImage("\\src\\Game\\Resources\\Objects\\bush.png");
        this.isSolid = false;
    }

}
