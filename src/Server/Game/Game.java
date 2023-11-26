package Server.Game;

import CustomTypes.Round;
import Enums.GameState;
import Server.ClientHandler;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import CustomTypes.GameData;

public class Game {
    private ClientHandler player1;
    private ClientHandler player2;
    private GameState gameState;
    private GameData gameData;
    private final CountDownLatch latch;
    Properties properties = new Properties();
    public Game(ClientHandler player1) {
        this.player1 = player1;
        gameData = new GameData(String.valueOf(UUID.randomUUID()), new ArrayList<>());
        gameState = GameState.WAITING;
        this.latch = new CountDownLatch(1);
    }
    public GameData getGameData() {
        return gameData;
    }
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
    public void setPlayer2(ClientHandler player2) {
        this.player2 = player2;
    }

    public ClientHandler getPlayer1() {
        return player1;
    }

    public ClientHandler getPlayer2() {
        return player2;
    }

    public void setPlayer1(ClientHandler player1) {
        this.player1 = player1;
    }


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
