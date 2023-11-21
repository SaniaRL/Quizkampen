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
                String[] message = fromClient.split(";");
                System.out.println(fromClient);
                if (message[0].equals("exit")) {
                    System.out.println("Client disconnected");
                    server.shutdown();
                    break;
                }

                if (message[0].equals("new game")) {
                    System.out.println("in new game check");
                    if (!server.games.isEmpty()) {
                        for (Game game : server.games) {
                            System.out.println("in for loop");
                            if (game.getPlayer2() == null && game.getTurn().equals("player1")) {
                                System.out.println(game.getGameID());
                                this.writeToClient("game found wait;" + game.getGameID());
                                game.setPlayer2(this);
                                System.out.println(game.getPlayer2());
                            } else if (game.getPlayer2() == null && game.getTurn().equals("player2")) {
                                System.out.println(game.getGameID());
                                this.writeToClient("game found start;" + game.getGameID());
                                game.setPlayer2(this);
                                System.out.println(game.getPlayer2());
                            }
                        }
                    } else {
                        System.out.println("creating new game");
                        Game game = new Game(this);
                        server.games.add(game);
                        this.writeToClient("game started;" + game.getGameID());
                    }
                }
                if (message[0].equals("round finished")) {
                    for (Game game : server.games) {
                        if (game.gameID.equals(message[1])) {
                            if (game.getTurn().equals("player1")) {
                                game.setTurn("player2");
                                game.getPlayer1().writeToClient("opponent turn;" + game.getGameID());
                                game.getPlayer2().writeToClient("your turn;" + game.getGameID());
                            } else {
                                game.setTurn("player1");
                                game.getPlayer2().writeToClient("opponent turn;" + game.getGameID());
                                game.getPlayer1().writeToClient("your turn;" + game.getGameID());

                            }
                        }
                    }
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
