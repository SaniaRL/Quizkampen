package Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionCollection {
    String path;
    List<Question> allQuestions = new ArrayList<>();

/*
    public static void main(String[] args) throws IOException {
        QuestionCollection questionCollection = new QuestionCollection();
        for(Question question : questionCollection.allQuestions){
            System.out.println("Hej");
        }
    }

 */
    public QuestionCollection() throws IOException {
        path = "src/Question/Questions.txt";
        addQuestionsFromFile();
    }

    public void run(){
        addQuestionsFromFile();

    }

    private void addQuestionsFromFile(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String temp;
            String[] tempArray;

            while((temp = bufferedReader.readLine()) != null){
                tempArray = temp.split(";");
                if(tempArray.length >= 6){
                    String[] options = {
                            tempArray[2],
                            tempArray[3],
                            tempArray[4],
                            tempArray[5],

                    };
                    allQuestions.add(new Question(tempArray[1], options, findCategory(tempArray[0])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

    }
    private QuestionCategory findCategory(String question){
        return switch(question){
            case "MOVIES" -> QuestionCategory.MOVIES;
            case "ANIMALS" -> QuestionCategory.ANIMALS;
            case "LITERATURE" -> QuestionCategory.LITERATURE;
            case "SPACE" -> QuestionCategory.SPACE;
            case "TECHNOLOGY" -> QuestionCategory.TECHNOLOGY;
            case "SPORT" -> QuestionCategory.SPORT;
            case "MUSIC" -> QuestionCategory.MUSIC;
            case "HISTORY" -> QuestionCategory.HISTORY;
            default -> null;
        };
    }

    public List<Question> getCategoryList(String category){
        List<Question> categorizedList = new ArrayList<>();
        for(Question question : allQuestions){
            if(question.getCategory().equals(QuestionCategory.valueOf(category))){
                categorizedList.add(question);
            }
        }
        return categorizedList;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }
}
