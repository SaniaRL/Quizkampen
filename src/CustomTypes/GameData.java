package CustomTypes;

import Enums.Turn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameData implements Serializable {
    private String gameID;
    private Turn turn;
    private List<Round> rounds = new ArrayList<>();
    private int roundAmount;
    private int questionAmount;

    public GameData(){}
    public GameData(String gameID, int roundAmount, int questionAmount) {
        this.gameID = gameID;
        this.roundAmount = roundAmount;
        this.questionAmount = questionAmount;
        this.turn = Turn.Player1;
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

    public synchronized void setRounds(Round round) {
        Round newRound = new Round(round.getCategory(), round.getQuestions(), round.getPlayer1Score(), round.getPlayer2Score());
        this.rounds.add(newRound);
    }
    public void addRound(Round round){
        rounds.add(round);
    }

    public synchronized Turn getTurn() {
        return turn;
    }

    public synchronized void setTurn(Turn turn) {
        this.turn = turn;
    }

    public int getRoundsAmount(){
        return rounds.size();
    }
    public int getQuestionAmount(){
        return questionAmount;
    }
    public int getRoundAmount() {
        return roundAmount;
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
