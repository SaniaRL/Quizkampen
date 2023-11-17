package GUI;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {

    JLabel northLabel;
    JLabel southLabel;
    JPanel centerPanel;

    JButton startNewGame;
    JButton homeButton;
    JButton settingsButton;
    JButton notificationsButton;

    public StartPage(){
        northLabel = new JLabel();
        southLabel = new JLabel();
        centerPanel = new JPanel();
        startNewGame = new JButton();
        homeButton = new JButton();
        settingsButton = new JButton();
        notificationsButton = new JButton();

        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(700,700));
        setLayout(new BorderLayout());
        setOpaque(true);

        //Methods for designing components to make it easier to navigate
        startNewGameButton();

        add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(startNewGame);

        setVisible(true);
    }

    public void startNewGameButton(){
        centerPanel.setLayout(new FlowLayout());
        Dimension centerPanelSize = new Dimension(800, 600);
        centerPanel.setSize(centerPanelSize);
        centerPanel.setMinimumSize(centerPanelSize);
        centerPanel.setMaximumSize(centerPanelSize);

        Dimension buttonSize = new Dimension(400,200);
        startNewGame.setSize(buttonSize);
        startNewGame.setMinimumSize(buttonSize);
        startNewGame.setMaximumSize(buttonSize);
        startNewGame.setBackground(new Color(60,255,230));
        startNewGame.setText("New Game");
        startNewGame.setFont(new Font("Open Sans", Font.PLAIN, 14));
    }
}
