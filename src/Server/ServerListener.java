package Server;

import java.io.*;
import java.net.Socket;

public class ServerListener extends Thread {
    private final Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public ServerListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeToClient("Connection established to server");
            String fromClient = in.readLine();
            System.out.println("Thread no." + Thread.currentThread().threadId() + " :" + fromClient);

            while(true) {
                //Code to test connection.
                fromClient = in.readLine();

                if(fromClient.equalsIgnoreCase("exit")) {
                    writeToClient("Connection closed by server");
                    break;
                }

                System.out.println(fromClient);
                writeToClient("Echo: " + fromClient);
            }

        } catch (IOException e) {
            System.out.println("IO Exception from ServerListener: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // ignore;
            }
        }
    }

    public void writeToClient(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.out.println("IO Exception from ServerListener: " + e.getMessage());
        }
    }
}
