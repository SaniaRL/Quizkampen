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
    QuestionPage qp = new QuestionPage(); //Simon ändring
    int questionsToFind; //Simon ändring

    public ScoreCount(){
        player1Score = new ArrayList<>();
        player2Score = new ArrayList<>();
        player1Label = new JPanel();
        player2Label = new JPanel();
        categoryLabel = new CategoryLabel();
        addComponents();
    }
    public ScoreCount(List<Boolean> player1Score, List<Boolean> player2Score, QuestionCategory category){
        this.questionCategory = category;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        player1Label = new JPanel();
        player2Label = new JPanel();
        categoryLabel = new CategoryLabel(Color.ORANGE, questionCategory);
        addComponents();
    }

    public void addComponents(){
        questionsToFind = qp.getQuestionsToFind(); //Simon ändring
        setSize(new Dimension(800,80));
        setLayout(new GridLayout(1, questionsToFind)); //3
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
        questionsToFind = qp.getQuestionsToFind(); //Simon ändring
        panel.setSize(new Dimension(300, 80));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        if(list.size() >= questionsToFind){ //3
            for(int i = 0; i < questionsToFind; i++){ //3
                panel.add(new ScoreLabel(list.get(i)));
            }
        }
        else{
            for(int i = 0; i < questionsToFind; i++){ //3
                ScoreLabel scoreLabel = new ScoreLabel();
                panel.add(scoreLabel);
            }
        }
    }

}
