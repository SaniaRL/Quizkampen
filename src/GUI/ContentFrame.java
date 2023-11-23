package GUI;

import GUI.CategoryGUI.ChooseCategoryPage;
import GUI.ScoreBoard.ScoreBoardPage;
import GUI.StartPage.StartPage;
import Question.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
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

    SettingsPage settingsPage;

    //Should be moved to game logic later:
    List<List<Boolean>> player1Wins = new ArrayList<>();
    List<List<Boolean>> player2Wins = new ArrayList<>();
    List<Boolean> player1Round = new ArrayList<>();
    List<Boolean> player2Round = new ArrayList<>();
    String category = "Film";
    String gameID = "4556";

    QuestionCollection questionCollection = new QuestionCollection();
    ObjectOutputStream out;

    private final List<String> games = new ArrayList<>();
    boolean chosenCategory = true;

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

        settingsPage = new SettingsPage();

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

        settingsPage = new SettingsPage();

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

        contentPanel.add(settingsPage, "SettingsPage");

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
            System.out.println(category);
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.nextThreeQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });
        chooseCategoryPage.getCategoryOption2().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption2().getText();
            System.out.println(category);
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.nextThreeQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });
        chooseCategoryPage.getCategoryOption3().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption3().getText();
            System.out.println(category);
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
            addActionListenerToOptions();

        });
    }


    public void addActionListerToStartPage(){
        startPage.getStartNewGame().addActionListener(ActionEvent -> writeToServer("new game"));
    }
/*    public void addActionListerToStartPage(){

    public void addActionListerToStartPage(String newGame) {
        //START PAGE
//        startPage.getStartNewGame().addActionListener(ActionEvent -> {
        cardLayout.show(contentPanel, "WaitingPage");
        writeToServer(newGame);
//        });
//        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));
    }

    public void addActionListerToStartPage() {

        //START PAGE

        startPage.getStartNewGame().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "WaitingPage"));

        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));
    }*/

    public void addActionListenerToOptions() {
        List<JButton> optionButtons = questionPage.getOptionButtons();

        for (JButton option : optionButtons) {
            // Remove ActionListeners, to get the delay to work on every question
            ActionListener[] actionListeners = option.getActionListeners();
            for (ActionListener listener : actionListeners) {
                option.removeActionListener(listener);
            }

            option.addActionListener(e -> {
                checkIfWin(option);

                Timer timer = new Timer(500, evt -> {
                    if (player1Round.size() < 3) {
                        questionPage.nextQuestion();
                        cardLayout.show(contentPanel, "QuestionPage");
                        addActionListenerToOptions();
                    } else {
                        generateRandomPlayer2List();
                        player1Wins.add(new ArrayList<>(player1Round));
                        player2Wins.add(new ArrayList<>(player2Round));
                        player1Round.clear();
                        player2Round.clear();
                        scoreBoardPage.setWinList(player1Wins, player2Wins);

                        if (chosenCategory) {
                            cardLayout.show(contentPanel, "ScoreBoardPage");
                            scoreBoardPage.setGameID(gameID);
                            writeToServer("round finished;" + scoreBoardPage.getGameID());
                        } else {
                            newGameStarted(gameID);
                        }
                        try {
                            scoreBoardPage.updateScoreBoard();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        cardLayout.show(contentPanel, "ScoreBoardPage");
                    }
                });
                timer.setRepeats(false);
                timer.start();
            });
        }
    }

    public void checkIfWin(JButton option){
        if (option.getText().equals("<html><div style='text-align: center;'>" + questionPage.getAnswer())) {
            option.setBackground(Color.green);
            System.out.println("right");
            player1Round.add(true);
        } else {
            option.setBackground(Color.red);
            System.out.println("wrong");
            player1Round.add(false);
        }
        option.repaint();
        option.revalidate();
    }


    public void showScoreBoardPage(){
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
                if (player1Round.size() < 3) {
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions();
                } else {
                    generateRandomPlayer2List();
                    player1Wins.add(new ArrayList<>(player1Round));
                    player2Wins.add(new ArrayList<>(player2Round));
                    player1Round.clear();
                    player2Round.clear();
                    scoreBoardPage.setWinList(player1Wins, player2Wins);
                    if (chosenCategory) {
                        cardLayout.show(contentPanel, "ScoreBoardPage");
                        scoreBoardPage.setGameID(gameID);
                        writeToServer("round finished;" + scoreBoardPage.getGameID());
                    } else {
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

    public void generateRandomPlayer2List(){
        player2Round.add(true);
        player2Round.add(false);
        player2Round.add(false);
        Collections.shuffle(player2Round);
        player2Wins.add(new ArrayList<>(player2Round));
        System.out.println(player2Round.size());

    }
}
