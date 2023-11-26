package GUI;

import CustomTypes.GameData;
import CustomTypes.Round;
import GUI.CategoryGUI.CategoryButton;
import GUI.CategoryGUI.ChooseCategoryPage;
import GUI.ScoreBoard.ScoreBoardPage;
import GUI.StartPage.StartPage;
import Question.Question;
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
    List<Boolean> playerRound = new ArrayList<>();
    //    List<Boolean> player2Round = new ArrayList<>();
    QuestionCategory category = QuestionCategory.MOVIES;
    String gameID = "4556";
    DesignOptions designOptions = new DesignOptions();

    QuestionCollection questionCollection = new QuestionCollection();
    ObjectOutputStream out;
    private int amountOfQuestions;
    private int amountOfRounds;

    private GameData game;
    private Turn playerSide;
    boolean chosenCategory = false;

    public void setDesignOptions() {


    }

    public ContentFrame(ObjectOutputStream out, int amountOfQuestions, int amountOfRounds) throws IOException {
        this.amountOfQuestions = amountOfQuestions;
        this.amountOfRounds = amountOfRounds;
        this.out = out;
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage(amountOfQuestions);
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage(gameID, amountOfRounds, amountOfQuestions);
        settingsPage = new SettingsPage();

        buildFrame();
    }

    public ContentFrame() throws IOException {
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage(amountOfQuestions);
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage(gameID, amountOfRounds, amountOfQuestions);

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

    public synchronized <T> void writeToServer(String message, T item) {
        try {
            if (item != null)
                out.writeObject(new Object[]{message, item});
            else
                out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void newGameStarted() {
        System.out.println("new game started");
        cardLayout.show(contentPanel, "ChooseCategoryPage");
        addActionListenerToOptions();
        chosenCategory = true;
    }

    public void getQuestions() {
        System.out.println("existing game found!");
        questionPage.setQuestionPage(game.getRounds().get(0).getCategory(), game.getRounds().get(0).getQuestions());
        cardLayout.show(contentPanel, "QuestionPage");
        addActionListenerToOptions();
    }

    public void waitingForPlayer() {
        System.out.println("waiting for player method");
        scoreBoardPage.hidePlayButton();
        cardLayout.show(contentPanel, "ScoreBoardPage");
    }

    public void addActionEvents() {

        //START PAGE
        addActionListerToStartPage();

        //WAITING PAGE
        waitingPage.getTextButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));

        //CHOOSE CATEGORY PAGE
        for (CategoryButton categoryOption : chooseCategoryPage.getCategoryOptions()) {
            categoryOption.addActionListener(ActiveEvent -> {
                category = QuestionCategory.getQuestionCategory(categoryOption.getText());
                scoreBoardPage.addToCategoryList(category);
                questionPage.newQuestions(category);
                addActionListenerToOptions();
                cardLayout.show(contentPanel, "QuestionPage");
            });
        }

        //QUESTION PAGE
        //ActionListener till inställningsknapp
        startPage.getSettings().addActionListener(e -> {
            System.out.println("Settings Button Clicked!");
            cardLayout.show(contentPanel, "SettingsPage");
        });

        //SCORE BOARD PAGE
        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
            if (chosenCategory) {
                questionPage.newQuestions(category);
                scoreBoardPage.addToCategoryList(category);
                questionPage.newQuestions(category);
                addActionListenerToOptions();
                cardLayout.show(contentPanel, "QuestionPage");
                chosenCategory = false;
            } else {
                questionPage.newQuestions(questionCollection.getRandomCategory());
                SwingUtilities.invokeLater(() -> chooseCategoryPage.updateQuestionCategories());
                addActionListenerToOptions();
                cardLayout.show(contentPanel, "ChooseCategoryPage");
                chosenCategory = true;
            }
        });
    }


    public void addActionListerToStartPage() {
        startPage.getStartNewGame().addActionListener(ActionEvent -> {
            writeToServer("new game", null);
        });
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
                    if (playerRound.size() < amountOfQuestions) {
                        questionPage.nextQuestion();
                        cardLayout.show(contentPanel, "QuestionPage");
                        addActionListenerToOptions();
                    } else {
                        if (chosenCategory) {
                            game.setTurn(game.getTurn() == Turn.Player1 ? Turn.Player2 : Turn.Player1);
                            Question[] tempQuestions = new Question[amountOfQuestions];
                            Boolean[] tempScore = new Boolean[amountOfQuestions];
                            for (int i = 0; i < amountOfQuestions; i++) {
                                tempQuestions[i] = questionPage.questions.get(i);
                                tempScore[i] = playerRound.get(i);
                            }
                            game.addRound(new Round(questionPage.category, tempQuestions, tempScore, playerSide));
                            if (playerSide == Turn.Player1) {
                                player1Wins.add(new ArrayList<>(playerRound));
                                game.getRounds().get(game.getRounds().size() - 1).setPlayer2Score(new Boolean[0]);
                                playerRound.clear();
                            } else {
                                player2Wins.add(new ArrayList<>(playerRound));
                                game.getRounds().get(game.getRounds().size() - 1).setPlayer1Score(new Boolean[0]);
                                playerRound.clear();
                            }
                            writeToServer("round finished", game);
                            cardLayout.show(contentPanel, "ScoreBoardPage");
                        } else {
                            System.out.println("time to choose category");
                            if (playerSide == Turn.Player1) {
                                game.getRounds().get(game.getRounds().size() - 1).setPlayer1Score(playerRound.toArray(new Boolean[0]));
                            } else {
                                game.getRounds().get(game.getRounds().size() - 1).setPlayer2Score(playerRound.toArray(new Boolean[0]));
                            }
                            playerRound.clear();
                            cardLayout.show(contentPanel, "ChooseCategoryPage");
                            chosenCategory = true;
                        }
                        try {
                            scoreBoardPage.updateScoreBoard(game);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if(playerSide != game.getTurn())
                            scoreBoardPage.hidePlayButton();
                        cardLayout.show(contentPanel, "ScoreBoardPage");
                    }
                });
                timer.setRepeats(false);
                timer.start();
            });
        }
    }

    public void checkIfWin(JButton option) {
        JButton rightAnswer = checkRightAnswer();
        if (!option.equals(rightAnswer)) {
            option.setBackground(Color.red);
            System.out.println("wrong");
            playerRound.add(false);
        } else {
            System.out.println("right");
            playerRound.add(true);
        }
        rightAnswer.setBackground(Color.green);
        option.repaint();
        option.revalidate();
        rightAnswer.repaint();
        rightAnswer.revalidate();
    }

    public JButton checkRightAnswer() {
        List<JButton> optionButtons = questionPage.getOptionButtons();
        JButton rightAnswer = new JButton();
        for (JButton option : optionButtons) {
            if (option.getText().equals("<html><div style='text-align: center;'>" + questionPage.getAnswer())) {
                rightAnswer = option;
            }
        }
        return rightAnswer;
    }

    public void showScoreBoardPage() {
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
}
