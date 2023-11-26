package GUI.ScoreBoard;

import GUI.CategoryGUI.CategoryColor;
import GUI.CategoryGUI.CategoryLabel;
import GUI.QuestionPage;
import Question.QuestionCategory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;

public class ScoreCount extends JPanel {

    QuestionCategory questionCategory;

    List<Boolean> player1Score;
    List<Boolean> player2Score;

    JPanel player1Label;
    JPanel player2Label;
    JLabel categoryLabel;
    int amountOfQuestions;

    public ScoreCount(int amountOfQuestions){
        this.amountOfQuestions = amountOfQuestions;
        player1Score = new ArrayList<>();
        player2Score = new ArrayList<>();
        player1Label = new JPanel();
        player2Label = new JPanel();
        categoryLabel = new CategoryLabel();
        addComponents();
    }
    public ScoreCount(List<Boolean> player1Score, List<Boolean> player2Score, QuestionCategory category, int amountOfQuestions){
        this.amountOfQuestions = amountOfQuestions;
        this.questionCategory = category;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        player1Label = new JPanel();
        player2Label = new JPanel();
        categoryLabel = new CategoryLabel(Color.ORANGE, questionCategory);
        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(800,80));
        setLayout(new GridLayout(1, amountOfQuestions));
        setOpaque(false);

        player1Label.setLayout(new FlowLayout());
        player2Label.setLayout(new FlowLayout());

        generatePlayerLabel(player1Label, player1Score);
        add(player1Label);
        add(categoryLabel);
        generatePlayerLabel(player2Label, player2Score);
        add(player2Label);
    }

    public void generatePlayerLabel(JPanel panel, List<Boolean> list){
        panel.setSize(new Dimension(300, 80));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        if(list.size() >= amountOfQuestions){
            for(int i = 0; i < amountOfQuestions; i++){
                panel.add(new ScoreLabel(list.get(i), amountOfQuestions));
            }
        }
        else{
            for(int i = 0; i < amountOfQuestions; i++){
                ScoreLabel scoreLabel = new ScoreLabel(amountOfQuestions);
                panel.add(scoreLabel);
            }
        }
    }

}
