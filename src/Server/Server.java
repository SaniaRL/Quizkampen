package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public Server(int port) {

        try(ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                new ServerListener(socket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Server error");
        }
    }
    public static void main(String[] args) {
        int port = 8080;
        new Server(port);
    }
}
