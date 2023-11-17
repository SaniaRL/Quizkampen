package GUI;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {

    JPanel northPanel;
    JLabel centerLabel;

    JButton startNewGame;
    JButton homeButton;
    JButton settingsButton;
    JButton notificationsButton;

    public StartPage(){
        homeButton = new JButton();

        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        //Methods for designing components to make it easier to navigate
        generateCenterPanel();
        generateNorthPanel();

        add(centerLabel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void generateCenterPanel(){
        centerLabel = new JLabel();

        Dimension centerPanelSize = new Dimension(800, 600);
        centerLabel.setSize(centerPanelSize);
        centerLabel.setMinimumSize(centerPanelSize);
        centerLabel.setMaximumSize(centerPanelSize);
        centerLabel.setBackground(Color.GRAY);

        startNewGame = new JButton();

        Dimension buttonSize = new Dimension(400,200);
        startNewGame.setSize(buttonSize);
        startNewGame.setMinimumSize(buttonSize);
        startNewGame.setMaximumSize(buttonSize);
        startNewGame.setBackground(new Color(60,255,230));
        startNewGame.setLocation(200, 200);
        startNewGame.setText("New Game");
        startNewGame.setFont(new Font("Open Sans", Font.PLAIN, 25));

        centerLabel.add(startNewGame);
    }

    public void generateNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,5));
        Dimension northPanelSize = new Dimension(800,200);
        northPanel.setSize(northPanelSize);
        northPanel.setMaximumSize(northPanelSize);
        northPanel.setMinimumSize(northPanelSize);

        settingsButton = new JButton();
        settingsButton.setBackground(Color.blue);

        ImageIcon settings = new ImageIcon(new ImageIcon("Icons/settings.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        settingsButton.setIcon(settings);
        northPanel.add(settingsButton);

        for(int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            northPanel.add(label);
        }

        notificationsButton = new JButton();
        notificationsButton.setBackground(Color.blue);

        ImageIcon notifications = new ImageIcon(new ImageIcon("Icons/message.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        notificationsButton.setIcon(notifications);
        northPanel.add(notificationsButton);
    }
}
