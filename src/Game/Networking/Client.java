package Game.Networking;

import java.net.Socket;

public class Client {

    public Socket clientSocket;


    public Client(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public String getIP(){
        return clientSocket.getInetAddress().getHostName();
    }


    class ClientThread implements Runnable{

        @Override
        public void run() {

        }
    }

}
