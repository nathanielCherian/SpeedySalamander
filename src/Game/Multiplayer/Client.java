package Game.Multiplayer;

import com.amazonaws.services.dynamodbv2.xspec.S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientListener clientListener;

    public boolean open = true;

    public Client(String ip, int port, ClientListener listener){

        clientListener = listener;
        try{

            socket = new Socket(ip, port);

            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(), true);

            Thread clientThread = new Thread(new ClientRunnable());
            clientThread.setName("GAME: client listener");
            clientThread.setDaemon(true); //Saving cpu power for game
            clientThread.start();

            clientListener.connectedToServer();

        } catch (UnknownHostException e) {
            //unkown host
            open = false;
            clientListener.unknownHost();
            e.printStackTrace();
        } catch (IOException e) {
            //Took too long to connect
            open = false;
            clientListener.couldNotConnect();
            e.printStackTrace();
        } catch (Exception e) {
            //anything else
            open = false;
            e.printStackTrace();
        }

    }


    class ClientRunnable implements Runnable{

        @Override
        public void run() {

            while (open){

                try{

                    String msg = in.readLine();
                    if(msg==null){
                        open=false;
                        clientListener.disconnected();

                        try {
                            socket.close();
                            in.close();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    clientListener.recivedInput(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                    open = false;
                    clientListener.serverClosed();

                    //close all connections
                    try{
                        socket.close();
                        in.close();
                        out.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }


            }
        }
    }

    public void send(String msg){
        if(open){
            out.println(msg);
        }
    }

    public void dispose(){
        try{
            if(open){
                open=false;
                socket.close();
                in.close();
                out.close();
                clientListener.disconnected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isConnected(){
        return open;
    }

}
