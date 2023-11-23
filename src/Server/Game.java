package Server;

public class Game {
    String gameID;
    ClientHandler player1;
    ClientHandler player2;
    int player1Score;
    int player2Score;

    GameState gameState;
    String turn;

    public Game(ClientHandler player1) {
        gameID = String.valueOf((int) Math.floor(Math.random()*100000));
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

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
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
}
