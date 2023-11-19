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

    public ContentFrame() throws IOException {
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();
        questionPage = new QuestionPage();

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

        add(contentPanel);
        addActionEvents();
        setVisible(true);
    }

    public void addActionEvents(){
        startPage.getStartNewGame().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));
        startPage.getCatButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "QuestionPage"));
    }

}
