package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private BufferedWriter out;
    private BufferedReader in;

    public Client(InetAddress address, int port) {

        try(Socket socket = new Socket(address, port)) {
            //The types of stream may change depending on what you want to send.
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            //end of stream types

            writeToServer("Client connected");
            String fromServer = readFromServer();
            System.out.println(fromServer);

            while(true) {
                String message = stdIn.readLine();
                writeToServer(message);
                //TODO: Add logic for client
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            try {
                closeStreams();
            } catch (IOException e) {
                System.out.println("Error closing streams: " + e.getMessage());
            }
        }
    }

    public void writeToServer(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    public String readFromServer() throws IOException {
        return in.readLine();
    }

    public void closeStreams() throws IOException {
        out.close();
        in.close();
    }

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        int port = 8080;
        new Client(address, port);
    }

}