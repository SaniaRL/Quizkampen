package GUI.CategoryGUI;

import Enums.QuestionCategory;
import GUI.SettingsOptions;

import javax.swing.*;
import java.awt.*;

public class CategoryLabel extends JLabel {

    JLabel textLabel;
    SettingsOptions settingsOptions;

    public CategoryLabel(Color color, QuestionCategory category){
        settingsOptions = new SettingsOptions();
        textLabel = new JLabel(category.label, SwingConstants.CENTER);
        setBackground(color);
        addComponents();
    }

    public CategoryLabel(){
        settingsOptions = new SettingsOptions();
        textLabel = new JLabel("", SwingConstants.CENTER);
//        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponents();
    }

    public void setDesignOptions(SettingsOptions settingsOptionsOptions) {
        this.settingsOptions = settingsOptionsOptions;
    }

    public void addComponents(){
        setSize(new Dimension(200, 50));
        setLayout(new FlowLayout());

        textLabel.setFont(settingsOptions.getBigText());
        textLabel.setVisible(true);
        textLabel.setForeground(Color.BLACK);
        add(textLabel);

        setVisible(true);

    }
}
