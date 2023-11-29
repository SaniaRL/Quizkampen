package Server.Game;

import Enums.GameState;
import Server.ClientHandler;
import java.util.UUID;
import CustomTypes.GameData;
import Server.UserData.User;


public class Game {
    private ClientHandler player1;
    private ClientHandler player2;
    private User user2;
    private GameState gameState;
    private GameData gameData;

    public Game(ClientHandler player1) {
        this.player1 = player1;
        gameData = new GameData(String.valueOf(UUID.randomUUID()), 3, 3);
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
    public User getUser2() {
        return user2;
    }
    public void setUser2(User user2) {
        this.user2 = user2;
    }

}
