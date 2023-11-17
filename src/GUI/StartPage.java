package GUI;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {

    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JButton startNewGame;
    JButton homeButton;

    Color colorTheme;
    Color buttonColor;


    public StartPage(){
        homeButton = new JButton();
        colorTheme = new Color(190, 103, 208);
        buttonColor= new Color(93,246,246);

        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        //Methods for designing components to make it easier to navigate
        generateCenterPanel();
        generateNorthPanel();
        generateSouthPanel();

        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void generateCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));
        Dimension centerPanelSize = new Dimension(800, 600);
        centerPanel.setSize(centerPanelSize);
        centerPanel.setMinimumSize(centerPanelSize);
        centerPanel.setMaximumSize(centerPanelSize);
        centerPanel.setBackground(colorTheme);

        JLabel emptyLabel1 = new JLabel();
        emptyLabel1.setBackground(colorTheme);
        centerPanel.add(emptyLabel1);

        startNewGame = new JButton();

        Dimension buttonSize = new Dimension(400,200);
        startNewGame.setSize(buttonSize);
        startNewGame.setPreferredSize(buttonSize);
        startNewGame.setMinimumSize(buttonSize);
        startNewGame.setMaximumSize(buttonSize);
        startNewGame.setBackground(new Color(93,246,246));
        startNewGame.setLocation(200, 200);
        startNewGame.setText("New Game");
        startNewGame.setFont(new Font("Open Sans", Font.PLAIN, 45));

        generateEastWestPanels(BorderLayout.WEST);
        centerPanel.add(startNewGame);
        generateEastWestPanels(BorderLayout.EAST);

        JLabel emptyLabel2 = new JLabel();
        emptyLabel1.setBackground(colorTheme);
        centerPanel.add(emptyLabel2);
    }

    public void generateNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,5));
        Dimension northPanelSize = new Dimension(800,200);
        northPanel.setSize(northPanelSize);
        northPanel.setMaximumSize(northPanelSize);
        northPanel.setMinimumSize(northPanelSize);

        Color color = new Color(93,246,246);

        JButton settingsButton = createButton("Icons/Settings.png");
        northPanel.add(settingsButton);

        for(int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            label.setBackground(color);
            label.setOpaque(true);
            northPanel.add(label);
        }

        JButton notificationsButton = createButton("Icons/message.png");
        northPanel.add(notificationsButton);
    }

    public void generateSouthPanel(){
        southPanel = new JPanel();

        southPanel.setLayout(new GridLayout(1,5));
        Dimension northPanelSize = new Dimension(800,200);
        southPanel.setSize(northPanelSize);
        southPanel.setMaximumSize(northPanelSize);
        southPanel.setMinimumSize(northPanelSize);


        JButton homeButton = createButton("Icons/house.png");
        southPanel.add(homeButton);

        for(int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            label.setBackground(buttonColor);
            label.setOpaque(true);
            southPanel.add(label);
        }

        JButton catButton = createButton("Icons/cat.png");
        southPanel.add(catButton);
    }

    public JButton createButton(String path){
        JButton jButton = new JButton();
        jButton.setBackground(buttonColor);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        jButton.setIcon(imageIcon);

        return jButton;
    }

    public void generateEastWestPanels(String borderLayout){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 400));
        panel.setBackground(colorTheme);
        add(panel, borderLayout);


    }

}
