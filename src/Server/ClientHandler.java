package Server;

import CustomTypes.GameData;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler extends Thread implements Serializable {
    protected final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    Server server;
    Protocol protocol = new Protocol();

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            //The types of stream may change depending on what you want to send.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //end of stream types

            writeToClient("Connection established to server", null);


            Object fromClient = readFromClient();
            System.out.println("Thread no." + Thread.currentThread().threadId() + ": " + fromClient);

            while (true) {
                //TODO: Add logic for server
                synchronized (this) {
                    fromClient = readFromClient();
                    if (fromClient instanceof String) {
                        System.out.println(fromClient);
                        if (fromClient.equals("exit")) {
                            System.out.println("Client disconnected");
                            server.shutdown();
                            break;
                        }
                        if (fromClient.equals("new game")) {
                            protocol.checkIfNewGame((String) fromClient, server, this);
                        }
                    }
                    if (fromClient instanceof Object[] message) {
                        if (message[1] instanceof GameData) {
                            System.out.println(Arrays.toString(message));
                            protocol.roundFinished((String) message[0], (GameData) message[1], server, this);
                        }
                    }
                }
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
                closeConnection();
                server.connectedClients.remove(this);
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

    public synchronized Object readFromClient() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }
}
