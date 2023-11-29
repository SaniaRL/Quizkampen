package GUI.StartPage;

import GUI.SettingsOptions;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StartPage extends JPanel {

    JPanel northPanel;
    JPanel nameFieldPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JButton startNewGame;
    StartButton homeButton;
    JTextField nameField;

    Border emptyBorder;

    SettingsOptions settingsOptions;

    public StartPage(){
        settingsOptions = new SettingsOptions();

        homeButton = new StartButton("Q", new Dimension(180,180), 130, settingsOptions.getDetailColor());

        emptyBorder = BorderFactory.createEmptyBorder();
        addComponents();
    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        generateCenterPanel();
        generateNorthPanel();
        generateSouthPanel();

        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (settingsOptions.getBackgroundImage() != null) {
            g.drawImage(settingsOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateNameFieldPanel(){
        nameFieldPanel = new JPanel();
        nameFieldPanel.setLayout(new GridLayout(2,1));
        Dimension nameFieldPanelSize = new Dimension(400, 50);
        nameFieldPanel.setSize(nameFieldPanelSize);
        nameFieldPanel.setMinimumSize(nameFieldPanelSize);
        nameFieldPanel.setMaximumSize(nameFieldPanelSize);
        nameFieldPanel.setOpaque(false);

        JLabel nameLabel = new JLabel("Please enter your nickname and start your game:");
        Dimension textFieldSize = new Dimension(380, 25);
        nameField = new JTextField();
        nameField.setSize(textFieldSize);
        nameField.setPreferredSize(textFieldSize);

        nameFieldPanel.add(nameLabel);
        nameFieldPanel.add(nameField);

    }

    public void generateCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        Dimension centerPanelSize = new Dimension(1000, 600);
        centerPanel.setSize(centerPanelSize);
        centerPanel.setMinimumSize(centerPanelSize);
        centerPanel.setMaximumSize(centerPanelSize);
        centerPanel.setOpaque(false);

        JLabel emptyLabel1 = new JLabel();
        centerPanel.add(emptyLabel1);

        startNewGame = new JButton();

        Dimension buttonSize = new Dimension(400,200);
        startNewGame.setSize(buttonSize);
        startNewGame.setPreferredSize(buttonSize);
        startNewGame.setMinimumSize(buttonSize);
        startNewGame.setMaximumSize(buttonSize);
        startNewGame.setBackground(settingsOptions.getDetailColor());
        startNewGame.setLocation(200, 200);
        startNewGame.setText("New Game");
        startNewGame.setFont(settingsOptions.getTitleFont());
        startNewGame.setBorder(settingsOptions.getBorder());

        generateNameFieldPanel();
        generateEastWestPanels(BorderLayout.WEST);
        centerPanel.add(nameFieldPanel);
        centerPanel.add(startNewGame);
        generateEastWestPanels(BorderLayout.EAST);

        JLabel emptyLabel2 = new JLabel();
        setOpaque(false);
        centerPanel.add(emptyLabel2);
    }

    public void generateNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);
    }

    public void generateSouthPanel(){
        southPanel = new JPanel();

        southPanel.setLayout(new GridLayout(1,4));
        Dimension northPanelSize = new Dimension(800,200);
        southPanel.setSize(northPanelSize);
        southPanel.setMaximumSize(northPanelSize);
        southPanel.setMinimumSize(northPanelSize);
        southPanel.setOpaque(false);

        southPanel.add(homeButton);

        for(int i = 0; i < 3; i++){
            JLabel label = new JLabel();
            label.setOpaque(false);
            southPanel.add(label);
        }

    }

    public void generateEastWestPanels(String borderLayout){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 400));
        add(panel, borderLayout);
        panel.setOpaque(false);
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setDesignOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        startNewGame.setBorder(new LineBorder(settingsOptions.getColor(), 10));
        startNewGame.setBackground(settingsOptions.getDetailColor());
        homeButton.setForeground(settingsOptions.getDetailColor());
    }

    public JButton getStartNewGame() {
        return startNewGame;
    }
}
