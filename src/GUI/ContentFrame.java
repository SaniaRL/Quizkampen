package GUI;

import GUI.CategoryGUI.ChooseCategoryPage;
import GUI.ScoreBoard.ScoreBoardPage;
import GUI.StartPage.StartPage;
import Question.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import java.io.BufferedWriter;
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
    SettingsPage settingsPage; //Simon lagt till

    String gameID = "4556";

    //Should be moved to game logic later:
    List<List<Boolean>> player1Wins = new ArrayList<>();
    List<List<Boolean>> player2Wins = new ArrayList<>();
    List<Boolean> currentWin = new ArrayList<>();
    String category = "Film";

    QuestionCollection questionCollection = new QuestionCollection();
    BufferedWriter out;
    private final List<String> games = new ArrayList<>();
    boolean chosenCategory = true;

    public ContentFrame(BufferedWriter out) throws IOException {
        this.out = out;
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage(category);
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage();

        settingsPage = new SettingsPage(); //Simon lagt till

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
        scoreBoardPage = new ScoreBoardPage();

        settingsPage = new SettingsPage(); //Simon lagt till

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

        contentPanel.add(settingsPage, "SettingsPage"); //Simon lagt till settingsPage i CardLayout

        add(contentPanel);
        addActionEvents();
        setVisible(true);
    }

    public void writeToServer(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void newGameStarted(String gameID) {
        System.out.println("choose category");
        cardLayout.show(contentPanel, "ChooseCategoryPage");
        addActionListenerToOptions(gameID);
        chosenCategory = true;
    }

    public void getQuestions(String gameID) {
        System.out.println("existing game found!");
        cardLayout.show(contentPanel, "QuestionPage");
        addActionListenerToOptions(gameID);
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
        //Simon Ändring. ActionListener till inställningsknapp
        startPage.getSettings().addActionListener(e -> {
            System.out.println("Settings Button Clicked!");
            cardLayout.show(contentPanel, "SettingsPage");
        });

        //SCORE BOARD PAGE
        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
            questionPage.nextThreeQuestions(questionCollection.getRandomCategory().label);
            SwingUtilities.invokeLater(() -> chooseCategoryPage.updateQuestionCategories());
            cardLayout.show(contentPanel, "ChooseCategoryPage");
            addActionListenerToOptions(scoreBoardPage.getGameID());

        });
    }

    public void addActionListerToStartPage(String newGame){
        //START PAGE
//        startPage.getStartNewGame().addActionListener(ActionEvent -> {
        cardLayout.show(contentPanel, "WaitingPage");
            writeToServer(newGame);
//        });
//        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));
    }
    public void addActionListerToStartPage(){
        //START PAGE
        startPage.getStartNewGame().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "WaitingPage"));
        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));
    }

    public void addActionListenerToOptions() {
        List<JButton> optionButtons = questionPage.getOptionButtons();
        for (JButton option : optionButtons) {
            option.addActionListener(ActionEvent -> {
                checkIfWin(option);
                if (currentWin.size() < 3) {
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions(gameID);
                } else {
                    player1Wins.add(new ArrayList<>(currentWin));
                    player2Wins.add(new ArrayList<>(generateRandomPlayer2List()));
                    currentWin.clear();
                    scoreBoardPage.setWinList(player1Wins, player2Wins);

                    if (chosenCategory) {
                        cardLayout.show(contentPanel, "ScoreBoardPage");
                        scoreBoardPage.setGameID(gameID);
                        writeToServer("round finished;" + scoreBoardPage.getGameID());
                    }
                    else {
                        newGameStarted(gameID);
                    }
                    try {
                        scoreBoardPage.setWinList(player1Wins, player2Wins);
                        scoreBoardPage.updateScoreBoard();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    cardLayout.show(contentPanel, "ScoreBoardPage");
                }
            });
        }
    }

    public void checkIfWin(JButton option){
        if (option.getText().equals("<html><div style='text-align: center;'>" + questionPage.getAnswer())) {
            System.out.println("right");
            currentWin.add(true);
        } else {
            System.out.println("wrong");
            currentWin.add(false);
        }
    }

    public void showScoreBoardPage(){
        cardLayout.show(contentPanel, "ScoreBoardPage");
        chosenCategory = false;
    }

    //Needed for NetWork

    public List<String> getGames() {
        return games;
    }

    public void addActionListenerToOptions(String gameID) {
        List<JButton> optionButtons = questionPage.getOptionButtons();
        for (JButton option : optionButtons) {
            option.addActionListener(ActionEvent -> {
                checkIfWin(option);
                if (currentWin.size() < 3) {
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions(gameID);
                } else {
                    player1Wins.add(new ArrayList<>(currentWin));
                    player2Wins.add(new ArrayList<>(generateRandomPlayer2List()));
                    currentWin.clear();
                    scoreBoardPage.setWinList(player1Wins, player2Wins);
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
    }

    public List<Boolean> generateRandomPlayer2List(){
        List<Boolean> booleanList = new ArrayList<>();
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(false);
        player2Wins.add(booleanList);

        return booleanList;
    }

}
