package GUI;

import Question.QuestionCollection;
import Question.Question;

import javax.swing.*;
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
    List<Question> threeQuestions;
    String testCategory;

    JLabel questionLabel;

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    String backgroundImagePath;
    Image backgroundImage;

    int indexCount;
    String answer;
    List<JButton> optionButtons;

    public QuestionPage(String testCategory) throws IOException {

        questionCollection = new QuestionCollection();
        questionList = questionCollection.getAllQuestions();
        threeQuestions = new ArrayList<>();
        this.testCategory = testCategory;

        questionLabel = new JLabel();

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        indexCount = 0;
        optionButtons = new ArrayList<>();

        addComponents();

    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        findThreeQuestion();

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
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateCenterPanel(){
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setPreferredSize(new Dimension(800, 300));
        centerPanel.setOpaque(false);


        JLabel questionLabel = new JLabel((threeQuestions.get(indexCount)).getQuestion(), SwingConstants.CENTER);
        questionLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        questionLabel.setPreferredSize(new Dimension(600, 200));
        questionLabel.setBackground(Color.white);
        questionLabel.setBorder(new LineBorder(Color.BLUE, 5));
        questionLabel.setOpaque(true);

        centerPanel.add(questionLabel);
    }

    public void generationNorthPanel(){
        northPanel.setLayout(new GridLayout(1,3));
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);

        JLabel yourPlayer = new JLabel("YOU", SwingConstants.CENTER);
        JLabel category = new JLabel("CATEGORY", SwingConstants.CENTER);
        JLabel opponent = new JLabel("OPPONENT", SwingConstants.CENTER);

        yourPlayer.setOpaque(false);
        category.setOpaque(false);
        opponent.setOpaque(false);

        northPanel.add(yourPlayer, SwingConstants.CENTER);
        northPanel.add(category, SwingConstants.CENTER);
        northPanel.add(opponent, SwingConstants.CENTER);
    }

    public void generateSouthPanel(){
        southPanel.setLayout(new GridLayout(2,2));
        southPanel.setPreferredSize(new Dimension(800, 300));
        southPanel.setOpaque(false);

        List<String> optionsList = new ArrayList<>(Arrays.stream((threeQuestions.get(indexCount)).getQuestionOptions()).toList());
        answer = optionsList.get(0);
        Collections.shuffle(optionsList);
        if(optionsList.size() == 4){
            for (String string : optionsList) {
                JButton button = new JButton(string);
                button.setPreferredSize(new Dimension(200, 100));
                button.setBackground(Color.white);
                southPanel.add(button, SwingConstants.CENTER);
                optionButtons.add(button);
            }
        }
    }

    public void findThreeQuestion(){
            for(Question question : questionList){
                if(threeQuestions.size() < 3){
                    if(question.getCategory().label.equals(testCategory)){
                        threeQuestions.add(question);
                    }
                }
            }
    }

    public void nextQuestion(){
        indexCount++;
        northPanel.removeAll();
        centerPanel.removeAll();
        southPanel.removeAll();
        generationNorthPanel();
        generateCenterPanel();
        generateSouthPanel();
        repaint();
        revalidate();
    }

    public void nextThreeQuestions(String category){
        indexCount = 0;
        testCategory = category;
        threeQuestions.clear();
        optionButtons.clear();
        centerPanel.removeAll();
        northPanel.removeAll();
        southPanel.removeAll();
        findThreeQuestion();
        generateCenterPanel();
        generationNorthPanel();
        generateSouthPanel();
    }

    public String getAnswer() {
        return answer;
    }

    public List<JButton> getOptionButtons() {
        return optionButtons;
    }
}
