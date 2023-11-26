package GUI.ScoreBoard;

import CustomTypes.GameData;
import CustomTypes.Round;
import GUI.DesignOptions;
import Question.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class ScoreBoardPage extends JPanel {
    String gameID;

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    JLabel scoreLabel;
    JLabel turnLabel;

    JButton playGame;

    DesignOptions designOptions;

    int player1 = 0;
    int player2 = 0;

    //Temporary list
    List<ScoreCount> scoreCounts;
    List<QuestionCategory> categoryList;

    List<List<Boolean>> player1ScoreList;
    List<List<Boolean>> player2ScoreList;

    public ScoreBoardPage(String gameID) throws IOException {
        this.gameID = gameID;

        designOptions = new DesignOptions();

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        playGame = new JButton("SPELA");
        turnLabel = new JLabel();
        setTurnLabel(true);

        categoryList = new ArrayList<>();
        categoryList.add(QuestionCategory.HISTORY);
        player1ScoreList = new ArrayList<>();
        player2ScoreList = new ArrayList<>();

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
                System.out.println(player1ScoreList + " : " + player2ScoreList + " : " + categoryList);
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
        yourLabel.setFont(designOptions.getSmallText());
        JLabel opponentLabel = new JLabel("OPPONENT", SwingConstants.CENTER);
        opponentLabel.setFont(designOptions.getSmallText());

        JPanel middlePanel = new JPanel();
        turnLabel = new JLabel("<html><div style='text-align: center; padding-top: 36px;'>Din tur", SwingConstants.CENTER);
        scoreLabel = new JLabel("<html><div style='text-align: center; vertical-align: top;'>" + player1 + "-" + player2 + "</div></html>", SwingConstants.CENTER);
        scoreLabel.setFont(designOptions.getSmallText());
        setScores();

        middlePanel.setLayout(new GridLayout(2, 1));
        middlePanel.add(turnLabel);
        middlePanel.add(scoreLabel);
        middlePanel.setOpaque(false);

        setFont(yourLabel);
        setFont(opponentLabel);
        setFont(turnLabel);

        northPanel.add(yourLabel);
        northPanel.add(middlePanel);
        northPanel.add(opponentLabel);
    }

    public void setFont (JLabel label){
        label.setFont(designOptions.getSmallText());
        setOpaque(false);
    }

    public void generateSouthPanel(){
        southPanel.setLayout(new FlowLayout());
        southPanel.setPreferredSize(new Dimension(800, 100));
        southPanel.setOpaque(false);

        playGame.setBackground(Color.GREEN);
        playGame.setPreferredSize(new Dimension(200, 70));
        playGame.setFont(designOptions.getSmallText());
        playGame.setBorder(designOptions.getBorder());

        southPanel.add(playGame);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (designOptions.getBackgroundImage() != null) {
            g.drawImage(designOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void setWinList(List<List<Boolean>> player1ScoreList, List<List<Boolean>> player2ScoreList) {
        this.player1ScoreList = player1ScoreList;
        this.player2ScoreList = player2ScoreList;
    }

    public void updateScoreBoard(GameData game) throws IOException {
        centerPanel.removeAll();
        northPanel.removeAll();
        southPanel.removeAll();

        categoryList.clear();
        player1ScoreList.clear();
        player2ScoreList.clear();
        for (Round round : game.getRounds()) {
            if(round.getPlayer1Score() != null)
                player1ScoreList.add(Arrays.stream(round.getPlayer1Score()).toList());
            if(round.getPlayer2Score() != null)
                player2ScoreList.add(Arrays.stream(round.getPlayer2Score()).toList());
            categoryList.add(round.getCategory());
        }

        generateCenterPanel();
        generateNorthPanel();
        generateSouthPanel();
    }
    
    public void updateScoreBoard() throws IOException{
        centerPanel.removeAll();
        northPanel.removeAll();
        southPanel.removeAll();

        generateCenterPanel();
        generateNorthPanel();
        generateSouthPanel();
    }

    public void hidePlayButton(){
        playGame.setVisible(false);
    }

    public void showPlayButton(){
        playGame.setVisible(true);
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
        System.out.println("score: " + score);
        return score;
    }

    public void setScores(){
        player1 = calculateScore(player1ScoreList);
        player2 = calculateScore(player2ScoreList);
        scoreLabel.setText(player1 + " - " + player2);
    }

    public void setDesignOptions(DesignOptions designOptions) {
        this.designOptions = designOptions;
    }

    public void setTurnLabel(Boolean yourTurn){
        if(yourTurn){
            turnLabel.setText("<html><div style='text-align: center; vertical-align: bottom;'>Din tur");
        }
        else{
            turnLabel.setText("<html><div style='text-align: center; vertical-align: bottom;'>Deras tur");
        }

    }
}
