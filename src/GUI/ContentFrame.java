package GUI;

import GUI.ScoreBoard.ScoreBoardPage;

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

    //Should be moved to game logic later:
    List<List<Boolean>> totalWins = new ArrayList<>();
    List<Boolean> currentWin = new ArrayList<>();
    String category = "Film";
    BufferedWriter out;
    private final List<String> games = new ArrayList<>();
    boolean chosenCategory = false;

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

        buildFrame();
    }

    //TODO Remove main
//    public static void main(String[] args) throws IOException {
//        ContentFrame contentFrame = new ContentFrame();
//    }

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
            out.write(message);
            out.newLine();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
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
        startPage.getStartNewGame().addActionListener(ActionEvent -> {
//            cardLayout.show(contentPanel, "WaitingPage");
            writeToServer("new game");
        });
//        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));

        //WAITING PAGE
        waitingPage.getTextButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));

        //CHOOSE CATEGORY PAGE
        chooseCategoryPage.getCategoryOption1().addActionListener(ActiveEvent -> cardLayout.show(contentPanel, "QuestionPage"));
        chooseCategoryPage.getCategoryOption2().addActionListener(ActiveEvent -> cardLayout.show(contentPanel, "QuestionPage"));
        chooseCategoryPage.getCategoryOption3().addActionListener(ActiveEvent -> cardLayout.show(contentPanel, "QuestionPage"));

        //QUESTION PAGE
//        addActionListenerToOptions();

        //SCORE BOARD PAGE
        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
            questionPage.nextThreeQuestions("Musik");
            cardLayout.show(contentPanel, "ChooseCategoryPage");
            addActionListenerToOptions(scoreBoardPage.getGameID());
        });

    }

    public void addActionListenerToOptions(String gameID) {
        List<JButton> optionButtons = questionPage.getOptionButtons();
        for (JButton option : optionButtons) {
            option.addActionListener(ActionEvent -> {
                if (option.getText().equals(questionPage.getAnswer())) {
                    System.out.println("right");
                    currentWin.add(true);
                } else {
                    System.out.println("wrong");
                    currentWin.add(false);
                }
                if (currentWin.size() < 3) {
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions(gameID);
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
                    cardLayout.show(contentPanel, "ScoreBoardPage");
                    chosenCategory = false;
                }
            });
        }
    }

    public List<String> getGames() {
        return games;
    }
}
