package Server;

import CustomTypes.GameData;
import Server.Game.Game;
import Server.Game.GameState;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

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

            writeToClient("Connection established to server");


            Object fromClient = readFromClient();
            System.out.println("Thread no." + Thread.currentThread().threadId() + ": " + fromClient);

            while (true) {
                //TODO: Add logic for server
                fromClient = readFromClient();
                if (fromClient instanceof String) {
                    Object[] message = fromClient.toString().split(";");
                    System.out.println(fromClient);

                    if (message[0].equals("exit")) {
                        System.out.println("Client disconnected");
                        server.shutdown();
                        break;
                    }
                    synchronized (this) {
                        protocol.checkIfNewGame((String) message[0], server, this);
                        if (message.length > 1)
                            protocol.roundFinished((String) message[0], (GameData) message[1], server, this);
                    }
                }
            } /*else if (fromClient instanceof otherType) {
                //TODO: Add logic for other types of objects
            }*/

        } catch (IOException e) {
            System.out.println("IO Exception from ServerListener: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception from ServerListener: " + e.getMessage());
        } finally {
            try {
                closeConnection();
                server.connectedClients.remove(this);
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public synchronized void writeToClient(String message) throws IOException {
        out.writeObject(message);
        out.flush();
    }

    public synchronized Object readFromClient() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }
}
