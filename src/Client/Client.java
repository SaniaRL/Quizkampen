package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client(InetAddress address, int port) {

        try(Socket socket = new Socket(address, port);
            //The types of stream may change depending on what you want to send.
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            //end of stream types

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
                // End of test code.
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
