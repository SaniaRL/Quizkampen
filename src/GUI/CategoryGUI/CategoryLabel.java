package GUI.CategoryGUI;

import GUI.DesignOptions;

import javax.swing.*;
import java.awt.*;

public class CategoryLabel extends JLabel {

    JLabel textLabel;
    DesignOptions designOptions;

    public CategoryLabel(Color color, Question.QuestionCategory category){
        designOptions = new DesignOptions();
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

        textLabel.setFont(designOptions.getBigText());
        textLabel.setVisible(true);
        textLabel.setForeground(Color.BLACK);
        add(textLabel);

        setVisible(true);

    }
}
