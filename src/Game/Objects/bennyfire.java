package Game.Objects;

public class bennyfire extends ImageObject{
    public bennyfire (int x, int y) {
        super(x, y);
        this.ID = "bennyfire";
        setImage("\\src\\Game\\Resources\\Objects\\fire1_64.png");

        this.isSolid = false;
    }
}