package CustomTypes;

import Enums.Turn;

import java.io.Serializable;
import java.util.List;

public final class GameData implements Serializable {
    private String gameID;
    private Turn turn;
    private List<Round> rounds;
    public GameData(){}
    public GameData(String gameID, List<Round> rounds, String turn) {
        this.gameID = gameID;
        this.rounds = rounds;
        this.turn = Turn.Player1;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
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
