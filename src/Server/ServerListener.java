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
            //The types of stream may change depending on what you want to send.
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //end of stream types

            writeToClient("Connection established to server");
            String fromClient = in.readLine();
            System.out.println("Thread no." + Thread.currentThread().threadId() + " :" + fromClient);

            while (true) {
                //Code to test connection. Will be removed later.
                fromClient = in.readLine();

                if (fromClient.equalsIgnoreCase("exit")) {
                    writeToClient("Connection closed by server");
                    break;
                }

                System.out.println(fromClient);
                writeToClient("Echo: " + fromClient);
                //End of test code
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

    public void writeToClient(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }
}
