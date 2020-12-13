package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws SQLException, InterruptedException, IOException {


        try(Socket socket = new Socket("127.0.0.1", 3000)){

            // Writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //Reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String line = null;

            while(!"exit".equalsIgnoreCase(line)){
                line = scanner.nextLine();

                out.println(line);
                out.flush();

                System.out.println("replied: " + in.readLine());
            }

            scanner.close();

        }catch (IOException e){
            e.printStackTrace();
        }



        /*
        Socket s = new Socket("127.0.0.1", 3000);

        //Connection Message
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String msg = dis.readUTF();
        System.out.println(msg);


        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.println("say something...");
        while(true){
            String in = scanner.nextLine();
            dos.writeUTF(in);
        }
        */

    }

}
