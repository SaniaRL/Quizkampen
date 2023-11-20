package GUI;

import Question.QuestionCategory;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;

public class ScoreBoardPage extends JPanel {

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    String backgroundImagePath;
    Image backgroundImage;

    //Temporary list
    List<Boolean> winList;

    public ScoreBoardPage(){

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        winList = new ArrayList<>();
        winList.add(true);
        winList.add(true);
        winList.add(false);

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = (new ImageIcon(backgroundImagePath)).getImage();
        addComponents();
    }

    public void addComponents(){
        setLayout(new BorderLayout());

        generateCenterPanel();
        generateNorthPanel();
        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }

    public void generateCenterPanel(){
        centerPanel.setPreferredSize(new Dimension(800, 700));
        centerPanel.setLayout(new GridLayout(1, 3));
        centerPanel.setOpaque(false);

        JPanel scorePanel = generateScorePanel(winList);
        centerPanel.add(scorePanel);

        CategoryLabel categoryLabel = new CategoryLabel(Color.ORANGE, QuestionCategory.HISTORY);
        centerPanel.add(categoryLabel, SwingConstants.CENTER);

        JPanel scorePanelOpponent1 = generateScorePanel(winList);
        centerPanel.add(scorePanelOpponent1);
    }

    public JPanel generateScorePanel(List<Boolean> winList){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 100));
        panel.setOpaque(false);
        for(int i = 0; i < 3; i++){
            ScoreLabel scoreLabel = new ScoreLabel(winList.get(i));
            panel.add(scoreLabel);
        }
        return panel;
    }

    public void generateNorthPanel(){
        northPanel.setPreferredSize(new Dimension(800, 200));
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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
