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
        designOptions = new DesignOptions();
        textLabel = new JLabel("", SwingConstants.CENTER);
//        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponents();
    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(JLabel textLabel) {
        this.textLabel = textLabel;
    }

    public DesignOptions getDesignOptions() {
        return designOptions;
    }

    public void setDesignOptions(DesignOptions designOptions) {
        this.designOptions = designOptions;
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
