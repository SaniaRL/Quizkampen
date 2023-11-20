package GUI;

import javax.swing.*;
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

    public ContentFrame() throws IOException {
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage();
        waitingPage = new WaitingPage();
        scoreBoardPage = new ScoreBoardPage();

        buildFrame();
    }

    //TODO Remove main
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("unused")
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
    }
}
