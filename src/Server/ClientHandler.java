package Server;

import Server.Game.Game;
import Server.Game.GameState;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread implements Serializable {
    protected final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    Server server;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public synchronized void run() {

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
                    String[] message = fromClient.toString().split(";");
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
                                if (game.getPlayer2() == null) {
                                    game.setPlayer2(this);
                                    game.setGameState(GameState.STARTED);
                                    if (game.getGameState() == GameState.STARTED) {
                                        game.notify();
                                    }
                                    if (game.getTurn().equals("player1")) {
                                        this.writeToClient("game found wait;" + game.getGameID());
                                    } else if (game.getTurn().equals("player2")) {
                                        this.writeToClient("game found start;" + game.getGameID());
                                    }
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
                            if (game.getGameID().equals(message[1])) {
                                if (game.getTurn().equals("player1")) {
                                    game.setTurn("player2");
                                } else {
                                    game.setTurn("player1");
                                }
                                while (game.getGameState() == GameState.WAITING) {
                                    try {
                                        game.wait();
                                    } catch (InterruptedException e) {
                                        //ignore
                                    }
                                    if (game.getTurn().equals("player2")) {
                                        game.getPlayer1().writeToClient("opponent turn;" + game.getGameID());
                                        game.getPlayer2().writeToClient("your turn;" + game.getGameID());
                                    } else {
                                        game.getPlayer2().writeToClient("opponent turn;" + game.getGameID());
                                        game.getPlayer1().writeToClient("your turn;" + game.getGameID());
                                    }
                                }
                            }
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
        out.writeObject(message);
        out.flush();
    }

    public Object readFromClient() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void closeConnection() throws IOException {
        socket.close();
    }
}
