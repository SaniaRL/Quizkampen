package Server;

import CustomTypes.GameData;
import Enums.Turn;
import Server.Game.Game;
import Enums.GameState;

import java.io.IOException;

public class Protocol {
    public Protocol() {
    }

    public void checkIfNewGame(String message, Server server, ClientHandler client) {
        if (!message.equals("new game"))
            return;
            if (!server.games.isEmpty()) {
                for (Game game : server.games) {
                    if (game.getPlayer2() != null)
                        continue;
                    game.setPlayer2(client);
                    game.setGameState(GameState.STARTED);
                    client.notify();
                    if (game.getGameData().getTurn() == Turn.Player1) {
                        System.out.println("entered wait");
                        client.writeToClient("game found wait", game.getGameData());
                    } else {
                        System.out.println("entered start");
                        client.writeToClient("game found start", game.getGameData());
                    }

                }
            } else {
                System.out.println("creating new game");
                Game game = new Game(client);
                server.games.add(game);
                client.writeToClient("game started", game.getGameData());
            }
    }

    public void roundFinished(String message, GameData gameData, Server server, ClientHandler client) throws IOException, InterruptedException {
        if (!message.equals("round finished"))
            return;
        for (Game game : server.games) {
            if (!game.getGameData().getGameID().equals(gameData.getGameID()))
                continue;
            game.setGameData(gameData);
            while (game.getGameState() == GameState.WAITING) {
                client.wait();
                if (game.getGameData().getTurn() == Turn.Player2) {
                    game.getPlayer1().writeToClient("your turn", null);
                    game.getPlayer2().writeToClient("opponent turn", game.getGameData());
                } else {
                    game.getPlayer2().writeToClient("your turn", null);
                    game.getPlayer1().writeToClient("opponent turn", game.getGameData());
                }
            }

        }
    }
}
