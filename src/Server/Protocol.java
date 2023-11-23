package Server;

import Server.Game.Game;
import Server.Game.GameState;

import java.io.IOException;

public class Protocol {
    public Protocol() {
    }

    public void checkIfNewGame(String message, Server server, ClientHandler client) throws IOException {
        if (!message.equals("new game"))
            return;
        if (!server.games.isEmpty()) {
            for (Game game : server.games) {
                if (game.getPlayer2() != null)
                    continue;
                game.setPlayer2(client);
                game.setGameState(GameState.STARTED);
//                if (game.getGameState() == GameState.STARTED) {
//                    game.notify();
//                }
                if (game.getTurn().equals("player1")) {
                    client.writeToClient("game found wait;" + game.getGameID());
                } else {
                    client.writeToClient("game found start;" + game.getGameID());
                }
            }
        } else {
            System.out.println("creating new game");
            Game game = new Game(client);
            server.games.add(game);
            client.writeToClient("game started;" + game.getGameID());
        }
    }

    public void roundFinished(String message, String gameID, Server server, ClientHandler client) throws IOException {
        if (!message.equals("round finished"))
            return;
        for (Game game : server.games) {
            if (!game.getGameID().equals(gameID))
                continue;
            if (game.getTurn().equals("player1")) {
                game.setTurn("player2");
            } else {
                game.setTurn("player1");
            }
            while (game.getGameState() == GameState.WAITING) {
                try {
                    client.wait();
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
