package CustomTypes;

import Enums.Turn;
import Enums.QuestionCategory;
import Question.Question;

import java.io.Serializable;
import java.util.Arrays;

public class Round implements Serializable {
    private QuestionCategory category;
    private Boolean[] player1Score;
    private Boolean[] player2Score;
    private Question[] questions;

    public Round(QuestionCategory category) {
        this.category = category;
    }

    public Round(QuestionCategory category, Question[] questions, Boolean[] score, Turn player) {
        this.category = category;
        if (player == Turn.Player1)
            this.player1Score = score;
        else
            this.player2Score = score;
        this.questions = questions;
    }

    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public Boolean[] getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Boolean[] player1Score) {
        this.player1Score = player1Score;
    }

    public Boolean[] getPlayer2Score() {
        return player2Score;
    }


    public void setPlayer2Score(Boolean[] player2Score) {
        this.player2Score = player2Score;
    }

    @Override
    public String toString() {
        return "Round{" +
                "category=" + category +
                ", player1Score=" + Arrays.toString(player1Score) +
                ", player2Score=" + Arrays.toString(player2Score) +
                '}';
    }
}
