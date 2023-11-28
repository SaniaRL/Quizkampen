package Server;

import CustomTypes.GameData;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler extends Thread implements Serializable {
    protected final Socket socket;
    private final Protocol protocol;
    Server server;
    Object propertyObject;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.protocol = new Protocol();

        propertyObject = server.getQuestionsToFind();
    }

    @Override
    public void run() {

        try {
            //The types of stream may change depending on what you want to send.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //end of stream types

            writeToClient("properties", propertyObject);

            Object fromClient = readFromClient();
            System.out.println("Thread no." + Thread.currentThread().threadId() + ": " + fromClient);
            while (true) {
                //TODO: Add logic for server

                fromClient = readFromClient();

                System.out.println(fromClient instanceof String);
                if (fromClient instanceof String) {
                    System.out.println(fromClient);
                    if (fromClient.equals("exit")) {
                        System.out.println("Client disconnected");
                        break;
                    }
                    if (fromClient.equals("new game")) {
                        protocol.checkIfNewGame((String) fromClient, server, this);
                    }
                }
                if (fromClient instanceof Object[] message) {
                    if (message[1] instanceof GameData) {
                        System.out.println(Arrays.toString(message));
                        if (message[0].equals("round finished")) {
                            protocol.roundFinished((String) message[0], (GameData) message[1], server);
                        }
                        else if (message[0].equals("game finished")) {
                            protocol.gameFinished((String) message[0], (GameData) message[1], server, this);
                        }
                    }
                }
                //if from client -> Protokol?

            } /*else if (fromClient instanceof otherType) {
                //TODO: Add logic for other types of objects
                }*/
        } catch (IOException e) {
            System.out.println("IO Exception from ServerListener: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception from ServerListener: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                protocol.clientDisconnected(server, this);
                closeConnection();
                server.getConnectedClients().remove(this);
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public synchronized <T> void writeToClient(String message, T item) {
        try {
            if (item != null) {
                out.writeObject(new Object[]{message, item});
            } else {
                out.writeObject(message);
            }
            out.flush();
        } catch (IOException e) {
            System.out.println("Error writing to client: " + e.getMessage());
        }
    }

    public Object readFromClient() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }
}
