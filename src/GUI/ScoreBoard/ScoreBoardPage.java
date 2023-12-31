package GUI.ScoreBoard;

import CustomTypes.GameData;
import CustomTypes.Round;
import Enums.Turn;
import GUI.SettingsOptions;
import Enums.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import javax.swing.border.Border;
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

    JPanel yourPanel;
    JPanel opponentPanel;

    JButton playGame;
    JButton giveUp;

    SettingsOptions settingsOptions;

    int player; //Tidigare = 0;
    int opponent; //Tidigare = 0;
    private Turn playerSide;

    private List<ScoreCount> scoreCounts;
    private List<QuestionCategory> categoryList;
    private List<List<Boolean>> playerScoreList;
    private List<List<Boolean>> opponentScoreList;
    private final int amountOfRounds;
    private final int amountOfQuestions;

    public ScoreBoardPage(String gameID, int amountOfRounds, int amountOfQuestions) throws IOException {
        this.amountOfQuestions = amountOfQuestions;
        this.amountOfRounds = amountOfRounds;
        this.gameID = gameID;

        settingsOptions = new SettingsOptions();
        //TODO get from server, from Other players GUI

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        playGame = new JButton("SPELA");
        giveUp = new JButton("GE UPP");
        turnLabel = new JLabel();
        setTurnLabel(true);

        yourPanel = new JPanel();
        opponentPanel = new JPanel();

        categoryList = new ArrayList<>();
        playerScoreList = new ArrayList<>();
        opponentScoreList = new ArrayList<>();

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
        for (int i = 0; i < amountOfRounds; i++) {
            ScoreCount sc = new ScoreCount(amountOfQuestions);
            scoreCounts.add(sc);
            centerPanel.add(sc);
        }

        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.shuffleCategoryList();

//        generateScoreCounts();

    }

    //TODO Set from frame
