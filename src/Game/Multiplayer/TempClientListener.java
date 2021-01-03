package Game.Multiplayer;

public class TempClientListener implements ClientListener{
    @Override
    public void unknownHost() {
        System.out.println("unknownHost");
    }

    @Override
    public void couldNotConnect() {
        System.out.println("couldNotConnect");
    }

    @Override
    public void recivedInput(String msg) {
        System.out.println("recivedInput: " + msg);
    }

    @Override
    public void serverClosed() {
        System.out.println("serverClosed");
    }

    @Override
    public void disconnected() {
        System.out.println("disconnected");
    }

    @Override
    public void connectedToServer() {
        System.out.println("connectedToServer");
    }
}
