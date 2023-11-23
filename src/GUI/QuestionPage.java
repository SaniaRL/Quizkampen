package GUI;

import Question.QuestionCollection;
import Question.Question;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.InputStream;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class QuestionPage extends JPanel {

    QuestionCollection questionCollection;
    List<Question> questionList;
    List<Question> threeQuestions;
    String category = "";

    JLabel questionLabel;

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    String backgroundImagePath;
    Image backgroundImage;

    int indexCount;
    int questionsToFind; //Simon lagt til
    String answer;
    List<JButton> optionButtons;

    public QuestionPage(String category) throws IOException {

        questionCollection = new QuestionCollection();
        questionList = questionCollection.getAllQuestions();
        threeQuestions = new ArrayList<>();
        this.category = category;

        questionLabel = new JLabel();

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        indexCount = 0;
        optionButtons = new ArrayList<>();

        loadProperties(); //Simon lagt till

        addComponents();

    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        findQuestions();

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


        JLabel questionLabel = new JLabel("<html><div style='text-align: center;'>"  + (threeQuestions.get(indexCount)).getQuestion(), SwingConstants.CENTER);
        questionLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border border = new LineBorder(Color.BLUE, 10);
        Border compoundBorder = new CompoundBorder(border, emptyBorder);
        questionLabel.setPreferredSize(new Dimension(600, 200));
        questionLabel.setBackground(Color.white);
        questionLabel.setBorder(compoundBorder);
        questionLabel.setOpaque(true);

        centerPanel.add(questionLabel);
    }

    public void generationNorthPanel(){
        northPanel.setLayout(new GridLayout(1,3));
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);

        JLabel yourPlayer = new JLabel("YOU", SwingConstants.CENTER);

        JLabel category = new JLabel(this.category, SwingConstants.CENTER);
        category.setFont(new Font("Cabin", Font.BOLD, 22));

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
                JButton button = new JButton("<html><div style='text-align: center;'>" + string);
                Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
                Border border = new LineBorder(Color.BLUE, 2);
                Border compoundBorder = new CompoundBorder(border, emptyBorder);
                button.setBorder(compoundBorder);
                button.setPreferredSize(new Dimension(200, 100));
                button.setFont(new Font("Cabin", Font.PLAIN, 20));
                button.setBackground(Color.white);
                southPanel.add(button, SwingConstants.CENTER);
                optionButtons.add(button);
            }
        }
    }

    public void findQuestions() {
        Collections.shuffle(questionList);
        for (Question question : questionList) {
            if (threeQuestions.size() < questionsToFind) {
                if (question.getCategory().label.equals(category)) {
                    threeQuestions.add(question);
                }
            }
        }
    }
    private void loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("GUI/NumberOfQuestions.properties")) {
            if (input != null) {
                prop.load(input);
                questionsToFind = Integer.parseInt(prop.getProperty("questionsToFind", "3"));
            } else {
                System.out.println("Could not find properties");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //Not removing the method below yet. But looks like propertiesFile works as intended.
    public void findThreeQuestion(){
        Collections.shuffle(questionList);
            for(Question question : questionList){
                if(threeQuestions.size() < 3){
                    if(question.getCategory().label.equals(category)){
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
        this.category = category;
        threeQuestions.clear();
        optionButtons.clear();
        centerPanel.removeAll();
        northPanel.removeAll();
        southPanel.removeAll();
        findQuestions();
        generateCenterPanel();
        generationNorthPanel();
        generateSouthPanel();
    }

    public String getAnswer() {
        return answer;
    }

    public List<JButton> getOptionButtons() {return optionButtons;}
    public void setIndexCount(int indexCount) {this.indexCount = indexCount;}

}
