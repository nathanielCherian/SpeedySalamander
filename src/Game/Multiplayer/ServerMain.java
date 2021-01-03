package Game.Multiplayer;

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

    public static void main(String args[]) throws IOException {


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

                while (open){
                    System.out.println("RECIEVED: " + in.readLine());
                }

            } catch (IOException e) {
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
