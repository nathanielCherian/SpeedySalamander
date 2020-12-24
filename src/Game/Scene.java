package Game;

import java.awt.*;
import java.util.ArrayList;

public class Scene{

    public int x;
    public int y;
    public int w;

    public ArrayList<Paintable> children = new ArrayList<>();


    public void add(Paintable o){
        children.add(o);
    }

    public void paint(Graphics2D g2d){
        for(Paintable child: children){
            child.paint(g2d);
        }
    }


}
