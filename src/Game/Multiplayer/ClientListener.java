package Game.Multiplayer;

import org.json.simple.JSONObject;

public interface ClientListener {
    public void unknownHost();
    public void couldNotConnect();
    public void receivedInput(String msg);
    public void receivedJSONInput(JSONObject object);
    public void serverClosed();
    public void disconnected();
    public void connectedToServer();
}
