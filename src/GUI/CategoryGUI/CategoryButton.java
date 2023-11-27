package GUI.CategoryGUI;
import Enums.CategoryColor;
import GUI.SettingsOptions;
import Enums.QuestionCategory;

import javax.swing.*;
import java.awt.*;

public class CategoryButton extends JButton {

    QuestionCategory category;
    Color backgroundColor;
    SettingsOptions settingsOptions;

    //La till en tom konstruktor för att kunna initeria tomma knappar i ChooseCategoryPage
    public CategoryButton() {}
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
        setForeground(Color.BLACK);
        setBackground(backgroundColor);
    }

    public void setDesignOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        setBorder(settingsOptions.getBorder());
    }
}