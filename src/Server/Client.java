package Server;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {

    public int playerID;
    public String ip;
    public Client(int id_, String ip){
        playerID = id_;
        this.ip = ip;
    }


    public String makeRequest(JSONObject data){


        String response = null;

        try(Socket socket = new Socket(ip, 3000)){

            // Writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //Reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            out.println(data.toJSONString());
            out.flush();

            response = in.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }


        return response;
    }


    public static void main(String args[]) throws SQLException, InterruptedException, IOException {

        int PLAYER_ID = 0;

        try(Socket socket = new Socket("127.0.0.1", 3000)){

            // Writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //Reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            { //Sending playerID First
                JSONObject so = new JSONObject();
                so.put("playerID", PLAYER_ID);
                out.println(so.toJSONString());
            }



            Scanner scanner = new Scanner(System.in);
            while(true){

                float xPos = scanner.nextFloat();
                float yPos = scanner.nextFloat();

                JSONObject so = new JSONObject();
                so.put("playerID", PLAYER_ID);
                so.put("xPos", xPos);
                so.put("yPos", yPos);

                out.println(so.toJSONString());
                out.flush();


                System.out.println("replied: " + in.readLine());
            }


            //scanner.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }




}
