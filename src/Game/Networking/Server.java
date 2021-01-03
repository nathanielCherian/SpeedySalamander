package Game.Networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    static boolean open = true;

    public static void main(String args[]) throws IOException {

        System.out.println("Server Started...");

        ServerSocket server = new ServerSocket(8888);
        server.setReuseAddress(true);

        ClientManager clientManager = new ClientManager();

        while (open){

            Socket clientSocket = server.accept();
            ClientInstance client = new ClientInstance(clientSocket);
            String ip = client.getIP();
            System.out.println("user connected " + ip);

            clientManager.add(client);


        }

    }






}
