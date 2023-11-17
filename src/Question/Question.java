package Question;

public class Question {
    private String question;
    private String[] questionOptions;
    private QuestionCategoryENUM category;

    public Question(String question, String[] questionOptions, QuestionCategoryENUM category){
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

    public QuestionCategoryENUM getCategory() {
        return category;
    }

    public void setCategory(QuestionCategoryENUM category) {
        this.category = category;
    }
}
