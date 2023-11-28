package GUI;

import javax.swing.*;
import java.awt.*;

public class ResultPage extends JPanel {
    JPanel northPanel;
    JPanel southPanel;

    JButton newGame;
    JButton exitGame;

    JPanel yourPanel;
    JPanel opponentPanel;

    int yourPoints;
    int player2Points;

    SettingsOptions settingsOptions;

    public ResultPage(){
        northPanel = new JPanel();
        southPanel = new JPanel();

        newGame = new JButton("New Game");
        exitGame = new JButton("Exit Game");

        settingsOptions = new SettingsOptions();

        yourPoints = 10;
        player2Points = 7;

        addComponents();
        actionListenerHandler();
    }

    public void addComponents(){
        setSize(new Dimension(800,800));
        setLayout(new BorderLayout());
        setOpaque(true);

        generateNorthPanel();
        generateSouthPanel();

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

    public void generateNorthPanel(){
        northPanel.setLayout(new GridLayout(1,2));
        northPanel.setPreferredSize(new Dimension(800, 600));
        northPanel.setOpaque(false);

        yourPanel = new JPanel();
        opponentPanel = new JPanel();

        createPlayerPanels(yourPanel, settingsOptions.getBigIcon(), settingsOptions.getPlayer1(), yourPoints > player2Points);
        createPlayerPanels(opponentPanel, settingsOptions.getBigPlayer2Icon(), settingsOptions.getPlayer2(), player2Points > yourPoints);

        northPanel.add(yourPanel);
        northPanel.add(opponentPanel);
    }

    public void createPlayerPanels(JPanel panel, ImageIcon icon, String text, Boolean win){
        panel.setPreferredSize(new Dimension(400, 600));
        panel.setLayout(new GridLayout(4, 1));
        panel.setOpaque(false);

        JLabel place;
        if(win){
            place = new JLabel("WINNER", SwingConstants.CENTER);
        }
        else{
            place = new JLabel("LOSER", SwingConstants.CENTER);
        }
        place.setFont(settingsOptions.getBigText());
        place.setForeground(Color.BLACK);

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        JLabel nameLabel = new JLabel(text, SwingConstants.CENTER);
        nameLabel.setFont(settingsOptions.getBigText());
        nameLabel.setForeground(Color.BLACK);

        JLabel points = new JLabel("10", SwingConstants.CENTER);
        points.setFont(settingsOptions.getBigText());
        points.setForeground(Color.BLACK);

        panel.add(place);
        panel.add(iconLabel);
        panel.add(nameLabel);
        panel.add(points);
    }
    public void generateSouthPanel(){
        southPanel.setLayout(new GridLayout(1,2));
        southPanel.setPreferredSize(new Dimension(800, 200));
        southPanel.setOpaque(false);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        setButtonDesign(newGame, leftPanel);
        setButtonDesign(exitGame, rightPanel);
        newGame.setBackground(Color.green);

        southPanel.add(leftPanel);
        southPanel.add(rightPanel);
    }

    public void setButtonDesign(JButton button, JPanel panel){
        button.setPreferredSize(new Dimension(300, 100));
        button.setBackground(Color.WHITE);
        button.setFont(settingsOptions.getSmallText());
        button.setBorder(settingsOptions.getBorder());
        panel.add(button, SwingConstants.CENTER);
        panel.setOpaque(false);
    }

    public void setDesignOptions(SettingsOptions settingsOptions){
        this.settingsOptions = settingsOptions;
        newGame.setBorder(settingsOptions.getBorder());
        exitGame.setBorder(settingsOptions.getBorder());
    }

    public void setIcon(SettingsOptions settingsOptions){
        this.settingsOptions = settingsOptions;

        yourPanel.removeAll();
        opponentPanel.removeAll();

        createPlayerPanels(yourPanel, settingsOptions.getBigIcon(), settingsOptions.getPlayer1(), true);
        createPlayerPanels(opponentPanel, settingsOptions.getBigPlayer2Icon(), settingsOptions.getPlayer2(), false);
    }

    private void actionListenerHandler() {
        newGame.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "StartPage"));
        exitGame.addActionListener(ActionEvent -> System.exit(0));
    }

}
