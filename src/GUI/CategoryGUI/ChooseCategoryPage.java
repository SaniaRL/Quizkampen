package GUI.CategoryGUI;

import GUI.SettingsOptions;
import Enums.QuestionCategory;
import Question.QuestionCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.*;
import java.io.IOException;

public class ChooseCategoryPage extends JPanel {

    JPanel northPanel;
    JPanel southPanel;

    CategoryButton categoryOption1;
    CategoryButton categoryOption2;
    CategoryButton categoryOption3;
    List<CategoryButton> categoryOptionsList = new ArrayList<>();

    QuestionCollection questionCollection;
    List<QuestionCategory> randomCategoryList;

    JLabel text;

    SettingsOptions settingsOptions;

    public ChooseCategoryPage() throws IOException {
        settingsOptions = new SettingsOptions();

        northPanel = new JPanel();
        southPanel = new JPanel();
        questionCollection = new QuestionCollection();

        //Initierarar dessa för att förhindra nullpointerexception
        categoryOption1 = new CategoryButton();
        categoryOption2 = new CategoryButton();
        categoryOption3 = new CategoryButton();

        randomCategoryList = new ArrayList<>();

        generateRandomCategoryList();
        generateCategoryButtons();

        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        generateNorthPanel();
        generateSouthPanel();

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (settingsOptions.getBackgroundImage() != null) {
            g.drawImage(settingsOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateNorthPanel(){
        northPanel.setLayout(new GridLayout(3, 1));
        northPanel.setPreferredSize(new Dimension(800,300));
        northPanel.setOpaque(false);

        JLabel empty1 = new JLabel();
        empty1.setPreferredSize(new Dimension(800, 100));

        JLabel empty2 = new JLabel();
        empty1.setPreferredSize(new Dimension(800, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);


        text = new JLabel("Välj en kategori", SwingConstants.CENTER);
        text.setFont(settingsOptions.getTitleFont());
        text.setPreferredSize(new Dimension(800, 100));
        text.setForeground(settingsOptions.getContrastColor());
        text.setOpaque(false);

        panel.add(text, BorderLayout.CENTER);

        northPanel.add(empty1);
        northPanel.add(text);
        northPanel.add(empty2);
    }

    public void generateSouthPanel(){
        southPanel.setLayout(new GridLayout(4, 1));
        southPanel.setPreferredSize(new Dimension(800,500));
        southPanel.setOpaque(false);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(800, 50));

        for (CategoryButton categoryButton : categoryOptionsList) {
            generateCategoryLabels(categoryButton, southPanel);
        }
    }
    public void generateCategoryLabels(CategoryButton button, JPanel panel){
        button.setOpaque(true);
        button.setVisible(true);
        button.setPreferredSize(new Dimension(600, 100));

        panel.add(button, SwingConstants.CENTER);
    }

    public void generateRandomCategoryList(){
        HashSet<QuestionCategory> randomCategoryHashset = new HashSet<>();
        while(randomCategoryHashset.size() < 3){
            QuestionCategory questionCategory = questionCollection.getRandomCategory();
            randomCategoryHashset.add(questionCategory);
        }
        randomCategoryList = randomCategoryHashset.stream().toList();
    }

    public void generateCategoryButtons(){
        for (int i = 0; i < 3; i++) {
            categoryOptionsList.add(new CategoryButton(randomCategoryList.get(i)));
            categoryOptionsList.get(i).setDesignOptions(this.settingsOptions);
        }
    }

    public CategoryButton getCategoryOption1() {
        return categoryOption1;
    }

    public CategoryButton getCategoryOption2() {
        return categoryOption2;
    }

    public CategoryButton getCategoryOption3() {
        return categoryOption3;
    }

    public List<CategoryButton> getCategoryOptions(){
        return categoryOptionsList;
    }

    public void updateQuestionCategories(){
        generateRandomCategoryList();

        categoryOption1.updateCategoryButton(randomCategoryList.get(0));
        categoryOption2.updateCategoryButton(randomCategoryList.get(1));
        categoryOption3.updateCategoryButton(randomCategoryList.get(2));

        repaint();
        revalidate();
    }

    public void setDesignOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        for (CategoryButton categoryButton : categoryOptionsList) {
            categoryButton.setBorder(settingsOptions.getBorder());
        }
        text.setForeground(settingsOptions.getContrastColor());

    }
}
