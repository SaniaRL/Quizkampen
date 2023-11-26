package GUI.ScoreBoard;

import GUI.QuestionPage;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    Color winColor = Color.GREEN;
    Color loseColor = Color.RED;
    Color defaultColor = Color.BLUE;
    int amountOfQuestions;

    public ScoreLabel(Boolean win, int amountOfQuestions){
        this.amountOfQuestions = amountOfQuestions;
        System.out.println(amountOfQuestions+" frågor i ScoreLabel");
        addComponents();
        if(win){
            setForeground(winColor);
        }
        else{
            setForeground(loseColor);
        }
    }

    public ScoreLabel(int amountOfQuestions){
        this.amountOfQuestions = amountOfQuestions;
        System.out.println(amountOfQuestions+" frågor i ScoreLabel");
        addComponents();
        setForeground(defaultColor);
    }

    public void addComponents(){ //Simon

        if (amountOfQuestions <= 3) { //Adjusting button size to fit circles on the same row.
            setPreferredSize(new Dimension(80, 50));
            setFont(new Font("Montserrat", Font.PLAIN, 70));
        }
        else if (amountOfQuestions == 4) {
            setPreferredSize(new Dimension(50, 30));
            setFont(new Font("Montserrat", Font.PLAIN, 45));
        }
        else if (amountOfQuestions == 5) {
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
