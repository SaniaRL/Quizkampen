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

        leftPanel.add(newGame);
        rightPanel.add(exitGame);

        southPanel.add(leftPanel);
        southPanel.add(rightPanel);
    }
}
