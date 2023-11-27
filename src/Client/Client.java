package Client;

import CustomTypes.GameData;
import Enums.Turn;
import GUI.ContentFrame;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private ObjectOutputStream out;
    private ObjectInputStream in;


    public Client(InetAddress address, int port) {

        try (Socket socket = new Socket(address, port)) {
            //The types of stream may change depending on what you want to send.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //end of stream types

            Object fromServer = readFromServer();
            Object[] fromServerArray = (Object[]) fromServer;

            String str = fromServerArray[1].toString();
            String[] parts = str.split(";");
            int amountOfQuestions = Integer.parseInt(parts[0]);
            int amountOfRounds = Integer.parseInt(parts[1]);

            writeToServer("client connected", null);

            ContentFrame frame = new ContentFrame(out, amountOfQuestions, amountOfRounds);
            while (true) {
                fromServer = readFromServer();
                if (fromServer instanceof Object[] message) {
                    if(message[1] instanceof GameData gameData){
                        if (message[0].equals("game started")) {
                            frame.setGame(gameData);
                            frame.setPlayerSide(Turn.Player1);
                            frame.newGameStarted();
                        }
                        if (message[0].equals("game found wait")) {
                            System.out.println("found game waiting");
                            frame.setGame(gameData);
                            frame.setPlayerSide(Turn.Player2);
                            frame.waitingForPlayer();
                        }
                        if (message[0].equals("game found start")) {
                            System.out.println("found game starting");
                            frame.setGame(gameData);
                            frame.setPlayerSide(Turn.Player2);
                            frame.setChosenCategory(true);
                            frame.getQuestions();
                        }
                        if (message[0].equals("your turn")) {
                            System.out.println("your turn size: " + gameData.getRounds().size());
                            frame.setGame(gameData);
                            System.out.println(gameData);
                            frame.getQuestions();
                        }
                        if (message[0].equals("opponent turn")) {
                            System.out.println("opponent turn size: " + gameData.getRounds().size());
                            frame.setGame(gameData);
                            frame.waitingForPlayer();
                        }
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

    public <T> void writeToServer(String message, T item) throws IOException {
        if(item != null){
            out.writeObject(new Object[]{message, item});
        } else {
            out.writeObject(message);
        }
        out.flush();
    }

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