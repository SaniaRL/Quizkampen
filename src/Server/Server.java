package Server;

import Server.Game.Game;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Server {
    List<ClientHandler> connectedClients = Collections.synchronizedList(new ArrayList<>());
    List<ClientHandler> waiting = Collections.synchronizedList(new ArrayList<>());
    List<Game> games = Collections.synchronizedList(new ArrayList<>());
    ServerSocket socket;
    private boolean running = true;
    private String questionsToFind;  //5

    public Server(int port) {

        try {
            socket = new ServerSocket(port);
            loadPropertiesServer(); // Här sattes 5:an. Vi kan hämta 5:an med vår getter metod.
            System.out.println(questionsToFind +" Det här är en femma i Servern");

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

    public String getQuestionsToFind() {
        return questionsToFind;
    }

    public static void main(String[] args) {
        int port = 8080;
        new Server(port);
    }

    private void loadPropertiesServer() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("Server/PropertiesFile.properties")) {
            if (input != null) {
                prop.load(input);
                questionsToFind = String.valueOf(Integer.parseInt(prop.getProperty("questionsToFind", "3"))); // Om mikakel justera sen
            } else {
                System.out.println("Could not find properties");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void shutdown() {
        running = false;

        try {
            for (ClientHandler client : connectedClients) {
                client.writeToClient("Server is shutting down", null);
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

}
