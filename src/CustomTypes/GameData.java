package CustomTypes;

import Enums.Turn;
import Server.UserData.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class GameData implements Serializable {
    private String gameID;
    private Turn turn;
    private List<Round> rounds = new ArrayList<>();
    private User player1;
    volatile private User player2;
    private int roundAmount;
    private int questionAmount;

    public GameData(){}
    public GameData(String gameID, int roundAmount, int questionAmount) {
        this.gameID = gameID;
        this.roundAmount = roundAmount;
        this.questionAmount = questionAmount;
        this.turn = Turn.Player1;
        this.player2 = new User("OPPONENT");
    }

    public synchronized String getGameID() {
        return gameID;
    }

    public synchronized void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public synchronized List<Round> getRounds() {
        return rounds;
    }

    public synchronized void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
    public synchronized void addRound(Round round){
        rounds.add(round);
    }

    public synchronized Turn getTurn() {
        return turn;
    }

    public synchronized void setTurn(Turn turn) {
        this.turn = turn;
    }

    public synchronized int getRoundsAmount(){
        return rounds.size();
    }
    public synchronized int getQuestionAmount(){
        return questionAmount;
    }
    public synchronized int getRoundAmount() {
        return roundAmount;
    }

    public synchronized User getPlayer1() {
        return player1;
    }

    public synchronized void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public synchronized User getPlayer2() {
        return player2;
    }

    public synchronized void setPlayer2(User player2) {
        this.player2 = player2;
    }

    @Override
    public synchronized String toString() {
        return "GameData{" +
                "gameID='" + gameID + '\'' +
                ", turn='" + turn + '\'' +
                ", rounds=" + rounds +
                '}';
    }
}
