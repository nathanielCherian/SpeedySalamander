package Game.Objects;

import org.json.simple.JSONObject;

public class Tree extends ImageObject {


    public String filePath = "\\src\\Game\\Resources\\Environment\\summer_tree.png";

    public Tree(int x, int y){
        super(x,y);
        init();
    }

    public Tree(JSONObject object){
        super(object);
        init();
    }

    private void init(){
        this.ID = "TREE";
        setImage("\\src\\Game\\Resources\\Environment\\summer_tree.png");
        this.isSolid = true;
    }


}
