package Game.Objects;

public class Tree extends ImageObject {

    public String filePath = "\\src\\Game\\Resources\\Environment\\summer_tree.png";

    public Tree(int x, int y){
        super(x,y);
        setImage("\\src\\Game\\Resources\\Environment\\summer_tree.png");

        this.isSolid = true;
    }



}
