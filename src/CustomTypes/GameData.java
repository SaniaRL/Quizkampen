package CustomTypes;

import Enums.Turn;
import java.io.Serializable;
import java.util.List;

public final class GameData implements Serializable {
    private String gameID;
    private Turn turn;
    private Round[] rounds;
    public GameData(){}
    public GameData(String gameID, String turn, int roundsAmount, int questionAmount) {
        this.gameID = gameID;
        this.rounds = new Round[roundsAmount];
        setRoundsQuestionAmount(questionAmount);
        this.turn = Turn.Player1;
    }

    private void setRoundsQuestionAmount(int questionsAmount){
        for (Round round : rounds) {
            round.setQuestionsAmount(questionsAmount);
        }
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public Round[] getRounds() {
        return rounds;
    }

    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public int getRoundsAmount(){
        return rounds.length;
    }
    public int getQuestionsAmount(){
        return rounds[0].getQuestions().length;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "gameID='" + gameID + '\'' +
                ", turn='" + turn + '\'' +
                ", rounds=" + rounds +
                '}';
    }
}
