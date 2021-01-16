package Game.Objects;

import org.json.simple.JSONObject;

public class Dog extends ImageObject{
    public Dog(int x, int y) {
        super(x, y);
        init();
    }

    public Dog(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "DOG";
        setImage("\\src\\Game\\Resources\\Objects\\pixeldognew.png");
        this.isSolid = false;
    }
}
