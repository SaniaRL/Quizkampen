package GUI.ScoreBoard;

import GUI.CategoryGUI.CategoryLabel;
import Enums.QuestionCategory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class ScoreCount extends JPanel {

    QuestionCategory questionCategory;

    List<Boolean> player1Score; // can be removed
    List<Boolean> player2Score; // can be removed
    List<ScoreLabel> playerLabels;
    List<ScoreLabel> opponentLabels;
    JPanel playerPanel;
    JPanel opponentPanel;
    CategoryLabel categoryLabel;
    int amountOfQuestions;

    public ScoreCount(int amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;
        player1Score = new ArrayList<>();
        player2Score = new ArrayList<>();
        playerPanel = new JPanel();
        opponentPanel = new JPanel();
        playerLabels = new ArrayList<>();
        opponentLabels = new ArrayList<>();
        categoryLabel = new CategoryLabel();
        addComponents();
    }

    public ScoreCount(List<Boolean> player1Score, List<Boolean> player2Score, QuestionCategory category, int amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;
        this.questionCategory = category;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        playerPanel = new JPanel();
        opponentPanel = new JPanel();
        categoryLabel = new CategoryLabel(Color.ORANGE, questionCategory);
        addComponents();
    }

    public void addComponents() {
        setSize(new Dimension(800, 80));
        setLayout(new GridLayout(1, amountOfQuestions));
        setOpaque(false);

        playerPanel.setLayout(new FlowLayout());
        opponentPanel.setLayout(new FlowLayout());

        generatePlayerLabel(playerPanel, playerLabels);
        add(playerPanel);
        add(categoryLabel);
        generatePlayerLabel(opponentPanel, opponentLabels);
        add(opponentPanel);
    }

    public void generatePlayerLabel(JPanel panel, List<ScoreLabel> list) {
        panel.setSize(new Dimension(300, 80));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 2, 0, 2);
        panel.setBorder(emptyBorder);
        for (int i = 0; i < amountOfQuestions; i++) {
            ScoreLabel scoreLabel = new ScoreLabel(amountOfQuestions);
            panel.add(scoreLabel);
            list.add(scoreLabel);
        }
    }

    public void setCategoryLabel(String label) {
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setVerticalAlignment(SwingConstants.NORTH);
        this.categoryLabel.setText("<html><font size=40>" + label + "</font></html>");
    }

    public List<ScoreLabel> getPlayerLabels() {
        return playerLabels;
    }

    public List<ScoreLabel> getOpponentLabels() {
        return opponentLabels;
    }
}
