package Server;

import CustomTypes.GameData;
import Enums.Turn;
import Server.Game.Game;
import Enums.GameState;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Protocol {
    CountDownLatch latch = new CountDownLatch(1);
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
                //Starts the latch countdown when entering an existing game
                latch.countDown();

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

    public void roundFinished(String message, GameData gameData, Server server) throws IOException, InterruptedException {
        if (!message.equals("round finished")) {
            return;
        }
        for (Game game : server.games) {
            if (!game.getGameData().getGameID().equals(gameData.getGameID()))
                continue;
            game.setGameData(gameData);
            System.out.println("waiting for opponent");

            //Pause thread until opponent has been found
            if(game.getPlayer2() == null) {
                latch.await();
            }

            if (game.getGameData().getTurn() == Turn.Player2) {
                game.getPlayer1().writeToClient("opponent turn", null);
                game.getPlayer2().writeToClient("your turn", game.getGameData());
                System.out.println("player 2 turn");
            } else {
                game.getPlayer2().writeToClient("opponent turn", null);
                game.getPlayer1().writeToClient("your turn", game.getGameData());
                System.out.println("player 1 turn");
            }
        }
    }
}
