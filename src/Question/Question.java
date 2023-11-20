package Question;

import java.util.Arrays;
import java.util.Collections;

public class Question {
    private String question;
    private String[] questionOptions;
    private QuestionCategory category;

    public Question(String question, String[] questionOptions, QuestionCategory category){
        this.question = question;
        this.questionOptions = questionOptions;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String[] questionOptions) {
        this.questionOptions = questionOptions;
    }

    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }
}
