package GUI;

import javax.swing.*;
import java.awt.*;

public class ResultPage extends JPanel {
    JPanel northPanel;
    JPanel southPanel;

    JButton newGame;
    JButton exitGame;

    DesignOptions designOptions;

    public ResultPage(){
        northPanel = new JPanel();
        southPanel = new JPanel();

        newGame = new JButton("New Game");
        exitGame = new JButton("Exit Game");

        designOptions = new DesignOptions();

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
        if (designOptions.getBackgroundImage() != null) {
            g.drawImage(designOptions.getBackgroundImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateNorthPanel(){
        northPanel.setLayout(new GridLayout(1,3));
        northPanel.setPreferredSize(new Dimension(800, 500));
        northPanel.setOpaque(false);
    }
    public void generateSouthPanel(){
        southPanel.setLayout(new GridLayout(1,2));
        southPanel.setPreferredSize(new Dimension(800, 300));
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
        button.setFont(designOptions.getSmallText());
        button.setBorder(designOptions.getBorder());
        panel.add(button, SwingConstants.CENTER);
        panel.setOpaque(false);
    }

    public void setDesignOptions(DesignOptions designOptions){
        this.designOptions = designOptions;
        newGame.setBorder(designOptions.getBorder());
        exitGame.setBorder(designOptions.getBorder());
    }

    private void actionListenerHandler() {
        newGame.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "StartPage"));
        exitGame.addActionListener(ActionEvent -> System.exit(0));
    }

}
