package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    List<ClientHandler> connectedClients = Collections.synchronizedList(new ArrayList<>());
    List<ClientHandler> waiting = Collections.synchronizedList(new ArrayList<>());
    List<Game> games = Collections.synchronizedList(new ArrayList<>());
    ServerSocket socket;
    private boolean running = true;

    public Server(int port) {

        try {
            socket = new ServerSocket(port);

            while (running) {
                Socket clientSocket = socket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                connectedClients.add(clientHandler);
                clientHandler.start();
                connectedClients.removeIf(Thread::isInterrupted);
                System.out.println(connectedClients);
            }
        } catch (IOException e) {
            System.out.println("Server error");
        }
    }

    public void shutdown() {
        running = false;

        try {
            for (ClientHandler client : connectedClients) {
                client.writeToClient("Server is shutting down");
                client.closeConnection();
            }

            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing socket: " + e.getMessage());
        }
        connectedClients.clear();
    }

    public static void main(String[] args) {
        int port = 8080;
        new Server(port);
    }
}
