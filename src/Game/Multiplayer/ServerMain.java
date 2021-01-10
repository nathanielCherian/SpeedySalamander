package Game.Multiplayer;

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


    static class ServerRunnable implements Runnable{

        @Override
        public void run() {

            System.out.println("server started...");

            while (open){

                try{

                    Socket s = ss.accept();

                    Thread clientThread = new Thread(new ClientThreadRunnable(s));
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

        @Override
        public void run() {
            System.out.println("client connected!");
            clients.add(s);

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                out.println(serverScene.getSceneAsJSON());
                JSONParser parser = new JSONParser();

                while (open){
                    String msg;
                    if((msg = in.readLine()) == null)break;
                    JSONObject object = (JSONObject) parser.parse(msg);
                    System.out.println("RECIEVED: " + msg);
                    serverScene.updateFromJSON(object);
                    System.out.println(serverScene.getSceneAsJSON());
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();

            }finally {
                clients.remove(s);
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
