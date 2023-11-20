package GUI;

import javax.swing.*;
import java.awt.*;

public class CategoryLabel extends JLabel {

    public CategoryLabel(Color color, Question.QuestionCategory category){
        setSize(new Dimension(200, 100));
        setBackground(color);
        setText(category.label);
        setFont(new Font("Montserrat", Font.PLAIN, 40));
    }
}
