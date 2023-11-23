package GUI.ScoreBoard;

import Question.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class ScoreBoardPage extends JPanel {
    String gameID;

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    JLabel scoreLabel;

    JButton playGame;

    String backgroundImagePath;
    Image backgroundImage;

    int player1 = 0;
    int player2 = 0;

    //Temporary list
    List<ScoreCount> scoreCounts;
    List<QuestionCategory> categoryList;

    List<List<Boolean>> player1ScoreList;
    List<List<Boolean>> player2ScoreList;

    public ScoreBoardPage(String gameID) throws IOException {
        this.gameID = gameID;

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        playGame = new JButton("SPELA");

        categoryList = new ArrayList<>();
        player1ScoreList = new ArrayList<>();
        player2ScoreList = new ArrayList<>();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = (new ImageIcon(backgroundImagePath)).getImage();
        scoreCounts = new ArrayList<>();
        addComponents();
    }

    public ScoreBoardPage() throws IOException {
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        playGame = new JButton("SPELA");

        categoryList = new ArrayList<>();
        player1ScoreList = new ArrayList<>();
        player2ScoreList = new ArrayList<>();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = (new ImageIcon(backgroundImagePath)).getImage();
        scoreCounts = new ArrayList<>();
        addComponents();
    }

    public void addComponents() throws IOException {
        setLayout(new BorderLayout());

        generateCenterPanel();
        generateNorthPanel();
        generateSouthPanel();
        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    public void generateCenterPanel() throws IOException {
        centerPanel.setPreferredSize(new Dimension(800, 500));
        centerPanel.setLayout(new GridLayout(6, 1));
        centerPanel.setOpaque(false);

        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.shuffleCategoryList();

        generateScoreCounts();

    }

    //TODO Set from frame
    public void generateScoreCounts() {
        for(int i = 0; i < 6; i++){
            ScoreCount scoreCountLabel;
            if(player1ScoreList.size() > i && player2ScoreList.size() > i){
                scoreCountLabel = new ScoreCount(player1ScoreList.get(i), player2ScoreList.get(i), categoryList.get(i));
            }
            else{
                scoreCountLabel = new ScoreCount();
            }
            centerPanel.add(scoreCountLabel);
        }
    }

    public void generateNorthPanel(){
        northPanel.setPreferredSize(new Dimension(800, 150));
        northPanel.setLayout(new GridLayout(1, 3));
        northPanel.setOpaque(false);

        JLabel yourLabel = new JLabel("YOU", SwingConstants.CENTER);
        JLabel opponentLabel = new JLabel("OPPONENT", SwingConstants.CENTER);

        JPanel middlePanel = new JPanel();
        JLabel turnLabel = new JLabel("DIN TUR", SwingConstants.CENTER);
        scoreLabel = new JLabel("0 - 1", SwingConstants.CENTER);
        setScores();
        scoreLabel.setFont(new Font("Cabin", Font.PLAIN, 22));

        middlePanel.setLayout(new GridLayout(2, 1));
        middlePanel.add(turnLabel);
        middlePanel.add(scoreLabel);
        middlePanel.setOpaque(false);

        setFont(yourLabel);
        setFont(opponentLabel);
        setFont(turnLabel);
        setFont(scoreLabel);

        northPanel.add(yourLabel);
        northPanel.add(middlePanel);
        northPanel.add(opponentLabel);
    }

    public void setFont (JLabel label){
        label.setFont(new Font("Montserrat", Font.PLAIN, 22));
        setOpaque(false);
    }

    public void generateSouthPanel(){
        southPanel.setLayout(new FlowLayout());
        southPanel.setPreferredSize(new Dimension(800, 100));
        southPanel.setOpaque(false);

        playGame.setBackground(Color.GREEN);
        playGame.setPreferredSize(new Dimension(200, 70));
        playGame.setFont(new Font("Montserrat", Font.PLAIN, 22));
        playGame.setBorder(new LineBorder(Color.BLUE, 5));

        southPanel.add(playGame);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void setWinList(List<List<Boolean>> player1ScoreList, List<List<Boolean>> player2ScoreList) {
        this.player1ScoreList = player1ScoreList;
        this.player2ScoreList = player2ScoreList;
    }

    public void updateScoreBoard() throws IOException {
        centerPanel.removeAll();
        northPanel.removeAll();
        southPanel.removeAll();

        generateCenterPanel();
        generateNorthPanel();
        generateSouthPanel();
    }

    public JButton getPlayGame() {
        return playGame;
    }

    public void addToCategoryList(QuestionCategory category){
        categoryList.add(category);
    }

    public void clearCategoryList() {
        categoryList.clear();
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    private int calculateScore(List<List<Boolean>> scoreList) {
        int score = 0;
        for (List<Boolean> results : scoreList) {
            for (Boolean result : results) {
                if (result) {
                    score++;
                }
            }
        }
        return score;
    }

    public void setScores(){
        player1 = calculateScore(player1ScoreList);
        player2 = calculateScore(player2ScoreList);
        scoreLabel.setText(player1 + " - " + player2);
    }
}
