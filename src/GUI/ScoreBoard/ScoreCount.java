package GUI.ScoreBoard;

import GUI.CategoryLabel;
import Question.QuestionCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ScoreCount extends JPanel {

    QuestionCollection questionCollection;

    List<Boolean> player1Score;
    List<Boolean> player2Score;

    JLabel player1Label;
    JLabel player2Label;
    JLabel categoryLabel;

    public ScoreCount(){
        player1Score = new ArrayList<>();
        player2Score = new ArrayList<>();
        player1Label = new JLabel();
        player2Label = new JLabel();
        categoryLabel = new JLabel();
        addComponents();
    }
    public ScoreCount(List<Boolean> winList, QuestionCollection questionCollection){
        this.questionCollection = questionCollection;
        this.player1Score = winList;
        player1Label = new JLabel();
        player2Label = new JLabel();
        categoryLabel = new JLabel();
        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(800,80));
        setLayout(new GridLayout(1, 3));
        setOpaque(false);

        generatePlayerLabel(player1Label, player1Score);
        add(player1Label);
        CategoryLabel categoryLabel = new CategoryLabel(Color.ORANGE, questionCollection.getRandomCategory());
        add(categoryLabel);
        generatePlayerLabel(player2Label, player2Score);
        add(player2Label);
    }

    public void generatePlayerLabel(JLabel label, List<Boolean> list){
        label.setSize(new Dimension(300, 80));
        label.setLayout(new GridLayout(1,3));
        label.setOpaque(false);
        if(list.size() >= 3){
            for(int i = 0; i < 3; i++){
                label.add(new ScoreLabel(list.get(i)));
            }
        }
        else{
            for(int i = 0; i < 3; i++){
                label.add(new ScoreLabel());
            }
        }
    }
}