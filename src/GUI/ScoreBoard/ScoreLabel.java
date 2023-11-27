package GUI.ScoreBoard;

import GUI.SettingsOptions;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    SettingsOptions settingsOptions;

    Color winColor;
    Color loseColor;
    Color defaultColor;
    int amountOfQuestions;

    public ScoreLabel(Boolean win, int amountOfQuestions){
        this.amountOfQuestions = amountOfQuestions;
        settingsOptions = new SettingsOptions();
        winColor = Color.GREEN;
        loseColor = Color.RED;
        defaultColor = settingsOptions.getColor();
        settingsOptions.setColor("violet");

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
        addComponents();
        setForeground(defaultColor);
    }

    public void addComponents(){

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
