package GUI.CategoryGUI;
import Question.QuestionCategory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CategoryButton extends JButton {

    QuestionCategory category;
    Color backgroundColor;

    public CategoryButton(QuestionCategory category) {
        this.category = category;
        this.backgroundColor = CategoryColor.getColor(category.label);

        setText(category.label);
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(600, 200));
        setFont(new Font("Arial", Font.PLAIN, 40));
        setBorder(new LineBorder(Color.BLUE, 5));
    }

    public void updateCategoryButton(QuestionCategory category){
        this.category = category;
        this.backgroundColor = CategoryColor.getColor(category.label);

        setText(category.label);
        setBackground(backgroundColor);
    }
}