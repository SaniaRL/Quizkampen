package GUI.CategoryGUI;
import GUI.SettingsOptions;
import Question.QuestionCategory;

import javax.swing.*;
import java.awt.*;

public class CategoryButton extends JButton {

    QuestionCategory category;
    Color backgroundColor;
    SettingsOptions settingsOptions;

    public CategoryButton(QuestionCategory category) {
        settingsOptions = new SettingsOptions();
        this.category = category;
        this.backgroundColor = CategoryColor.getColor(category.label);

        setText(category.label);
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(600, 200));
        setFont(settingsOptions.getBigText());
        setBorder(settingsOptions.getBorder());
    }

    public void updateCategoryButton(QuestionCategory category){
        this.category = category;
        this.backgroundColor = CategoryColor.getColor(category.label);

        setText(category.label);
        setBackground(backgroundColor);
    }

    public void setDesignOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        setBorder(settingsOptions.getBorder());
    }
}