package Server;

import CustomTypes.GameData;
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
                if (game.getGameData().getTurn().equals("player1")) {
                    client.writeToClient("game found wait;" + game.getGameData().getGameID());
                } else {
                    client.writeToClient("game found start;" + game.getGameData().getGameID());
                }
            }
        } else {
            System.out.println("creating new game");
            Game game = new Game(client);
            server.games.add(game);
            client.writeToClient("game started;" + game.getGameData().getGameID());
        }
    }

    public void roundFinished(String message, GameData gameData, Server server, ClientHandler client) throws IOException {
        if (!message.equals("round finished"))
            return;
        for (Game game : server.games) {
            if (!game.getGameData().getGameID().equals(gameData.getGameID()))
                continue;
            if (game.getGameData().getTurn().equals("player1")) {
                game.getGameData().setTurn("player2");
            } else {
                game.getGameData().setTurn("player1");
            }
            while (game.getGameState() == GameState.WAITING) {
                try {
                    client.wait();
                } catch (InterruptedException e) {
                    //ignore
                }
                if (game.getGameData().getTurn().equals("player2")) {
                    game.getPlayer1().writeToClient("opponent turn;" + game.getGameData().getGameID());
                    game.getPlayer2().writeToClient("your turn;" + game.getGameData().getGameID());
                } else {
                    game.getPlayer2().writeToClient("opponent turn;" + game.getGameData().getGameID());
                    game.getPlayer1().writeToClient("your turn;" + game.getGameData().getGameID());
                }
            }

        }
    }
}
