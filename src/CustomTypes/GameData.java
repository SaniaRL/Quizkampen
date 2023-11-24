package CustomTypes;

import java.io.Serializable;
import java.util.List;

public final class GameData implements Serializable {
    private String gameID;

    private List<RoundData> rounds;
    private String turn;

    public GameData(){}
    public GameData(String gameID, List<RoundData> rounds, String turn) {
        this.gameID = gameID;
        this.rounds = rounds;
        this.turn = turn;
    }


    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public List<RoundData> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundData> rounds) {
        this.rounds = rounds;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }
}
