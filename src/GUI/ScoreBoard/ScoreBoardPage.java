package GUI.ScoreBoard;

import Question.QuestionCollection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class ScoreBoardPage extends JPanel {

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    JButton playGame;

    String backgroundImagePath;
    Image backgroundImage;

    //Temporary list
    List<List<Boolean>> winList;
    List<ScoreCount> scoreCounts;

    public ScoreBoardPage() throws IOException {

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        playGame = new JButton("SPELA");

        winList = new ArrayList<>();

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
        centerPanel.setLayout(new GridLayout(6, 3));
        centerPanel.setOpaque(false);

        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.shuffleCategoryList();

        generateScoreCounts();

    }

    public void generateScoreCounts() throws IOException {
        QuestionCollection questionCollection = new QuestionCollection();
        for(int i = 0; i < 6; i++){
            ScoreCount scoreCountLabel;
            if(winList.size() > i){
                scoreCountLabel = new ScoreCount(winList.get(i), questionCollection.getRandomCategory());
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
        JLabel scoreLabel = new JLabel("3 - 3", SwingConstants.CENTER);

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

    public void setWinList(List<List<Boolean>> winList) {
        this.winList = winList;
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
}
