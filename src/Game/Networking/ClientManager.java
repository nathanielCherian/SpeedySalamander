package Game.Networking;

import java.util.ArrayList;

public class ClientManager {

    public ArrayList<ClientInstance> clients = new ArrayList<>();


    public ClientManager(){

    }

    public void add(ClientInstance client){
        clients.add(client);
    }




}
