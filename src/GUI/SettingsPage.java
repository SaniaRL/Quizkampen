package GUI;

import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {

    JPanel centerPanel;
    JPanel northPanel;
    JPanel southPanel;

    JButton backButton = new JButton("Home");
    JButton test1 = new JButton("Home1");
    JButton test2 = new JButton("Home2");
    JButton test3 = new JButton("Home3");
    JButton test4 = new JButton("Home4");
    JLabel headerLabel = new JLabel("Quizkampen");

    String backgroundImagePath;
    Image backgroundImage;

    public SettingsPage() {

        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

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

    private void generateSouthPanel() {
        southPanel.setLayout(new GridLayout(1,3));
        southPanel.setPreferredSize(new Dimension(800, 300));
        southPanel.setOpaque(false);

        southPanel.add(backButton);
     //   southPanel.add(test4);
     //   southPanel.add(test3);

    }

    private void generationNorthPanel() {
        northPanel.setLayout(new FlowLayout());
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);

        northPanel.add(headerLabel);
    }

    private void generateCenterPanel() {
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.setPreferredSize(new Dimension(800, 300));
        centerPanel.setOpaque(false);

      //  centerPanel.add(test1);
      //  centerPanel.add(test2);

    }

    private void actionListenerHandler() {
        backButton.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "StartPage"));
    }

    private void componentStyling() {
        Font fontL = new Font("Serif", Font.BOLD, 18);
        headerLabel.setFont(fontL);
        //headerLabel.setBackground(Color.WHITE);

    }
}

