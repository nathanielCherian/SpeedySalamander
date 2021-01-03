package Game.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
    static private Socket socket;
    static private BufferedReader in;
    static private PrintWriter out;

    public static void main(String args[]){

        try{
            socket = new Socket("127.0.0.1", 8888);


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("hello there");

            //Starting listener thread
            ThreadHandler threadHandler = new ThreadHandler();
            Thread clientThread = new Thread(threadHandler);
            clientThread.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class ThreadHandler implements Runnable{

        @Override
        public void run() {

            try{
                while (true){
                    String s = in.readLine();
                    System.out.println("message: " + s);
                }

            } catch (IOException e) {
                e.printStackTrace();

            }

        }
    }


}
