//package GUI;
//
//import GUI.CategoryGUI.ChooseCategoryPage;
//import GUI.ScoreBoard.ScoreBoardPage;
//import GUI.StartPage.StartPage;
//import Question.QuestionCategory;
//import Question.QuestionCollection;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class TestFrame extends JFrame {
//    JPanel contentPanel;
//    CardLayout cardLayout;
//
//    StartPage startPage;
//    ChooseCategoryPage chooseCategoryPage;
//    QuestionPage questionPage;
//    WaitingPage waitingPage;
//    ScoreBoardPage scoreBoardPage;
//
//    SettingsPage settingsPage;
//
//    //Should be moved to game logic later:
//    java.util.List<java.util.List<Boolean>> player1Wins = new ArrayList<>();
//    java.util.List<java.util.List<Boolean>> player2Wins = new ArrayList<>();
//    java.util.List<Boolean> player1Round = new ArrayList<>();
//    java.util.List<Boolean> player2Round = new ArrayList<>();
//    String category = "Film";
//    String gameID = "4556";
//
//    QuestionCollection questionCollection = new QuestionCollection();
//    ObjectOutputStream out;
//
//    private final List<String> games = new ArrayList<>();
//    boolean chosenCategory = true;
//
//    DesignOptions designOptions;
//
//
//    public TestFrame() throws IOException {
//        designOptions = new DesignOptions();
//        designOptions.setColor("violet");
//        contentPanel = new JPanel();
//        cardLayout = new CardLayout();
//        contentPanel.setLayout(cardLayout);
//
//        startPage = new StartPage();
//        startPage.setDesignOptions(this.designOptions);
//
//        chooseCategoryPage = new ChooseCategoryPage();
//        chooseCategoryPage.setDesignOptions(this.designOptions);
//
//        questionPage = new QuestionPage(category);
//        questionPage.setDesignOptions(this.designOptions);
//
//        waitingPage = new WaitingPage();
//
//        scoreBoardPage = new ScoreBoardPage(gameID);
//        scoreBoardPage.setDesignOptions(this.designOptions);
//
//        settingsPage = new SettingsPage();
//        settingsPage.setDesignOptions(this.designOptions);
//
//        buildFrame();
//    }
//
//    public static void main(String[] args) throws IOException {
//        @SuppressWarnings("unused")
//        TestFrame testFrame = new TestFrame();
//    }
//
//    public void buildFrame() {
//        setSize(800, 800);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setTitle("Quizkampen");
//        ImageIcon image = new ImageIcon("Icons/Sun.png");
//        setIconImage(image.getImage());
//
//        contentPanel.add(startPage, "StartPage");
//        contentPanel.add(chooseCategoryPage, "ChooseCategoryPage");
//        contentPanel.add(questionPage, "QuestionPage");
//        contentPanel.add(waitingPage, "WaitingPage");
//        contentPanel.add(scoreBoardPage, "ScoreBoardPage");
//
//        contentPanel.add(settingsPage, "SettingsPage");
//
//        add(contentPanel);
//        addActionEvents();
//        setVisible(true);
//    }
//
//    public void addActionEvents() {
//
//        //START PAGE
//        addActionListerToStartPage();
//
//        //WAITING PAGE
//        waitingPage.getTextButton().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));
//
//        //CHOOSE CATEGORY PAGE
//        chooseCategoryPage.getCategoryOption1().addActionListener(ActiveEvent -> {
//            category = chooseCategoryPage.getCategoryOption1().getText();
//            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
//            questionPage.nextThreeQuestions(category);
//            addActionListenerToOptions();
//            cardLayout.show(contentPanel, "QuestionPage");
//        });
//        chooseCategoryPage.getCategoryOption2().addActionListener(ActiveEvent -> {
//            category = chooseCategoryPage.getCategoryOption2().getText();
//            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
//            questionPage.nextThreeQuestions(category);
//            addActionListenerToOptions();
//            cardLayout.show(contentPanel, "QuestionPage");
//        });
//        chooseCategoryPage.getCategoryOption3().addActionListener(ActiveEvent -> {
//            category = chooseCategoryPage.getCategoryOption3().getText();
//            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
//            questionPage.nextThreeQuestions(category);
//            addActionListenerToOptions();
//            cardLayout.show(contentPanel, "QuestionPage");
//        });
//
//        //QUESTION PAGE
//        //Simon Ändring. ActionListener till inställningsknapp
//        startPage.getSettings().addActionListener(e -> {
//            System.out.println("Settings Button Clicked!");
//            cardLayout.show(contentPanel, "SettingsPage");
//        });
//
//        //SCORE BOARD PAGE
//        scoreBoardPage.getPlayGame().addActionListener(ActionEvent -> {
//            if(chosenCategory) {
//                questionPage.nextThreeQuestions(QuestionCategory.MUSIC.label);
//                category = QuestionCategory.MUSIC.label;
//                scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
//                questionPage.nextThreeQuestions(category);
//                addActionListenerToOptions();
//                cardLayout.show(contentPanel, "QuestionPage");
//                chosenCategory = false;
//
//            }
//            else{
//                questionPage.nextThreeQuestions(questionCollection.getRandomCategory().label);
//                SwingUtilities.invokeLater(() -> chooseCategoryPage.updateQuestionCategories());
//                addActionListenerToOptions();
//                cardLayout.show(contentPanel, "ChooseCategoryPage");
//                chosenCategory = true;
//            }
//
//        });
//    }
//
//
//    public void addActionListerToStartPage(){
//        startPage.getStartNewGame().addActionListener(ActionEvent -> cardLayout.show(contentPanel, "ChooseCategoryPage"));
//    }
//
//    public void addActionListenerToOptions() {
//        List<JButton> optionButtons = questionPage.getOptionButtons();
//
//        for (JButton option : optionButtons) {
//            // Remove ActionListeners, to get the delay to work on every question
//            ActionListener[] actionListeners = option.getActionListeners();
//            for (ActionListener listener : actionListeners) {
//                option.removeActionListener(listener);
//            }
//
//            option.addActionListener(e -> {
//                checkIfWin(option);
//
//                Timer timer = new Timer(3000, evt -> {
//                    if (player1Round.size() < 3) {
//                        questionPage.nextQuestion();
//                        cardLayout.show(contentPanel, "QuestionPage");
//                        addActionListenerToOptions();
//                    } else {
//                        generateRandomPlayer2List();
//                        player1Wins.add(new ArrayList<>(player1Round));
//                        player2Wins.add(new ArrayList<>(player2Round));
//                        player1Round.clear();
//                        player2Round.clear();
//                        scoreBoardPage.setWinList(player1Wins, player2Wins);
//
//                        if(chosenCategory) {
//                            cardLayout.show(contentPanel, "ScoreBoardPage");
//                            scoreBoardPage.setGameID(gameID);
//                        }
//                        else {
//                            //TODO
//                            category = QuestionCategory.MUSIC.label;
//                            scoreBoardPage.addToCategoryList(QuestionCategory.getQuestionCategory(category));
//                            questionPage.nextThreeQuestions(category);
//                            addActionListenerToOptions();
//                            cardLayout.show(contentPanel, "QuestionPage");
//                        }
//                        try {
//                            scoreBoardPage.setWinList(player1Wins, player2Wins);
//                            scoreBoardPage.updateScoreBoard();
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                        cardLayout.show(contentPanel, "ScoreBoardPage");
//                    }
//                });
//                timer.setRepeats(false);
//                timer.start();
//            });
//        }
//    }
//
//    public void checkIfWin(JButton option){
//        if (option.getText().equals("<html><div style='text-align: center;'>" + questionPage.getAnswer())) {
//            option.setBackground(Color.green);
//            System.out.println("right");
//            player1Round.add(true);
//        } else {
//            option.setBackground(Color.red);
//            System.out.println("wrong");
//            player1Round.add(false);
//        }
//        option.repaint();
//        option.revalidate();
//    }
//
//
//    public void showScoreBoardPage(){
//        cardLayout.show(contentPanel, "ScoreBoardPage");
//        chosenCategory = false;
//    }
//
//    //Needed for NetWork
//
//    public List<String> getGames() {
//        return games;
//    }
//
//    public void generateRandomPlayer2List(){
//        player2Round.add(true);
//        player2Round.add(false);
//        player2Round.add(false);
//        System.out.println(player2Round);
//        Collections.shuffle(player2Round);
//        System.out.println(player2Round);
//        player2Wins.add(new ArrayList<>(player2Round));
//        System.out.println(player2Round.size());
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//}
