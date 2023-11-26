package GUI;

import Question.QuestionCollection;
import Question.Question;
import Question.QuestionCategory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

public class QuestionPage extends JPanel {

    QuestionCollection questionCollection;
    List<Question> questionList;
    List<Question> questions;
    QuestionCategory category;

    JLabel categoryLabel;
    JLabel questionLabel;

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;
    int indexCount;
    String answer;
    List<JButton> optionButtons;
    int questionAmount;

    DesignOptions designOptions;

    public QuestionPage() throws IOException {
        designOptions = new DesignOptions();

        questionCollection = new QuestionCollection();
        questionList = questionCollection.getAllQuestions();
        questions = new ArrayList<>();
        questionLabel = new JLabel();

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        indexCount = 0;
        optionButtons = new ArrayList<>();

        addComponents();
    }

    public void addComponents() {
        setSize(new Dimension(800, 800));
        setLayout(new BorderLayout());
        setOpaque(true);

//        findThreeQuestion();

        generationNorthPanel();
        add(northPanel, BorderLayout.NORTH);

        generateCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

        generateSouthPanel();
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (designOptions.getBackgroundImage() != null) {
            g.drawImage(designOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateCenterPanel() {
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setPreferredSize(new Dimension(800, 300));
        centerPanel.setOpaque(false);


        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(designOptions.getSmallText());
        Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border border = new LineBorder(designOptions.getColor(), 10);
        Border compoundBorder = new CompoundBorder(border, emptyBorder);
        questionLabel.setPreferredSize(new Dimension(600, 200));
        questionLabel.setBackground(Color.white);
        questionLabel.setBorder(compoundBorder);
        questionLabel.setOpaque(true);

        centerPanel.add(questionLabel);
    }

    public void generationNorthPanel() {
        northPanel.setLayout(new GridLayout(1, 3));
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);

        ImageIcon yourImageIcon = new ImageIcon(designOptions.getIcon().getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH));
        JPanel yourPanel = new JPanel();
        createIconPanel(yourImageIcon, "YOU", yourPanel);

        categoryLabel = new JLabel("", SwingConstants.CENTER);
        categoryLabel.setFont(designOptions.getSmallText());
        categoryLabel.setPreferredSize(new Dimension(300, 150));

        ImageIcon opponentImageIcon = new ImageIcon(designOptions.getIcon().getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH));
        JPanel opponentPanel = new JPanel();
        createIconPanel(opponentImageIcon, "Random", opponentPanel);

        Border emptyBorder = BorderFactory.createEmptyBorder(50,10,50,10);

        categoryLabel.setOpaque(false);

        northPanel.setBorder(emptyBorder);
        northPanel.add(yourPanel);
        northPanel.add(categoryLabel);
        northPanel.add(opponentPanel);
    }

    public void createIconPanel(ImageIcon imageIcon, String text, JPanel panel){
        JLabel iconLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(200, 100));

        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(200,30));
        textLabel.setFont(designOptions.getSmallText());

        panel.setLayout(new GridLayout(2, 1));
        panel.setPreferredSize(new Dimension(200,150));
        panel.setOpaque(false);
        panel.add(iconLabel);
        panel.add(textLabel);

    }

    //Arrays.stream((threeQuestions.get(indexCount)).getQuestionOptions()).toList()
//"<html><div style='text-align: center;'>" + string    // for setText
    public void generateSouthPanel() {
        southPanel.setLayout(new GridLayout(2, 2));
        southPanel.setPreferredSize(new Dimension(800, 300));
        southPanel.setOpaque(false);

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton();
            Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            Border border = new LineBorder(designOptions.getColor(), 2);
            Border compoundBorder = new CompoundBorder(border, emptyBorder);
            button.setBorder(compoundBorder);
            button.setPreferredSize(new Dimension(200, 100));
            button.setFont(designOptions.getSmallText());
            button.setBackground(Color.white);
            southPanel.add(button, SwingConstants.CENTER);
            optionButtons.add(button);
        }
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    public void findQuestions(QuestionCategory category) {
        Collections.shuffle(questionList);
        for (Question question : questionList) {
            if (questions.size() < questionAmount) {
                if (question.getCategory() == category) {
                    questions.add(question);
                }
            }
        }
    }

    public void nextQuestion() {
        indexCount++;
        updateQuestionText(indexCount);
        updateButtons(indexCount);
    }

    public void newQuestions(QuestionCategory category) {
        System.out.println(category);
        indexCount = 0;
        questions.clear();
        this.category = category;
        findQuestions(category);
        categoryLabel.setText(category.label);
        updateQuestionText(0);
        updateButtons(0);
    }

    public void setQuestionPage(QuestionCategory category, Question[] questions){
        indexCount = 0;
        this.questions.clear();
        this.questions.addAll(Arrays.asList(questions));
        this.category = category;
        categoryLabel.setText(category.label);
        updateQuestionText(0);
        updateButtons(0);
    }

    private void updateButtons(int index) {
        List<String> tempList = new ArrayList<>(List.of(questions.get(index).getQuestionOptions()));
        answer = tempList.get(0);
        Collections.shuffle(tempList);
        for (int i = 0; i < 4; i++) {
            optionButtons.get(i).setBackground(Color.WHITE);
            optionButtons.get(i).setText("<html><div style='text-align: center;'>" + tempList.get(i));
        }
    }
    private void updateQuestionText(int index) {
        System.out.println(questions);
        questionLabel.setText("<html><div style='text-align: center;'>" + (questions.get(index).getQuestion()));
    }

    public String getAnswer() {
        return answer;
    }

    public List<JButton> getOptionButtons() {
        return optionButtons;
    }

    public void setIndexCount(int indexCount) {
        this.indexCount = indexCount;
    }
    public void setDesignOptions(DesignOptions designOptions) {
        this.designOptions = designOptions;
    }
}
