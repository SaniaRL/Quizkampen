package Server.Game;

import CustomTypes.RoundData;
import Server.ClientHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import CustomTypes.GameData;

public class Game {
    private ClientHandler player1;
    private ClientHandler player2;
    private GameState gameState;
    private GameData gameData;
    public Game(ClientHandler player1) {
        super();
        this.player1 = player1;
        gameData = new GameData(String.valueOf(UUID.randomUUID()), new ArrayList<RoundData>(), "player1");
        gameState = GameState.WAITING;
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
}
