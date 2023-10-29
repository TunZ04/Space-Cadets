import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter port number");
        int port = s.nextInt();
        ServerSocket serversocket = new ServerSocket(port);

        Socket clientsocket = serversocket.accept();
        DataInputStream in = new DataInputStream(clientsocket.getInputStream());

        String line = "";
        while (!line.equals("END")){
            line = in.readUTF();
            System.out.println(line);
        }

        clientsocket.close();
        serversocket.close();
        in.close();

    }
}
