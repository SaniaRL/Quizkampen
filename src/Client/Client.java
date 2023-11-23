package Client;

import GUI.ContentFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private ObjectOutputStream out;
    private ObjectInputStream in;


    public Client(InetAddress address, int port) {

        try (Socket socket = new Socket(address, port)) {
            //The types of stream may change depending on what you want to send.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //end of stream types

            writeToServer("Client connected");
            Object fromServer = readFromServer();
            System.out.println(fromServer.toString());

            ContentFrame frame = new ContentFrame(out);
            while (true) {
                fromServer = readFromServer();
                if (fromServer instanceof String) {
                    String[] message = fromServer.toString().split(";");

                    if (message[0].equals("game started")) {
                        System.out.println("new game started : " + message[0] + " " + message[1]);
                        frame.getGames().add(message[1]);
                        frame.newGameStarted(message[1]);
                    }
                    if (message[0].equals("game found wait")) {
                        System.out.println("found game waiting");
                        frame.getGames().add(message[1]);
                        frame.waitingForPlayer(message[1]);
                    }
                    if (message[0].equals("game found start")) {
                        System.out.println("found game starting");
                        frame.getGames().add(message[1]);
                        frame.getQuestions(message[1]);
                    }
                    if (message[0].equals("your turn")) {
                        frame.getQuestions(message[1]);
                    }
                    if (message[0].equals("opponent turn")) {
                        frame.waitingForPlayer(message[1]);
                    }
                    //TODO: Add logic for client
                } /*else if (fromServer instanceof otherObject) {
                    //TODO: Add logic for other object
                }*/
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } finally {
                try {
                    closeStreams();
                } catch (IOException e) {
                    System.out.println("Error closing streams: " + e.getMessage());
                }
            }
        }

    public void writeToServer(String message) throws IOException {
        out.writeObject(message);
        out.flush();
    }

    //TODO: possible method to send new game data from user to server, needs more from GUI. Possibly a Game class for client.
//    public void sendGameToServer(ClientGame game){
//        out.writeObject(game);
//
//        List<Object> gameData = new ArrayList<>();
//        gameData.add(game.id);
//        gameData.add(game.rounds);
//    }

    public Object readFromServer() throws IOException, ClassNotFoundException {
        return in.readObject();
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