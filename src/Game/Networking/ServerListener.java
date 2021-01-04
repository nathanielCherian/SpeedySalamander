package Game.Networking;

public interface ServerListener {
    void onStart(String ip, String port);
    void onClientConnect();
    void onInputRecieved(String msg);
    void onClientDisconnect();
    void onClose();
}
