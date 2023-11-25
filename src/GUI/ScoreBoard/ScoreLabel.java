package GUI.ScoreBoard;

import GUI.DesignOptions;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    DesignOptions designOptions;

    Color winColor;
    Color loseColor;
    Color defaultColor;

    public ScoreLabel(Boolean win){
        designOptions = new DesignOptions();
        winColor = Color.GREEN;
        loseColor = Color.RED;
        defaultColor = designOptions.getColor();
        designOptions.setColor("violet");

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

    public void addComponents(){
        setSize(new Dimension(80, 50));
        setOpaque(false);
        setText("⚫");
        setFont(new Font("Montserrat", Font.PLAIN, 70));
    }
}
