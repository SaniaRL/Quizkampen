package GUI.StartPage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StartButton extends JButton {
    String symbol;
    Dimension dimension;
    int fontSize;
    Border emptyBorder;
    Color textColor;

    public StartButton(String symbol, Dimension dimension, int fontSize, Color color) {
        this.symbol = symbol;
        this.dimension = dimension;
        this.fontSize = fontSize;
        this.textColor = color;

        emptyBorder = BorderFactory.createEmptyBorder();

        createButton();
    }

    public void createButton(){

        setPreferredSize(dimension);
        setBorder(emptyBorder);
        setOpaque(false);
        setContentAreaFilled(false);

        setText(symbol);
        setFont(new Font("Cabin", Font.PLAIN, fontSize));
        setForeground(textColor);

    }
}
