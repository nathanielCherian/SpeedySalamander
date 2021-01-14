package Game.Multiplayer;

import Game.Scene;
import org.json.simple.JSONObject;

public class ClientEventManager implements ClientListener{
    @Override
    public void unknownHost() {
        System.out.println("unknownHost");
    }

    @Override
    public void couldNotConnect() {
        System.out.println("couldNotConnect");
    }


    private Scene.MessageListener messageListener;
    public void setMessageListener(Scene.MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void receivedInput(String msg) {
        //System.out.println("recivedInput: " + msg);
    }

    @Override
    public void receivedJSONInput(JSONObject object) {
        messageListener.receivedJSONInput(object);
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
