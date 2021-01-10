package Game.Multiplayer;

import Game.Listeners.UpdateListener;
import Game.Paintable;
import Game.Scene;
import com.amazonaws.services.dynamodbv2.xspec.S;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientListener clientListener;

    public boolean open = true;

    public ClientUpdateListener clientUpdateListener;
    public Client(){
        clientUpdateListener = new EmptyClientUpdateListener(); //Start with empty so no messages are sent

    }

    private Scene.InitialSceneListener initialSceneListener;
    public void setInitialSceneListener(Scene.InitialSceneListener initialSceneListener) {
        this.initialSceneListener = initialSceneListener;
    }

    private int PLAYER_ID;
    public void setPLAYER_ID(int PLAYER_ID) {
        this.PLAYER_ID = PLAYER_ID;
    }

    public void startClient(String ip, int port, ClientListener listener){

        clientUpdateListener = new ClientUpdateListener(); //Switch Client listener

        clientListener = listener;
        try{

            socket = new Socket(ip, port);

            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(), true);

            String initialScene = in.readLine();
            if(initialScene==null)return;
            initialSceneListener.receivedInput(initialScene);

            Thread clientThread = new Thread(new ClientRunnable());
            clientThread.setName("GAME: client listener");
            clientThread.setDaemon(true); //Saving cpu power for game
            clientThread.start();

            clientListener.connectedToServer();

        } catch (UnknownHostException e) {
            //unkown host
            open = false;
            clientListener.unknownHost();
            e.printStackTrace();
        } catch (IOException e) {
            //Took too long to connect
            open = false;
            clientListener.couldNotConnect();
            e.printStackTrace();
        } catch (Exception e) {
            //anything else
            open = false;
            e.printStackTrace();
        }

    }


    class ClientRunnable implements Runnable{

        @Override
        public void run() {

            while (open){

                try{

                    String msg = in.readLine();
                    if(msg==null){
                        open=false;
                        clientListener.disconnected();

                        try {
                            socket.close();
                            in.close();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    clientListener.receivedInput(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                    open = false;
                    clientListener.serverClosed();

                    //close all connections
                    try{
                        socket.close();
                        in.close();
                        out.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }


            }
        }
    }

    public void send(String msg){
        if(open){
            out.println(msg);
        }
    }

    public void dispose(){
        try{
            if(open){
                open=false;
                socket.close();
                in.close();
                out.close();
                clientListener.disconnected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isConnected(){
        return open;
    }


    //This class will send messages
    public class ClientUpdateListener implements UpdateListener {
        @Override
        public void onCreate(Paintable p) {
            //System.out.println("created! " + p);
            sendJSONString(p, "CREATE");
        }

        @Override
        public void onChange(Paintable p) {
            //System.out.println("changed! " + p);
            sendJSONString(p, "CHANGE");
        }

        @Override
        public void onDelete(Paintable p) {
            //System.out.println("deleted! " + p);
            sendJSONString(p, "DELETE");
        }


        public void sendJSONString(Paintable p, String code){
            JSONObject object = new JSONObject();
            object.put("playerID", PLAYER_ID);
            object.put("stateCode", code);
            object.put("object", p.toJSON());
            send(object.toJSONString());
        }

    }


    //Goes to nowhere
    public class EmptyClientUpdateListener extends ClientUpdateListener {
        @Override
        public void onCreate(Paintable p) {
            //System.out.println("created!");
        }

        @Override
        public void onChange(Paintable p) {
            //System.out.println("changed!");
        }

        @Override
        public void onDelete(Paintable p) {
            //System.out.println("deleted!");
        }
    }

}
