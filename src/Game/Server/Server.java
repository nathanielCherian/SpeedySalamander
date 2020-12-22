package Game.Server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;

public class Server {

    public static HashMap<Integer, ClientObject> clients = new HashMap<>();

    public static void main(String args[]) throws SQLException, InterruptedException, IOException {

        System.out.println("Server Started...");

        ServerSocket server = new ServerSocket(8888);
        server.setReuseAddress(true);

        //getting client request
        while (true){

            Socket client = server.accept();
            System.out.println("user connected " + client.getInetAddress().getHostName());


            /*
            {

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                int id_ = ((Long) parseJSON(in.readLine()).get("playerID")).intValue();
                clients.put(id_, new ClientObject(id_));
            }
            */


            // New Thread Object
            ClientHandler clientSock = new ClientHandler(client);
            new Thread(clientSock).start();

        }


    }

    public static JSONObject parseJSON(String s){
        JSONObject object = null;
        try{
            JSONParser parser = new JSONParser();
            object = (JSONObject) parser.parse(s);
        } catch(ParseException e){
            e.printStackTrace();
        }

        return object;
    }


    private static class ClientObject{

        public int playerID;

        public double xPos;
        public double yPos;


        public ClientObject(int id){
            playerID = id;
        }


        @Override
        public String toString() {
            return getJSON().toJSONString();
        }

        public JSONObject getJSON(){
            JSONObject json = new JSONObject();
            json.put("xPos", xPos);
            json.put("yPos", yPos);

            return json;
        }

        public void setJSON(JSONObject json){
            xPos = ((Long)json.get("xPos")).doubleValue();
            yPos = ((Long)json.get("yPos")).doubleValue();
        }


    }


    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }



        @Override
        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;

            try{
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line;
                while((line = in.readLine()) != null){


                    JSONObject object = parseJSON(line);
                    int id_ = ((Long) object.get("playerID")).intValue();
                    System.out.println("Recieved: " + object);


                    if(clients.containsKey(id_)){
                        ClientObject co = clients.get(id_);
                        co.setJSON(object);
                    }else{
                        ClientObject co = new ClientObject(id_);
                        co.setJSON(object);
                        clients.put(id_, co);
                    }




                    JSONObject object_out = new JSONObject(clients);
                    out.println(object_out.toJSONString());

                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("USER DISCONNECTED");
            } finally {

                try{
                    if(out != null){
                        out.close();
                    }
                    if(in != null){
                        in.close();
                        clientSocket.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        }

    }

}
