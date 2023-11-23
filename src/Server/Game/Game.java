package Server.Game;

import Server.ClientHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private String gameID;
    private ClientHandler player1;
    private ClientHandler player2;
    List<Round> rounds = new ArrayList<>();
    private GameState gameState;
    private String turn;

    public Game(ClientHandler player1) {
        gameID = String.valueOf(UUID.randomUUID());
        this.player1 = player1;
        turn = "player1";
        gameState = GameState.WAITING;
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

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
    public void addToRounds(Round round) {
        rounds.add(round);
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
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
