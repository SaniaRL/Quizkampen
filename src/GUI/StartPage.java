package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StartPage extends JPanel {

    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JButton startNewGame;
    JButton settings;
    JButton notifications;
    JButton homeButton;
    JButton catButton;

    String backgroundImagePath;
    Image backgroundImage;

    Color colorTheme;
    Color buttonColor;

    Border emptyBorder;

    public StartPage(){
        settings = new JButton();
        notifications = new JButton();
        homeButton = new JButton();
        catButton = new JButton();

        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        colorTheme = new Color(190, 103, 208);
        buttonColor= new Color(93,246,246);

        emptyBorder = BorderFactory.createEmptyBorder();

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));
        Dimension centerPanelSize = new Dimension(800, 600);
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
        startNewGame.setBackground(new Color(93,246,246));
        startNewGame.setLocation(200, 200);
        startNewGame.setText("New Game");
        startNewGame.setFont(new Font("Open Sans", Font.PLAIN, 45));

        generateEastWestPanels(BorderLayout.WEST);
        centerPanel.add(startNewGame);
        generateEastWestPanels(BorderLayout.EAST);

        JLabel emptyLabel2 = new JLabel();
        setOpaque(false);
        centerPanel.add(emptyLabel2);
    }

    public void generateNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,5));
        Dimension northPanelSize = new Dimension(800,200);
        northPanel.setSize(northPanelSize);
        northPanel.setMaximumSize(northPanelSize);
        northPanel.setMinimumSize(northPanelSize);
        northPanel.setOpaque(false);

        settings = createButton(settings, "Icons/Settings.png");
        settings.setBorder(emptyBorder);
        northPanel.add(settings);

        for(int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            label.setOpaque(false);
            northPanel.add(label);
        }

        notifications = createButton(notifications, "Icons/message.png");
        notifications.setBorder(emptyBorder);
        northPanel.add(notifications);
    }

    public void generateSouthPanel(){
        southPanel = new JPanel();

        southPanel.setLayout(new GridLayout(1,5));
        Dimension northPanelSize = new Dimension(800,200);
        southPanel.setSize(northPanelSize);
        southPanel.setMaximumSize(northPanelSize);
        southPanel.setMinimumSize(northPanelSize);
        southPanel.setOpaque(false);

        homeButton = createButton(homeButton, "Icons/house.png");
        homeButton.setBorder(emptyBorder);
        southPanel.add(homeButton);

        for(int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            label.setOpaque(false);
            southPanel.add(label);
        }

        catButton = createButton(catButton, "Icons/cat.png");
        catButton.setBorder(emptyBorder);
        southPanel.add(catButton);
    }

    public JButton createButton(JButton button, String path){

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        button.setIcon(imageIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);

        return button;
    }

    public void generateEastWestPanels(String borderLayout){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 400));
        add(panel, borderLayout);
        panel.setOpaque(false);
    }

    public JButton getSettings() {
        return settings;
    }

    public JButton getNotifications() {
        return notifications;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getCatButton() {
        return catButton;
    }

    public JButton getStartNewGame() {
        return startNewGame;
    }
}
