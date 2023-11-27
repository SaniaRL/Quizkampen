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
    List<ScoreLabel> player1Labels;
    List<ScoreLabel> player2Labels;
    JPanel player1Panel;
    JPanel player2Panel;
    CategoryLabel categoryLabel;
    int amountOfQuestions;

    public ScoreCount(int amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;
        player1Score = new ArrayList<>();
        player2Score = new ArrayList<>();
        player1Panel = new JPanel();
        player2Panel = new JPanel();
        player1Labels = new ArrayList<>();
        player2Labels = new ArrayList<>();
        categoryLabel = new CategoryLabel();
        addComponents();
    }

    public ScoreCount(List<Boolean> player1Score, List<Boolean> player2Score, QuestionCategory category, int amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;
        this.questionCategory = category;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        player1Panel = new JPanel();
        player2Panel = new JPanel();
        categoryLabel = new CategoryLabel(Color.ORANGE, questionCategory);
        addComponents();
    }

    public void addComponents() {
        setSize(new Dimension(800, 80));
        setLayout(new GridLayout(1, amountOfQuestions));
        setOpaque(false);

        player1Panel.setLayout(new FlowLayout());
        player2Panel.setLayout(new FlowLayout());

        generatePlayerLabel(player1Panel, player1Labels);
        add(player1Panel);
        add(categoryLabel);
        generatePlayerLabel(player2Panel, player2Labels);
        add(player2Panel);
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

    public List<ScoreLabel> getPlayer1Labels() {
        return player1Labels;
    }

    public List<ScoreLabel> getPlayer2Labels() {
        return player2Labels;
    }
}
