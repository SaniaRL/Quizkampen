package GUI;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

    Color winColor = Color.GREEN;
    Color loseColor = Color.RED;
    Color defaultColor = Color.BLUE;

    public ScoreLabel(Boolean win){
        setSize(new Dimension(100, 50));
        setOpaque(false);
        setText("⚫");
        setFont(new Font("Montserrat", Font.PLAIN, 40));
        if(win){
            setForeground(winColor);
        }
        else{
            setForeground(loseColor);
        }
    }

    public ScoreLabel(){
        setSize(new Dimension(50, 50));
        setOpaque(false);
        setText("⚫");
        setFont(new Font("Montserrat", Font.PLAIN, 40));
        setForeground(defaultColor);
    }
}
