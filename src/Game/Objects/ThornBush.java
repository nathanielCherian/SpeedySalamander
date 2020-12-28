package Game.Objects;

public class ThornBush extends ImageObject{
    public ThornBush(int x, int y) {
        super(x, y);
        this.ID = "THORNBUSH";
        setImage("\\src\\Game\\Resources\\Objects\\bush.png");

        this.isSolid = false;
    }
}
