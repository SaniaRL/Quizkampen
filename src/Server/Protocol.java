package Server;

import CustomTypes.GameData;
import CustomTypes.Round;
import Enums.Turn;
import Server.Game.Game;
import Enums.GameState;
import java.io.IOException;

public class Protocol {

    public void checkIfNewGame(String message, Server server, ClientHandler client) {
        if (!message.equals("new game"))
            return;

        boolean gameFound = false;

            for (Game game : server.games) {
                if (game.getGameState() == GameState.WAITING) {
                    game.setPlayer2(client);
                    game.setGameState(GameState.STARTED);
                    //Starts the latch countdown when entering an existing game
                    game.getLatch().countDown();

                    if (game.getGameData().getTurn() == Turn.Player1) {
                        System.out.println("entered wait");
                        client.writeToClient("game found wait", game.getGameData());
                    } else {
                        System.out.println("entered start");
                        client.writeToClient("game found start", game.getGameData());
                    }
                    gameFound = true;
                    break;
                }
            }

            if (!gameFound) {
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
            if(game.getGameState() == GameState.WAITING) {
                game.getLatch().await();
            }
            System.out.println("before if");
            if (game.getGameData().getTurn() == Turn.Player2) {
//                game.getPlayer1().writeToClient("opponent turn", null);
                if(game.getPlayer2() != null)
                    game.getPlayer2().writeToClient("your turn", gameData);
                System.out.println("player 2 turn");
            } else {
//                game.getPlayer2().writeToClient("opponent turn", null);
                game.getPlayer1().writeToClient("your turn", gameData);
                System.out.println("player 1 turn");
            }
        }
    }
}
