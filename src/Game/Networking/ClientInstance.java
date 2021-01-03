package Game.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientInstance {

    public Socket clientSocket;


    public ClientInstance(Socket clientSocket){
        this.clientSocket = clientSocket;

        ClientHandler clientHandler = new ClientHandler();
        Thread clientThread = new Thread(clientHandler);
        clientThread.start();

    }

    public String getIP(){
        return clientSocket.getInetAddress().getHostName();
    }


    class ClientHandler implements Runnable{

        @Override
        public void run() {
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("welcome to server");

                while (true){
                    String s = in.readLine();
                    System.out.println("FROM CLIENT: " + s);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
