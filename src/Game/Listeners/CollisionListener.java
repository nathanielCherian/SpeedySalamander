package Game.Listeners;

import Game.Paintable;

public interface CollisionListener extends Listener{
    public void onCollide(Paintable obj1, Paintable obj2);
}
