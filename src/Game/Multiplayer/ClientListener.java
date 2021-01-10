package Game.Multiplayer;

public interface ClientListener {
    public void unknownHost();
    public void couldNotConnect();
    public void receivedInput(String msg);
    public void serverClosed();
    public void disconnected();
    public void connectedToServer();
}
