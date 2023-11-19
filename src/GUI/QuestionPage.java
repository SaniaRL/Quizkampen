package GUI;

import Question.QuestionCollection;
import Question.Question;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.io.IOException;

public class QuestionPage extends JPanel {

    QuestionCollection questionCollection;
    List<Question> questionList;

    JLabel questionLabel;

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    String backgroundImagePath;
    Image backgroundImage;

    public QuestionPage() throws IOException {

        questionCollection = new QuestionCollection();
        questionList = questionCollection.getAllQuestions();

        questionLabel = new JLabel();

        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        addComponents();

    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        generationNorthPanel();
        add(northPanel, BorderLayout.NORTH);

        generateCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

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


        JLabel questionLabel = new JLabel("Får får får?", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Montserrat", Font.PLAIN, 22));
        questionLabel.setPreferredSize(new Dimension(500, 200));
        questionLabel.setBackground(Color.lightGray);
        questionLabel.setBorder(new LineBorder(Color.BLACK));
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
        northPanel.setLayout(new GridLayout(1,3));
        northPanel.setPreferredSize(new Dimension(800, 300));
        northPanel.setOpaque(false);


        JLabel questionLabel = new JLabel();

    }

    public void generateOptionLabels(){

    }
}
