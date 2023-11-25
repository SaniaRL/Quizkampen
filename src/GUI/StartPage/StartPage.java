package GUI.StartPage;

import GUI.DesignOptions;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StartPage extends JPanel {

    JPanel northPanel;
    JPanel centerPanel;
    JPanel southPanel;

    JButton startNewGame;
    StartButton settings;
    StartButton notifications;
    StartButton homeButton;
    StartButton catButton;

    Border emptyBorder;

    DesignOptions designOptions;

    public StartPage(){
        designOptions = new DesignOptions();
        designOptions.setColor("violet");

        settings = new StartButton("\uD83D\uDD27", new Dimension(150,150), 50, Color.BLACK);
        notifications = new StartButton("\uD83D\uDCAC", new Dimension(150,150), 50, Color.BLACK);
        catButton = new StartButton("", new Dimension(150,150), 50, Color.BLACK);
        homeButton = new StartButton("Q", new Dimension(180,180), 130, designOptions.getDetailColor());

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
        if (designOptions.getBackgroundImage() != null) {
            g.drawImage(designOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
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
        startNewGame.setBackground(designOptions.getDetailColor());
        startNewGame.setLocation(200, 200);
        startNewGame.setText("New Game");
        startNewGame.setFont(designOptions.getTitleFont());
        startNewGame.setBorder(designOptions.getBorder());

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

        northPanel.add(settings);

        for(int i = 0; i < 5; i++){
            JLabel label = new JLabel();
            label.setOpaque(false);
            northPanel.add(label);
        }

        northPanel.add(notifications);
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

        for(int i = 0; i < 2; i++){
            JLabel label = new JLabel();
            label.setOpaque(false);
            southPanel.add(label);
        }

        southPanel.add(catButton);
    }

  /*  public JButton createButton(JButton button, String path, Dimension dimension, int size){

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size, size, Image.SCALE_REPLICATE));
        button.setIcon(imageIcon);
        button.setPreferredSize(dimension);
        button.setOpaque(false);
        button.setBorder(emptyBorder);
        button.setContentAreaFilled(false);

        return button;
    }

   */

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
