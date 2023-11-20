package GUI;

import javax.swing.*;
import java.awt.*;

public class CategoryLabel extends JLabel {

    public CategoryLabel(Color color, Question.QuestionCategory category){
        setSize(new Dimension(200, 100));
        setBackground(color);
        setLayout(new FlowLayout());

        JLabel textLabel = new JLabel(category.label, SwingConstants.CENTER);
        textLabel.setFont(new Font("Montserrat", Font.PLAIN, 40));
        textLabel.setVisible(true);
        textLabel.setForeground(Color.BLACK);
        add(textLabel);

        setVisible(true);
    }
}
