package GUI.CategoryGUI;

import javax.swing.*;
import java.awt.*;

public class CategoryLabel extends JLabel {

    JLabel textLabel;

    public CategoryLabel(Color color, Question.QuestionCategory category){
        textLabel = new JLabel(category.label, SwingConstants.CENTER);
        setBackground(color);
        addComponents();
    }

    public CategoryLabel(){
        textLabel = new JLabel();
        setOpaque(false);
    }

    public void addComponents(){
        setSize(new Dimension(200, 50));
        setLayout(new FlowLayout());

        textLabel.setFont(new Font("Montserrat", Font.PLAIN, 40));
        textLabel.setVisible(true);
        textLabel.setForeground(Color.BLACK);
        add(textLabel);

        setVisible(true);

    }
}
