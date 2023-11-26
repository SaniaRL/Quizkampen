package CustomTypes;

import Enums.Turn;
import java.io.Serializable;
import java.util.List;

public final class GameData implements Serializable {
    private String gameID;
    private Turn turn;
    private List<Round> rounds;
    public GameData(){}
    public GameData(String gameID, List<Round> rounds) {
        this.gameID = gameID;
        this.rounds = rounds;
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

    public synchronized void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public synchronized Turn getTurn() {
        return turn;
    }

    public synchronized void setTurn(Turn turn) {
        this.turn = turn;
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
