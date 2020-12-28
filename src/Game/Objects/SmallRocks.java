package Game.Objects;

public class SmallRocks extends ImageObject{
    public SmallRocks(int x, int y) {
        super(x, y);
        this.ID = "SMALLROCKS";

        setImage("\\src\\Game\\Resources\\Objects\\small_rocks.png");

        this.isSolid = false;

    }


}
