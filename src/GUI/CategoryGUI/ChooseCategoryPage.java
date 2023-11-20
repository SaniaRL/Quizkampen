package GUI.CategoryGUI;

import Question.QuestionCategory;
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

    String backgroundImagePath;
    Image backgroundImage;

    CategoryButton categoryOption1;
    CategoryButton categoryOption2;
    CategoryButton categoryOption3;

    QuestionCollection questionCollection;
    List<QuestionCategory> randomCategoryList;

    public ChooseCategoryPage() throws IOException {
        northPanel = new JPanel();
        southPanel = new JPanel();
        questionCollection = new QuestionCollection();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();

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
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
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


        JLabel text = new JLabel("Välj en kategori", SwingConstants.CENTER);
        text.setFont(new Font("Open Sans", Font.PLAIN, 54));
        text.setPreferredSize(new Dimension(800, 100));
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

        generateCategoryLabels(categoryOption1, southPanel);
        generateCategoryLabels(categoryOption2, southPanel);
        generateCategoryLabels(categoryOption3, southPanel);
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
        categoryOption1 = new CategoryButton(randomCategoryList.get(0));
        categoryOption2 = new CategoryButton(randomCategoryList.get(1));
        categoryOption3 = new CategoryButton(randomCategoryList.get(2));
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

    public void updateQuestionCategories(){
        generateRandomCategoryList();

        categoryOption1.updateCategoryButton(randomCategoryList.get(0));
        categoryOption2.updateCategoryButton(randomCategoryList.get(1));
        categoryOption3.updateCategoryButton(randomCategoryList.get(2));

//        generateCategoryLabels(categoryOption1, southPanel);
//        generateCategoryLabels(categoryOption2, southPanel);
//        generateCategoryLabels(categoryOption3, southPanel);

        System.out.println("Update categories");

        repaint();
        revalidate();
    }
}
