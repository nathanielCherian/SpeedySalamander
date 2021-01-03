package Game.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {

    boolean open = true;

    public void main(String args[]) throws IOException {

        System.out.println("Server Started...");

        ServerSocket server = new ServerSocket(8888);
        server.setReuseAddress(true);

        ClientManager clientManager = new ClientManager();

        while (open){

            Socket clientSocket = server.accept();
            Client client = new Client(clientSocket);
            String ip = client.getIP();
            System.out.println("user connected " + ip);

            clientManager.add(client);


        }

    }






}
