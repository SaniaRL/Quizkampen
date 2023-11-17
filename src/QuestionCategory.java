import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionCategory {
    String path;
    List<Question> allQuestions = new ArrayList<>();


    public QuestionCategory() throws IOException {
        path = "src/Questions.txt";
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
                String[] options = {
                        tempArray[2],
                        tempArray[3],
                        tempArray[4],
                        tempArray[5],

                };
                allQuestions.add(new Question(tempArray[1], options, findCategory(tempArray)));

            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

    }
    private QuestionCategoryENUM findCategory(String[] question){
        switch(question[0]){
            case "MOVIES" -> {
                return  QuestionCategoryENUM.MOVIES;
            }
            case "ANIMALS" -> {
                return QuestionCategoryENUM.ANIMALS;
            }
            case "LITERATURE" -> {
                return QuestionCategoryENUM.LITERATURE;
            }
            case "SPACE" -> {
                return QuestionCategoryENUM.SPACE;
            }
            case "TECHNOLOGY" -> {
                return QuestionCategoryENUM.TECHNOLOGY;
            }
            case "SPORT" -> {
                return QuestionCategoryENUM.SPORT;
            }
            case "MUSIC" -> {
                return QuestionCategoryENUM.MUSIC;
            }
            case "HISTORY" -> {
                return QuestionCategoryENUM.HISTORY;
            }
            default -> {
                return null;
            }
        }
    }

    public List<Question> getCategoryList(String category){
        List<Question> categorizedList = new ArrayList<>();
        for(Question question : allQuestions){
            if(question.getCategory().equals(QuestionCategoryENUM.valueOf(category))){
                categorizedList.add(question);
            }
        }
        return categorizedList;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }
}
