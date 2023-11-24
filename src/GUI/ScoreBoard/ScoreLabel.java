package GUI.ScoreBoard;

import GUI.QuestionPage;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    Color winColor = Color.GREEN;
    Color loseColor = Color.RED;
    Color defaultColor = Color.BLUE;

    public ScoreLabel(Boolean win){
        addComponents();
        if(win){
            setForeground(winColor);
        }
        else{
            setForeground(loseColor);
        }
    }

    public ScoreLabel(){
        addComponents();
        setForeground(defaultColor);
    }

    public void addComponents(){ //Simon
        QuestionPage qp = new QuestionPage();
        int questionsToFind = qp.getQuestionsToFind();

        if (questionsToFind <= 3) { //Adjusting button size to fit circles on the same row.
            setPreferredSize(new Dimension(80, 50));
            setFont(new Font("Montserrat", Font.PLAIN, 70));
        }
        else if (questionsToFind == 4) {
            setPreferredSize(new Dimension(50, 30));
            setFont(new Font("Montserrat", Font.PLAIN, 45));
        }
        else if (questionsToFind == 5) {
            setPreferredSize(new Dimension(40, 25));
            setFont(new Font("Montserrat", Font.PLAIN, 40));
        }
        else {
            setPreferredSize(new Dimension(30, 20));
            setFont(new Font("Montserrat", Font.PLAIN, 30));
        }
        setText("⚫");
    }
}
