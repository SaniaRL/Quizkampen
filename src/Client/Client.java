package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client(InetAddress address, int port) {

        try(Socket socket = new Socket(address, port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String toServer = "Client connected";
            out.write(toServer);
            out.newLine();
            out.flush();

            String fromServer = in.readLine();
            System.out.println(fromServer);

            while(true) {
                // Code to test connections.
                System.out.print("Write something: ");
                String input = userInput.readLine();
                out.write(input);
                out.newLine();
                out.flush();

                fromServer = in.readLine();
                System.out.println(fromServer);
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        int port = 8080;
        new Client(address, port);
    }

}
