package GUI.StartPage;

import Enums.ImageIconAvatar;
import GUI.SettingsOptions;

import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class StartPage extends JPanel {

    private JPanel northPanel;
    private JPanel nameFieldPanel;
    private JPanel centerPanel;
    private JPanel southPanel;

    private AvatarPanel avatarPanel;

    private JButton startNewGame;
    private final StartButton homeButton;
    private JTextField nameField;
    private SettingsOptions settingsOptions;

    public StartPage(){
        settingsOptions = new SettingsOptions();
        homeButton = new StartButton("Q", new Dimension(180,180), 130, settingsOptions.getDetailColor());

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
        nameFieldPanel.setLayout(new GridLayout(4,1));
        Dimension nameFieldPanelSize = new Dimension(300, 100);
        nameFieldPanel.setSize(nameFieldPanelSize);
        nameFieldPanel.setPreferredSize(nameFieldPanelSize);
        nameFieldPanel.setMinimumSize(nameFieldPanelSize);
        nameFieldPanel.setMaximumSize(nameFieldPanelSize);
        nameFieldPanel.setOpaque(false);

        JLabel textLabel = new JLabel("Please enter your name:");
        JLabel emptyLabel = new JLabel();
        textLabel.setFont(settingsOptions.getSmallText());
        textLabel.setFont(settingsOptions.getSmallText());
        nameField = new JTextField();
        nameField.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        nameFieldPanel.add(textLabel);
        nameFieldPanel.add(emptyLabel);
        nameFieldPanel.add(nameField);

    }

    public void generateCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        Dimension centerPanelSize = new Dimension(1000, 400);
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

        JLabel emptyLabel2 = new JLabel();
        centerPanel.add(emptyLabel2);

        generateNameFieldPanel();
        generateEastWestPanels(BorderLayout.WEST);
        centerPanel.add(nameFieldPanel);
        centerPanel.add(startNewGame);
        generateEastWestPanels(BorderLayout.EAST);

        setOpaque(false);
    }

    public void generateNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.setPreferredSize(new Dimension(800, 200));
        northPanel.setOpaque(false);

        Border emptyBorder = BorderFactory.createEmptyBorder(80, 0, 0 ,0);
        northPanel.setBorder(emptyBorder);
        avatarPanel = new AvatarPanel();
        northPanel.add(avatarPanel);
    }

    public void generateSouthPanel(){
        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setPreferredSize(new Dimension(800, 160));
        southPanel.setOpaque(false);

        southPanel.add(homeButton, BorderLayout.WEST);
        homeButton.setPreferredSize(new Dimension(200, 160));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        emptyPanel.setPreferredSize(new Dimension(600, 160));
        northPanel.add(emptyPanel, BorderLayout.EAST);
    }

    public void generateEastWestPanels(String borderLayout){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 400));
        add(panel, borderLayout);
        panel.setOpaque(false);
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

    public StartButton getHomeButton() { return homeButton; }
    public JButton getStartNewGame() {
        return startNewGame;
    }

    public AvatarPanel getAvatarPanel() {
        return avatarPanel;
    }
}
