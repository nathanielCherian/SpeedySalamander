package Game.Multiplayer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                {
                    validateHTTPHandshake(s);

                }


                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(s.getOutputStream(), true);

                out.println(serverScene.getSceneAsJSON());
                JSONParser parser = new JSONParser();

                while (open){
                    String msg;
                    JSONObject object;
                    if((msg = in.readLine()) == null /*|| (object = isValidJSON(msg)) == null*/)break;
                    System.out.println("RECIEVED: " + msg);
                    object = (JSONObject) parser.parse(msg);
                    serverScene.updateFromJSON(object);
                    //System.out.println(serverScene.getSceneAsJSON());

                    clientThreadRunnables.forEach(client->{
                        if(client != this){
                            client.out.println(msg);
                        }
                    });

                }

            } catch (IOException | ParseException e) {
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


        public void validateHTTPHandshake(Socket s) throws IOException {
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            Scanner sc = new Scanner(in, "UTF-8");
            //System.out.println(sc.useDelimiter("\\r\\n\\r\\n").next());

            try{
                String data = sc.useDelimiter("\\r\\n\\r\\n").next();
                Matcher get = Pattern.compile("^GET").matcher(data);

                System.out.println(data);

                if (get.find()) {
                    Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
                    match.find();
                    byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n"
                            + "Connection: Upgrade\r\n"
                            + "Upgrade: websocket\r\n"
                            + "Sec-WebSocket-Accept: "
                            + Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1").digest((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8")))
                            + "\r\n\r\n").getBytes("UTF-8");

                    System.out.println("HEADER: \n" + new String(response, StandardCharsets.UTF_8));
                    out.write(response, 0, response.length);
                    System.out.println(sc.useDelimiter("\\r\\n\\r\\n").next());
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        }


    }




}
