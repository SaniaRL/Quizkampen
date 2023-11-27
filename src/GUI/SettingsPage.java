package GUI;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public class SettingsPage extends JPanel {

    JPanel northPanel;
    JPanel centerPanel;

    JButton backButton = new JButton("Home");
    JButton test1 = new JButton("Change avatar");
    JButton test2 = new JButton("Change Background");
    JButton test3 = new JButton("test3");
    JButton exitGame = new JButton("Exit game");
    JLabel headerLabel = new JLabel("<html><div style='text-align: center; padding-top: 36px;'>Quizkampen", SwingConstants.CENTER);

    List<JButton> optionsList;

    SettingsOptions settingsOptions;

    public SettingsPage() {
        settingsOptions = new SettingsOptions();
        optionsList = new ArrayList<>();

        northPanel = new JPanel();
        centerPanel = new JPanel();

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

        addButtonsToList();

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (settingsOptions.getBackgroundImage() != null) {
            g.drawImage(settingsOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
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
        button.setBorder(settingsOptions.getBorder());
        button.setFont(settingsOptions.getSmallText());
        centerPanel.add(button);
    }

    private void actionListenerHandler() {
        backButton.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "StartPage"));
        exitGame.addActionListener(ActionEvent -> System.exit(0));
    }

    private void componentStyling() {
        Font fontL = settingsOptions.getTitleFont();
        headerLabel.setFont(fontL);
        headerLabel.setForeground(Color.WHITE);
    }

    private void addButtonsToList(){
        optionsList.add(backButton);
        optionsList.add(test1);
        optionsList.add(test2);
        optionsList.add(test3);
        optionsList.add(exitGame);
    }

    public void setDesignOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
        for(JButton button : optionsList){
            button.setBorder(settingsOptions.getBorder());
            button.repaint();
            button.revalidate();
        }
    }
}

