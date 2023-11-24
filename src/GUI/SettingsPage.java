package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsPage extends JPanel {

    JPanel northPanel;
    JPanel centerPanel;

    JButton backButton = new JButton("Home");
    JButton test1 = new JButton("Change avatar");
    JButton test2 = new JButton("Change Background");
    JButton test3 = new JButton("test3");
    JButton exitGame = new JButton("Exit game");
    JLabel headerLabel = new JLabel("<html><div style='text-align: center; padding-top: 36px;'>Quizkampen", SwingConstants.CENTER);

    String backgroundImagePath;
    Image backgroundImage;

    public SettingsPage() {

        northPanel = new JPanel();
        centerPanel = new JPanel();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = (new ImageIcon(backgroundImagePath)).getImage();

        addComponents();
        actionListenerHandler();
        componentStyling();
    }

    public void addComponents() {
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

    private void generationNorthPanel() {
        northPanel.setLayout(new FlowLayout());
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);

        northPanel.add(headerLabel, SwingConstants.CENTER);
    }

    private void generateCenterPanel() {
        centerPanel.setLayout(new GridLayout(5,1));
        centerPanel.setPreferredSize(new Dimension(800, 600));
        centerPanel.setOpaque(false);


        addButtons(backButton);
        addButtons(test1);
        addButtons(test2);
        addButtons(test3);
        addButtons(exitGame);

    }

    public void addButtons(JButton button){
        button.setBackground(Color.WHITE);
        button.setBorder(new LineBorder(Color.BLUE, 5));
        button.setFont(new Font("Cabin", Font.BOLD, 22));
        centerPanel.add(button);

    }

    private void actionListenerHandler() {
        backButton.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "StartPage"));
        exitGame.addActionListener(ActionEvent -> System.exit(0));

    }

    private void componentStyling() {
        Font fontL = new Font("Serif", Font.BOLD, 50);
        headerLabel.setFont(fontL);
        headerLabel.setForeground(Color.WHITE);

    }
}

