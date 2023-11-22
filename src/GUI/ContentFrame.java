package GUI;

import GUI.CategoryGUI.ChooseCategoryPage;
import GUI.ScoreBoard.ScoreBoardPage;
import Question.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class ContentFrame extends JFrame {

    JPanel contentPanel;
    CardLayout cardLayout;

    StartPage startPage;
    ChooseCategoryPage chooseCategoryPage;
    QuestionPage questionPage;
    WaitingPage waitingPage;
    ScoreBoardPage scoreBoardPage;

    String gameID;

    //Should be moved to game logic later:
    List<List<Boolean>> totalWins = new ArrayList<>();
    List<Boolean> currentWin = new ArrayList<>();
    String category = "Film";

    QuestionCollection questionCollection = new QuestionCollection();
    ObjectOutputStream out;
    private final List<String> games = new ArrayList<>();
    boolean chosenCategory = false;

    public ContentFrame(ObjectOutputStream out) throws IOException {
        this.out = out;
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage(category);
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage(gameID);

        buildFrame();
    }
    public ContentFrame() throws IOException {
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage(category);
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage(gameID);

        buildFrame();
    }

    //TODO Remove main
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("unused")
        ContentFrame contentFrame = new ContentFrame();
    }

    public void buildFrame() {
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Quizkampen");
        ImageIcon image = new ImageIcon("Icons/Sun.png");
        setIconImage(image.getImage());

        contentPanel.add(startPage, "StartPage");
        contentPanel.add(chooseCategoryPage, "ChooseCategoryPage");
        contentPanel.add(questionPage, "QuestionPage");
        contentPanel.add(waitingPage, "WaitingPage");
        contentPanel.add(scoreBoardPage, "ScoreBoardPage");

        add(contentPanel);
        addActionEvents();
        setVisible(true);
    }

    public void writeToServer(String message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void newGameStarted(String gameID) {
        this.gameID = gameID;
        System.out.println("choose category");
        cardLayout.show(contentPanel, "ChooseCategoryPage");
        addActionListenerToOptions();
        chosenCategory = true;
    }

    public void getQuestions(String gameID) {
        this.gameID = gameID;
        System.out.println("existing game found!");
        cardLayout.show(contentPanel, "QuestionPage");
        addActionListenerToOptions();
    }

    public void waitingForPlayer(String gameID) {
        this.gameID = gameID;
        cardLayout.show(contentPanel, "WaitingPage");
    }

    public void addActionEvents() {

        //START PAGE
        addActionListerToStartPage();

        //WAITING PAGE
        waitingPage.getTextButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));

        //CHOOSE CATEGORY PAGE
        chooseCategoryPage.getCategoryOption1().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption1().getText();
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.nextThreeQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });
        chooseCategoryPage.getCategoryOption2().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption2().getText();
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.nextThreeQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });
        chooseCategoryPage.getCategoryOption3().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption3().getText();
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.nextThreeQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });

        //QUESTION PAGE
//        addActionListenerToOptions();

        //SCORE BOARD PAGE
        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
            questionPage.nextThreeQuestions(questionCollection.getRandomCategory().label);
            SwingUtilities.invokeLater(() -> chooseCategoryPage.updateQuestionCategories());
            cardLayout.show(contentPanel, "ChooseCategoryPage");
            addActionListenerToOptions();

        });
    }

    public void addActionListerToStartPage(){
        startPage.getStartNewGame().addActionListener(ActionEvent -> writeToServer("new game"));
    }
/*    public void addActionListerToStartPage(){
        //START PAGE
        startPage.getStartNewGame().addActionListener(ActionEvent -> {
        cardLayout.show(contentPanel, "WaitingPage");
        });
        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));
    }*/

    public void addActionListenerToOptions() {
        List<JButton> optionButtons = questionPage.getOptionButtons();
        for (JButton option : optionButtons) {
            option.addActionListener(ActionEvent -> {
                checkIfWin(option);
                if (currentWin.size() < 3) {
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions();
                } else {
                    totalWins.add(new ArrayList<>(currentWin));
                    currentWin.clear();
                    scoreBoardPage.setWinList(totalWins);
                    if (chosenCategory) {
                        cardLayout.show(contentPanel, "ScoreBoardPage");
                        scoreBoardPage.setGameID(gameID);
                        writeToServer("round finished;" + scoreBoardPage.getGameID());
                    }
                    else {
                        newGameStarted(gameID);
                    }
                    try {
                        scoreBoardPage.updateScoreBoard();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    questionPage.setIndexCount(0);
                    cardLayout.show(contentPanel, "ScoreBoardPage");
                    chosenCategory = false;
                }
            });
        }
    }

    public void checkIfWin(JButton option){
        if (option.getText().equals(questionPage.getAnswer())) {
            System.out.println("right");
            currentWin.add(true);
        } else {
            System.out.println("wrong");
            currentWin.add(false);
        }
    }

    public void showScoreBoardPage(){
        questionPage.setIndexCount(0);
        cardLayout.show(contentPanel, "ScoreBoardPage");
        chosenCategory = false;
    }

    //Needed for NetWork

    public List<String> getGames() {
        return games;
    }

    /*public void addActionListenerToOptions() {
        List<JButton> optionButtons = questionPage.getOptionButtons();
        for (JButton option : optionButtons) {
            option.addActionListener(ActionEvent -> {
                checkIfWin(option);
                if (currentWin.size() < 3) {
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions();
                } else {
                    totalWins.add(new ArrayList<>(currentWin));
                    currentWin.clear();
                    scoreBoardPage.setWinList(totalWins);
                    if (chosenCategory) {
                        cardLayout.show(contentPanel, "ScoreBoardPage");
                        scoreBoardPage.setGameID(gameID);
                        writeToServer("round finished;" + scoreBoardPage.getGameID());
                    }
                    else {
                        newGameStarted(gameID);
                    }
                    try {
                        scoreBoardPage.updateScoreBoard();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    showScoreBoardPage();
                }
            });
        }
    }*/

}
