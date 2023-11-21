package Client;

import GUI.ContentFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private BufferedWriter out;
    private BufferedReader in;


    public Client(InetAddress address, int port) {

        try (Socket socket = new Socket(address, port)) {
            //The types of stream may change depending on what you want to send.
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //end of stream types

            writeToServer("Client connected");
            String fromServer = readFromServer();
            System.out.println(fromServer);

            ContentFrame frame = new ContentFrame(out);
            while ((fromServer = readFromServer()) != null) {
                String[] message = fromServer.split(";");
                if (message[0].equals("game started")) {
                    System.out.println("new game started : " + message[0] + " " + message[1]);
                    frame.getGames().add(message[1]);
                    frame.newGameStarted(message[1]);
                }
                if(message[0].equals("game found")){
                    frame.getGames().add(message[1]);
                    frame.getQuestions(message[1]);
                }
                if(message[0].equals("your turn")){
                    frame.getQuestions(message[1]);
                }
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

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        int port = 8080;
        new Client(address, port);
    }

}