package GUI;

import CustomTypes.GameData;
import CustomTypes.Round;
import Enums.ImageIconAvatar;
import GUI.CategoryGUI.CategoryButton;
import GUI.CategoryGUI.ChooseCategoryPage;
import GUI.ScoreBoard.ScoreBoardPage;
import GUI.StartPage.StartPage;
import Question.Question;
import Enums.QuestionCategory;
import Question.QuestionCollection;
import Enums.Turn;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class ContentFrame extends JFrame implements Serializable {

    JPanel contentPanel;

    JMenuBar menuBar;
    JMenu settingsMenu;
    JMenu backgroundMenu;
    JMenu avatarMenu;
    JMenuItem itemExit;
    JMenuItem itemSelectViolet;
    JMenuItem itemSelectBlue;
    JMenuItem itemSelectGreen;
    JMenuItem itemSelectPig;
    JMenuItem itemSelectAvatar2;
    JMenuItem itemSelectAvatar3;


    CardLayout cardLayout;
    StartPage startPage;
    ChooseCategoryPage chooseCategoryPage;
    QuestionPage questionPage;
    WaitingPage waitingPage;
    ScoreBoardPage scoreBoardPage;
    SettingsPage settingsPage;
    ResultPage resultPage;

    //Should be moved to game logic later:
    List<List<Boolean>> player1Wins = new ArrayList<>();
    List<List<Boolean>> player2Wins = new ArrayList<>();
    List<Boolean> playerRound = new ArrayList<>();
    //    List<Boolean> player2Round = new ArrayList<>();
    QuestionCategory category = QuestionCategory.MOVIES;
    String gameID = "4556";
    SettingsOptions settingsOptions;

    QuestionCollection questionCollection = new QuestionCollection();
    ObjectOutputStream out;
    int test1 = 0;
    boolean chosenCategory = false;
    private final int amountOfQuestions;
    private final int amountOfRounds;
    private GameData game;
    private Turn playerSide;

    public ContentFrame(ObjectOutputStream out, int amountOfQuestions, int amountOfRounds) throws IOException {
        this.amountOfQuestions = amountOfQuestions;
        this.amountOfRounds = amountOfRounds;
        this.out = out;
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage(amountOfQuestions, () -> {
            playerRound.add(false);
            helpMe();
        });
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage(gameID, amountOfRounds, amountOfQuestions);
        settingsPage = new SettingsPage();
        //resultPage = new ResultPage();
        settingsOptions = new SettingsOptions();

        //Provat lila tema, ändra fram och tillbaka och kika
        settingsOptions.setColor("hejsan");
        settingsOptions.setPlayer1("Sania");
        settingsOptions.setPlayer2("Simon");
        settingsOptions.setIcon(ImageIconAvatar.COW.iconPath);
        settingsOptions.setPlayer2Icon(ImageIconAvatar.MONKEY.iconPath);
        setDesignOptions();
        setIconAndPlayerName();
        createMenu();
        buildFrame();
    }

    public void setIconAndPlayerName() {
        questionPage.setIconAndPlayerNames(this.settingsOptions);
        scoreBoardPage.setIconAndPlayerName(this.settingsOptions);
       // resultPage.setIconAndPlayerName(this.settingsOptions);
    }

    public void setDesignOptions() {
        startPage.setDesignOptions(this.settingsOptions);
        chooseCategoryPage.setDesignOptions(this.settingsOptions);
        questionPage.setDesignOptions(this.settingsOptions);
        scoreBoardPage.setDesignOptions(this.settingsOptions);
        settingsPage.setDesignOptions(this.settingsOptions);
      //  resultPage.setDesignOptions(this.settingsOptions);
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
      //  contentPanel.add(resultPage, "ResultPage");

        add(contentPanel);
        addActionEvents();
        setVisible(true);
    }

    private void createMenu() {
        Font menuFont = new Font("Arial", Font.BOLD, 14);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        settingsMenu = new JMenu("Settings");
        backgroundMenu = new JMenu("Customize background");
        avatarMenu = new JMenu("Select avatar");
        menuBar.add(settingsMenu);
        settingsMenu.add(backgroundMenu);
        settingsMenu.add(avatarMenu);
        settingsMenu.setFont(menuFont);

        itemSelectViolet = new JMenuItem("Violet");
        itemSelectGreen = new JMenuItem("Green");
        itemSelectBlue = new JMenuItem("Blue");
        itemSelectPig = new JMenuItem("Pig");
        itemSelectAvatar2 = new JMenuItem("Lobster");
        itemSelectAvatar3 = new JMenuItem("Monkey");
        itemExit = new JMenuItem("Exit the game");
        settingsMenu.add(itemExit);

        backgroundMenu.add(itemSelectViolet);
        backgroundMenu.add(itemSelectGreen);
        backgroundMenu.add(itemSelectBlue);
        avatarMenu.add(itemSelectPig);
        avatarMenu.add(itemSelectAvatar2);
        avatarMenu.add(itemSelectAvatar3);
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
        scoreBoardPage.clearScoreBoard();
        cardLayout.show(contentPanel, "ChooseCategoryPage");
        addActionListenerToOptions();
        chosenCategory = true;
    }

    public void getQuestions() throws IOException {
        System.out.println("existing game found!");
        scoreBoardPage.clearScoreBoard();
        scoreBoardPage.updateScoreBoard(game);
        showScoreBoardPage();
        scoreBoardPage.showPlayButton();
    }

    public void waitingForPlayer() {
        chosenCategory = true;
        System.out.println("waiting for player method");
        scoreBoardPage.hidePlayButton();
        showScoreBoardPage();
    }

    public void addActionEvents() {
        //SETTINGS MENU
        addActionListenerToSettingsMenu();

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
                questionPage.getProgressBar().start();
            });
        }

        //QUESTION PAGE
        questionPage.getNextQuestion().addActionListener(ActiveEvent -> {
            playerRound.add(false);
            helpMe();
        });

        //ActionListener till inställningsknapp
        startPage.getSettings().addActionListener(e -> {
            System.out.println("Settings Button Clicked!");
            cardLayout.show(contentPanel, "SettingsPage");
        });

        //SCORE BOARD PAGE
        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
            boolean player1LatestEmpty = game.getRounds().get(game.getRounds().size() - 1).getPlayer1Score().length == 0;
            boolean player1LatestNotEmpty = game.getRounds().get(game.getRounds().size() - 1).getPlayer1Score().length != 0;
            boolean player2LatestEmpty = game.getRounds().get(game.getRounds().size() - 1).getPlayer2Score().length == 0;
            boolean player2LatestNotEmpty = game.getRounds().get(game.getRounds().size() - 1).getPlayer2Score().length != 0;
            if ((player1LatestEmpty && player2LatestNotEmpty) || (player2LatestEmpty && player1LatestNotEmpty)) {
                questionPage.setQuestionPage(game.getRounds().get(game.getRounds().size() - 1).getCategory(), game.getRounds().get(game.getRounds().size() - 1).getQuestions());
                cardLayout.show(contentPanel, "QuestionPage");
                addActionListenerToOptions();
                chosenCategory = false;
                questionPage.getProgressBar().start();
            } else {
                questionPage.newQuestions(questionCollection.getRandomCategory());
                SwingUtilities.invokeLater(() -> chooseCategoryPage.updateQuestionCategories());
                cardLayout.show(contentPanel, "ChooseCategoryPage");
                //TODO why add action listener to options?
//                addActionListenerToOptions();
                chosenCategory = true;
            }
        });
    }

    private void addActionListenerToSettingsMenu() {
        itemSelectViolet.addActionListener(e -> {

            settingsOptions.setColor("violet");
            setDesignOptions();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        itemSelectGreen.addActionListener(e -> {
            settingsOptions.setColor("green");
            setDesignOptions();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        itemSelectBlue.addActionListener(e -> {
            settingsOptions.setColor("sören");
            setDesignOptions();
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        itemSelectPig.addActionListener(e -> {
            System.out.println("Gris");
            settingsOptions.setIcon(ImageIconAvatar.PIG.iconPath);
            setIconAndPlayerName();
            getContentPane().revalidate();
            getContentPane().repaint();
        });
    }

    public void addActionListerToStartPage() {
        startPage.getStartNewGame().addActionListener(ActionEvent ->
            writeToServer("new game", null)
        );
      //  startPage.getNotifications().addActionListener(ActionEvent -> {
      //      cardLayout.show(contentPanel, "ResultPage");
      //  });
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
                helpMe();
            });
        }
    }

    public void helpMe() {

        Timer timer = new Timer(500, evt -> {
            if (playerRound.size() < amountOfQuestions) {
                questionPage.nextQuestion();
                cardLayout.show(contentPanel, "QuestionPage");
                addActionListenerToOptions();
                questionPage.getProgressBar().start();
            } else {
                System.out.println("Answers: " + playerRound.size());
                System.out.println("Amount: " + amountOfQuestions);

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
                } else {
                    System.out.println("time to choose category");
                    if (playerSide == Turn.Player1) {
                        game.getRounds().get(game.getRounds().size() - 1).setPlayer1Score(playerRound.toArray(new Boolean[0]));
                    } else {
                        game.getRounds().get(game.getRounds().size() - 1).setPlayer2Score(playerRound.toArray(new Boolean[0]));
                    }
                    playerRound.clear();
                }

                if (amountOfRounds == game.getRounds().size() && game.getRounds().get(amountOfRounds - 1).getPlayer1Score().length == amountOfQuestions &&
                        game.getRounds().get(amountOfRounds - 1).getPlayer2Score().length == amountOfQuestions) {
                    writeToServer("game finished", game);
                }
                scoreBoardPage.updateScoreBoard(game);
                if (playerSide != game.getTurn())
                    scoreBoardPage.hidePlayButton();
                showScoreBoardPage();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void showResultPage() {
        resultPage = new ResultPage(scoreBoardPage.getPlayer(),scoreBoardPage.getOpponent());
        resultPage.setIconAndPlayerName(this.settingsOptions);
        resultPage.setDesignOptions(this.settingsOptions);
        contentPanel.add(resultPage, "ResultPage");
        startPage.getNotifications().addActionListener(ActionEvent -> {
            cardLayout.show(contentPanel, "ResultPage");
        });

        cardLayout.show(contentPanel, "ResultPage");
    }
    public void checkIfWin(JButton option) {
        JButton rightAnswer = checkRightAnswer();
        if (!option.equals(rightAnswer)) {
            option.setBackground(Color.red);
            playerRound.add(false);
        } else {
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
        scoreBoardPage.setTurnLabel(playerSide == game.getTurn());
        cardLayout.show(contentPanel, "ScoreBoardPage");
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
        scoreBoardPage.setPlayerSide(playerSide);
    }

    public void setChosenCategory(boolean chosenCategory) {
        this.chosenCategory = chosenCategory;
    }
}
