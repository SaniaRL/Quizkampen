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

        for (Game game : server.getGames()) {
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
            server.getGames().add(game);
            client.writeToClient("game started", game.getGameData());
        }
    }

    public void gameFinished(String message, GameData gameData, Server server, ClientHandler clientHandler) throws IOException, InterruptedException {
        Game tempGame = new Game(clientHandler);

        for (Game game : server.getGames()) {
            if (!game.getGameData().getGameID().equals(gameData.getGameID())) //Går igenom alla games i listan som skapats i servernclassen och jämför gameID. Om ej hittar, continue.
                continue;
            game.setGameData(gameData);
            tempGame = game;
        }

        tempGame.getPlayer1().writeToClient("game finished", tempGame.getGameData());
        tempGame.getPlayer2().writeToClient("game finished", tempGame.getGameData());
        server.getGames().removeIf(n -> (n.getGameData().getGameID().equals(gameData.getGameID())));
    }

    public void roundFinished(String message, GameData gameData, Server server) throws
            IOException, InterruptedException {
        if (!message.equals("round finished")) {
            return;
        }

        for (Game game : server.getGames()) {
            if (!game.getGameData().getGameID().equals(gameData.getGameID()))
                continue;

            game.setGameData(gameData);
            System.out.println("waiting for opponent");

            //Pause thread until opponent has been found
            if (game.getGameState() == GameState.WAITING) {
                game.getLatch().await();
            }
            System.out.println("before if");
            if (game.getGameData().getTurn() == Turn.Player2) {
//                game.getPlayer1().writeToClient("opponent turn", null);
                if (game.getPlayer2() != null)
                    game.getPlayer2().writeToClient("your turn", gameData);
                System.out.println("player 2 turn");
            } else {
//                game.getPlayer2().writeToClient("opponent turn", null);
                game.getPlayer1().writeToClient("your turn", gameData);
                System.out.println("player 1 turn");
            }
        }
    }

    public void clientDisconnected(Server server, ClientHandler client) {
        for (Game game : server.getGames()) {
            if (game.getPlayer1().equals(client)) {
                game.getPlayer2().writeToClient("opponent disconnected", null);
                server.getGames().remove(game);
            }
            if (game.getPlayer2().equals(client)) {
                game.getPlayer1().writeToClient("opponent disconnected", null);
                server.getGames().remove(game);
            }
        }
    }
}
