import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter ip address");
        String ip = s.nextLine();
        System.out.println("Enter port number");
        int port = s.nextInt();
        Socket clientsocket = new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(clientsocket.getOutputStream());

        String line = "";
        while (!line.equals("END")){
            line = s.nextLine();
            out.writeUTF(line);
        }

        out.close();
        clientsocket.close();

    }
}
