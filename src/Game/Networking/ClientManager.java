package Game.Networking;

import java.util.ArrayList;

public class ClientManager {

    public ArrayList<Client> clients = new ArrayList<>();


    public ClientManager(){

    }

    public void add(Client client){
        clients.add(client);
    }




}
