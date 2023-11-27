package GUI.CategoryGUI;
import GUI.DesignOptions;
import Question.QuestionCategory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CategoryButton extends JButton {

    QuestionCategory category;
    Color backgroundColor;
    DesignOptions designOptions;

    public CategoryButton(){}
    public CategoryButton(QuestionCategory category) {
        designOptions = new DesignOptions();
        this.category = category;
        this.backgroundColor = CategoryColor.getColor(category.label);

        setText(category.label);
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(600, 200));
        setFont(designOptions.getBigText());
        setBorder(designOptions.getBorder());
    }

    public void updateCategoryButton(QuestionCategory category){
        this.category = category;
        this.backgroundColor = CategoryColor.getColor(category.label);

        setText(category.label);
        setBackground(backgroundColor);
    }

    public void setDesignOptions(DesignOptions designOptions) {
        this.designOptions = designOptions;
        setBorder(designOptions.getBorder());
    }
}