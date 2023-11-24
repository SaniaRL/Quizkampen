package CustomTypes;

import Question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Round {
    private String category;
    private List<Boolean> player1Score;
    private List<Boolean> player2Score;
    private List<Question> questions;

    public Round(){}
    public Round(String category, List<Question> questions, List<Boolean> player1Score, List<Boolean> player2Score) {
        this.category = category;
        this.questions = questions;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
