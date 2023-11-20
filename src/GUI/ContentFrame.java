package GUI;

import GUI.ScoreBoard.ScoreBoardPage;

import javax.swing.*;
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

    public ContentFrame() throws IOException {
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
    public static void main(String[] args) throws IOException {
        ContentFrame contentFrame = new ContentFrame();
    }

    public void buildFrame(){
        setSize(800,800);
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

    public void addActionEvents(){

        //START PAGE
        startPage.getStartNewGame().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "WaitingPage"));
        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ScoreBoardPage"));

        //WAITING PAGE
        waitingPage.getTextButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));

        //CHOOSE CATEGORY PAGE
        chooseCategoryPage.getCategoryOption1().addActionListener(ActiveEvent -> cardLayout.show(contentPanel, "QuestionPage"));
        chooseCategoryPage.getCategoryOption2().addActionListener(ActiveEvent -> cardLayout.show(contentPanel, "QuestionPage"));
        chooseCategoryPage.getCategoryOption3().addActionListener(ActiveEvent -> cardLayout.show(contentPanel, "QuestionPage"));

        //QUESTION PAGE
        addActionListenerToOptions();

        //SCORE BOARD PAGE
        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
            questionPage.nextThreeQuestions("Musik");
            cardLayout.show(contentPanel, "ChooseCategoryPage");
            addActionListenerToOptions();
        });

    }

    public void addActionListenerToOptions(){
        List<JButton> optionButtons = questionPage.getOptionButtons();
        for(JButton option : optionButtons){
            option.addActionListener(ActionEvent -> {
                if(option.getText().equals(questionPage.getAnswer())){
                    currentWin.add(true);
                }
                else{
                    currentWin.add(false);
                }
                if(currentWin.size() < 3){
                    questionPage.nextQuestion();
                    cardLayout.show(contentPanel, "QuestionPage");
                    addActionListenerToOptions();
                }
                else{
                    totalWins.add(new ArrayList<>(currentWin));
                    currentWin.clear();
                    scoreBoardPage.setWinList(totalWins);
                    try {
                        scoreBoardPage.updateScoreBoard();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    cardLayout.show(contentPanel, "ScoreBoardPage");
                }
            });
        }
    }
}
