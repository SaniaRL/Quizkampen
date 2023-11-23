package GUI.ScoreBoard;

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
            System.out.println("GREEN");
        }
        else{
            setForeground(loseColor);
            System.out.println("RED");
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
