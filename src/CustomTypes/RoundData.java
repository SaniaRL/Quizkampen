package CustomTypes;

import java.util.List;

public class RoundData {
    private String category;
    private List<Boolean> player1Score;
    private List<Boolean> player2Score;

    public RoundData(String category, List<Boolean> player1Score, List<Boolean> player2Score) {
        this.category = category;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Boolean> getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(List<Boolean> player1Score) {
        this.player1Score = player1Score;
    }

    public List<Boolean> getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(List<Boolean> player2Score) {
        this.player2Score = player2Score;
    }
}
