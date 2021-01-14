package Game.Listeners;

import Game.Paintable;

public interface UpdateListener {
    public void onCreate(Paintable p);
    public void onChange(Paintable p);
    public void onDelete(Paintable p);
}
