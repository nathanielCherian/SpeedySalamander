package Game.Multiplayer;

import com.fasterxml.jackson.core.JsonParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    private static int port = 8888;
    private static boolean open = true;
    private static ServerSocket ss;
    private static ArrayList<Socket> clients = new ArrayList<>();

    private static ServerScene serverScene;

    public static void main(String args[]) throws IOException {

        serverScene = new ServerScene();

        ss = new ServerSocket(port);
        //Check if port is free later

        Thread serverThread = new Thread(new ServerRunnable());
        //serverThread.setDaemon(true);
        serverThread.setName("Server");
        serverThread.start();



    }


    public static ArrayList<ClientThreadRunnable> clientThreadRunnables = new ArrayList<>();

    static class ServerRunnable implements Runnable{

        @Override
        public void run() {

            System.out.println("server started...");

            while (open){

                try{

                    Socket s = ss.accept();

                    ClientThreadRunnable clientThreadRunnable = new ClientThreadRunnable(s);
                    clientThreadRunnables.add(clientThreadRunnable);
                    Thread clientThread = new Thread(clientThreadRunnable);
                    clientThread.setDaemon(true);
                    clientThread.setName("Client " + s.getInetAddress().toString());
                    clientThread.start();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    static class ClientThreadRunnable implements Runnable{

        Socket s;
        public ClientThreadRunnable(Socket s){
            this.s = s;
        }

        public BufferedReader in;
        public PrintWriter out;

        @Override
        public void run() {
            System.out.println("client connected!");
            clients.add(s);

            try {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(s.getOutputStream(), true);

                out.println(serverScene.getSceneAsJSON());
                JSONParser parser = new JSONParser();

                while (open){
                    String msg;
                    JSONObject object;
                    if((msg = in.readLine()) == null || (object = isValidJSON(msg)) == null)break;
                    System.out.println("RECIEVED: " + msg);
                    //JSONObject object = (JSONObject) parser.parse(msg);
                    serverScene.updateFromJSON(object);
                    //System.out.println(serverScene.getSceneAsJSON());

                    clientThreadRunnables.forEach(client->{
                        if(client != this){
                            client.out.println(msg);
                        }
                    });

                }

            } catch (IOException e) {
                e.printStackTrace();

            }finally {
                clients.remove(s);
                clientThreadRunnables.remove(this);
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        public JSONObject isValidJSON(String msg){
            JSONParser parser = new JSONParser();
            try {
                JSONObject object = (JSONObject) parser.parse(msg);
                return object;
            } catch (ParseException ex) {
                return null;
            }
        }

    }




}