//    public void generateScoreCounts() {
//        for (int i = 0; i < amountOfRounds; i++) {
//            ScoreCount scoreCountLabel;
//            if (player1ScoreList.size() > i && player2ScoreList.size() > i) {
//                scoreCountLabel = new ScoreCount(player1ScoreList.get(i), player2ScoreList.get(i), categoryList.get(i), amountOfQuestions);
//            } else {
//                scoreCountLabel = new ScoreCount(amountOfQuestions);
//            }
//            centerPanel.add(scoreCountLabel);
//        }
//    }

    public void generateNorthPanel() {
        northPanel.setPreferredSize(new Dimension(800, 150));
        northPanel.setLayout(new GridLayout(1, 3));
        northPanel.setOpaque(false);

        createIconPanel(yourPanel, settingsOptions.getIcon(), settingsOptions.getPlayer1());

        createIconPanel(opponentPanel, settingsOptions.getPlayer2Icon(), settingsOptions.getPlayer2());

        JPanel middlePanel = new JPanel();
        turnLabel = new JLabel("<html><div style='text-align: center; padding-top: 36px;'>Din tur", SwingConstants.CENTER);
        scoreLabel = new JLabel("<html><div style='text-align: center; vertical-align: top;'>" + player + "-" + opponent + "</div></html>", SwingConstants.CENTER);
        scoreLabel.setFont(settingsOptions.getSmallText());
        setScores();

        middlePanel.setLayout(new GridLayout(2, 1));
        middlePanel.add(turnLabel);
        middlePanel.add(scoreLabel);
        middlePanel.setOpaque(false);

        setFont(turnLabel);

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        northPanel.setBorder(emptyBorder);

        northPanel.add(yourPanel);
        northPanel.add(middlePanel);
        northPanel.add(opponentPanel);
    }

    public void createIconPanel(JPanel panel, ImageIcon imageIcon, String text) {
        JLabel iconLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(200, 100));

        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(200, 30));

        panel.setLayout(new GridLayout(2, 1));
        panel.setPreferredSize(new Dimension(200, 150));
        panel.setOpaque(false);
        panel.add(iconLabel);
        panel.add(textLabel);
    }

    public void setFont(JLabel label) {
        label.setFont(settingsOptions.getSmallText());
        setOpaque(false);
    }

    public void generateSouthPanel() {
        southPanel.setLayout(new FlowLayout());
        southPanel.setPreferredSize(new Dimension(800, 100));
        southPanel.setOpaque(false);

        playGame.setBackground(Color.GREEN);
        playGame.setPreferredSize(new Dimension(200, 70));
        playGame.setFont(settingsOptions.getSmallText());
        playGame.setBorder(settingsOptions.getBorder());

        giveUp.setBackground(Color.ORANGE);
        giveUp.setPreferredSize(new Dimension(200, 70));
        giveUp.setFont(settingsOptions.getSmallText());
        giveUp.setBorder(settingsOptions.getBorder());

        southPanel.add(playGame);
        southPanel.add(giveUp);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (settingsOptions.getBackgroundImage() != null) {
            g.drawImage(settingsOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void setWinList(List<List<Boolean>> playerScoreList, List<List<Boolean>> opponentScoreList) {
        this.playerScoreList = playerScoreList;
        this.opponentScoreList = opponentScoreList;
    }

    public void updateScoreBoard(GameData game) {
        categoryList.clear();
        playerScoreList.clear();
        opponentScoreList.clear();
        if (playerSide == Turn.Player1) {
            updatePlayer1(game);
        } else {
            updatePlayer2(game);
        }
        setScores();
    }

    private void updatePlayer1(GameData game) {
        int i = 0;
        for (Round round : game.getRounds()) {
            if (round.getPlayer1Score().length != 0 && round.getPlayer2Score().length != 0) {
                int j = 0;
                for (ScoreLabel playerLabel : scoreCounts.get(i).getPlayerLabels()) {
                    playerLabel.setForeground(round.getPlayer1Score()[j] ? Color.green : Color.RED);
                    j++;
                }
                j = 0;
                for (ScoreLabel opponentLabel : scoreCounts.get(i).getOpponentLabels()) {
                    opponentLabel.setForeground(round.getPlayer2Score()[j] ? Color.green : Color.RED);
                    j++;
                }
                playerScoreList.add(Arrays.stream(round.getPlayer1Score()).toList());
                opponentScoreList.add(Arrays.stream(round.getPlayer2Score()).toList());
            }
            if (round.getPlayer1Score().length != 0 && round.getPlayer2Score().length == 0) {
                int j = 0;
                for (ScoreLabel player1Label : scoreCounts.get(i).getPlayerLabels()) {
                    player1Label.setForeground(round.getPlayer1Score()[j] ? Color.green : Color.RED);
                    j++;
                }
                playerScoreList.add(Arrays.stream(round.getPlayer1Score()).toList());
            }
            scoreCounts.get(i).setCategoryLabel(round.getCategory().label);
            i++;
        }
    }

    private void updatePlayer2(GameData game) {
        int i = 0;
        for (Round round : game.getRounds()) {
            if (round.getPlayer1Score().length != 0 && round.getPlayer2Score().length != 0) {
                int j = 0;
                for (ScoreLabel playerLabel : scoreCounts.get(i).getPlayerLabels()) {
                    playerLabel.setForeground(round.getPlayer2Score()[j] ? Color.green : Color.RED);
                    j++;
                }
                j = 0;
                for (ScoreLabel opponentLabel : scoreCounts.get(i).getOpponentLabels()) {
                    opponentLabel.setForeground(round.getPlayer1Score()[j] ? Color.green : Color.RED);
                    j++;
                }
                playerScoreList.add(Arrays.stream(round.getPlayer2Score()).toList());
                opponentScoreList.add(Arrays.stream(round.getPlayer1Score()).toList());
            }
            if (round.getPlayer2Score().length != 0 && round.getPlayer1Score().length == 0) {
                int j = 0;
                for (ScoreLabel playerLabel : scoreCounts.get(i).getPlayerLabels()) {
                    playerLabel.setForeground(round.getPlayer2Score()[j] ? Color.green : Color.RED);
                    j++;
                }
                playerScoreList.add(Arrays.stream(round.getPlayer2Score()).toList());
            }
            scoreCounts.get(i).setCategoryLabel(round.getCategory().label);
            i++;
        }
    }

    public void clearScoreBoard() {
        for (ScoreCount scoreCount : scoreCounts) {
            scoreCount.setCategoryLabel("");
            for (ScoreLabel playerLabel : scoreCount.getPlayerLabels()) {
                playerLabel.setForeground(settingsOptions.getColor());
            }
            for (ScoreLabel opponentLabel : scoreCount.getOpponentLabels()) {
                opponentLabel.setForeground(settingsOptions.getColor());
            }
        }
    }

    public void hidePlayButton() {
        playGame.setVisible(false);
    }

    public void showPlayButton() {
        playGame.setVisible(true);
    }

    public void setPlayerSide(Turn playerSide) {
        this.playerSide = playerSide;
    }

    public Turn getPlayerSide() {
        return playerSide;
    }

    public JButton getPlayGame() {
        return playGame;
    }

    public JButton getGiveUp() {
        return giveUp;
    }

    public void addToCategoryList(QuestionCategory category) {
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

    public void setScores() {
        player = calculateScore(playerScoreList);
        opponent = calculateScore(opponentScoreList);
        scoreLabel.setText(player + " - " + opponent);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getOpponent() {
        return opponent;
    }

    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    public void setDesignOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        playGame.setBorder(settingsOptions.getBorder());
        giveUp.setBorder(settingsOptions.getBorder());
        updateColorOfScore();
    }

    private void updateColorOfScore() {
        for (ScoreCount scoreCount : scoreCounts) {
            for (ScoreLabel playerLabel : scoreCount.playerLabels) {
                if(playerLabel.getForeground() != Color.red && playerLabel.getForeground() != Color.green)
                    playerLabel.setForeground(settingsOptions.getColor());
            }
            for (ScoreLabel opponentLabel : scoreCount.opponentLabels) {
                if(opponentLabel.getForeground() != Color.red && opponentLabel.getForeground() != Color.green)
                    opponentLabel.setForeground(settingsOptions.getColor());
            }
        }
    }

    public void setIconAndPlayerName(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        yourPanel.removeAll();
        opponentPanel.removeAll();

        createIconPanel(yourPanel, settingsOptions.getIcon(), settingsOptions.getPlayer1());
        createIconPanel(opponentPanel, settingsOptions.getPlayer2Icon(), settingsOptions.getPlayer2());

        yourPanel.repaint();
        opponentPanel.revalidate();
    }

    public void setTurnLabel(Boolean yourTurn) {
        if (yourTurn) {
            turnLabel.setText("<html><div style='text-align: center; vertical-align: bottom;'>Din tur");
        } else {
            turnLabel.setText("<html><div style='text-align: center; vertical-align: bottom;'>Deras tur");
        }
    }
}
