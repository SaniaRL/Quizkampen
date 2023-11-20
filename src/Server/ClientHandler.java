package Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    protected final Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    Server server;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

        try {
            //The types of stream may change depending on what you want to send.
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //end of stream types

            writeToClient("Connection established to server");
            String fromClient = readFromClient();
            System.out.println("Thread no." + Thread.currentThread().threadId() + ": " + fromClient);

            while (true) {
                //TODO: Add logic for server
                fromClient = readFromClient();
                System.out.println(fromClient);
                if(fromClient.equals("exit")) {
                    System.out.println("Client disconnected");
                    server.shutdown();
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("IO Exception from ServerListener: " + e.getMessage());
        } finally {
            try {
                closeConnection();
                server.connectedClients.remove(this);
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public void writeToClient(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    public String readFromClient() throws IOException {
        return in.readLine();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }
}
