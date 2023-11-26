package Client;

import CustomTypes.GameData;
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

            Object fromServer = readFromServer();
            Object[] fromServerArray = (Object[]) fromServer; //Från Ovako
            System.out.println(fromServerArray[1] + " Ölen är god. Mkt godare V75");
            int vivaLaVidaSången = Integer.parseInt(fromServerArray[1].toString());
            System.out.println(vivaLaVidaSången + "Kan viva la vida var aen 5:a? DÅ blir det sören");


            ContentFrame frame = new ContentFrame(out, vivaLaVidaSången);
            while (true) {
                fromServer = readFromServer();
                if (fromServer instanceof Object[] message) {
                    if(message[1] instanceof GameData gameData){
                        if (message[0].equals("game started")) {
                            frame.setGame(gameData);
                            frame.newGameStarted();
                        }
                        if (message[0].equals("game found wait")) {
                            System.out.println("found game waiting");
                            frame.setGame(gameData);
                            frame.waitingForPlayer();
                        }
                        if (message[0].equals("game found start")) {
                            System.out.println("found game starting");
                            frame.setGame(gameData);
                            frame.getQuestions();
                        }
                        if (message[0].equals("your turn")) {
                            frame.setGame(gameData);
                            frame.getQuestions();
                        }
                        if (message[0].equals("opponent turn")) {
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