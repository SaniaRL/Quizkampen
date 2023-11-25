package GUI;

import CustomTypes.GameData;
import GUI.CategoryGUI.ChooseCategoryPage;
import GUI.ScoreBoard.ScoreBoardPage;
import GUI.StartPage.StartPage;
import Question.QuestionCategory;
import Question.QuestionCollection;
import Enums.Turn;

import javax.swing.*;
import java.awt.event.ActionListener;
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
    DesignOptions designOptions = new DesignOptions();

    QuestionCollection questionCollection = new QuestionCollection();
    ObjectOutputStream out;
    private GameData game; //to store game data
    private Turn playerSide;
    boolean chosenCategory = false;

    public ContentFrame(ObjectOutputStream out) throws IOException {
        this.out = out;
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage();
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
        questionPage = new QuestionPage();
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

    public <T> void writeToServer(String message, T item) {
        try {
            if(item != null)
                out.writeObject(new Object[]{message, item});
            else
                out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void newGameStarted() {
        System.out.println("choose category");
        questionPage.setQuestionAmount(game.getQuestionsAmount());
        cardLayout.show(contentPanel, "ChooseCategoryPage");
        addActionListenerToOptions();
        chosenCategory = true;
    }

    public void getQuestions() {
        System.out.println("existing game found!");
        cardLayout.show(contentPanel, "QuestionPage");
        addActionListenerToOptions();
    }

    public void waitingForPlayer() {
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
            questionPage.newQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });
        chooseCategoryPage.getCategoryOption2().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption2().getText();
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.newQuestions(category);
            addActionListenerToOptions();
            cardLayout.show(contentPanel, "QuestionPage");
        });
        chooseCategoryPage.getCategoryOption3().addActionListener(ActiveEvent -> {
            category = chooseCategoryPage.getCategoryOption3().getText();
            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
            questionPage.newQuestions(category);
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
            if(chosenCategory) {
                questionPage.newQuestions(QuestionCategory.MUSIC.label);
                category = QuestionCategory.MUSIC.label;
                scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
                questionPage.newQuestions(category);
                addActionListenerToOptions();
                cardLayout.show(contentPanel, "QuestionPage");
                chosenCategory = false;
            }
            else{
                questionPage.newQuestions(questionCollection.getRandomCategory().label);
                SwingUtilities.invokeLater(() -> chooseCategoryPage.updateQuestionCategories());
                addActionListenerToOptions();
                cardLayout.show(contentPanel, "ChooseCategoryPage");
                chosenCategory = true;
            }
        });
    }


    public void addActionListerToStartPage(){
        startPage.getStartNewGame().addActionListener(ActionEvent -> writeToServer("new game",null));
    }

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
                    if (player1Round.size() < game.getQuestionsAmount()) {
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

                        if(!chosenCategory) {
                            cardLayout.show(contentPanel, "ScoreBoardPage");
                            scoreBoardPage.setGameID(gameID);
                            writeToServer("round finished", game);
                        }
                        else {
                            //TODO
                            category = QuestionCategory.MUSIC.label;
                            questionPage.newQuestions(category);
                            addActionListenerToOptions();
                            cardLayout.show(contentPanel, "QuestionPage");
                            newGameStarted();
                            writeToServer("round finished", game);
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

    public GameData getGame() {
        return game;
    }

    public void setGame(GameData game) {
        this.game = game;
    }

    public Turn getPlayerSide() {
        return playerSide;
    }

    public void setPlayerSide(Turn playerSide) {
        this.playerSide = playerSide;
    }

    public void generateRandomPlayer2List(){
        player2Round.add(true);
        player2Round.add(false);
        player2Round.add(false);
        Collections.shuffle(player2Round);
        player2Wins.add(new ArrayList<>(player2Round));
        System.out.println(player2Round.size());

    }
}
