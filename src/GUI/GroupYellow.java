package GUI;

import Enums.ImageIconAvatar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GroupYellow extends JPanel {
    String backgroundImagePath;
    Image backgroundImage;

    private JPanel northPanel;
    private JPanel southPanel;

    public GroupYellow(){
        backgroundImagePath = "Backgrounds/sunYellow.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        northPanel = new JPanel();
        southPanel = new JPanel();
        addComponents();
    }

    public void addComponents(){

        setLayout(new BorderLayout());
        northPanel.setLayout(new GridLayout(2,2));

        generateIconPanels(ImageIconAvatar.SQUID, "Robin");
        generateIconPanels(ImageIconAvatar.ELEPHANT, "Sania");
        generateIconPanels(ImageIconAvatar.CRAB, "Sebastian");
        generateIconPanels(ImageIconAvatar.MONKEY, "Simon");

        Border emptyBorder = BorderFactory.createEmptyBorder(90,20,20,20);
        northPanel.setBorder(emptyBorder);
        northPanel.setOpaque(false);

        JLabel textLabel = new JLabel("GRUPP GUL", SwingConstants.CENTER);
        textLabel.setFont(new Font("Sans Serif", Font.BOLD, 70));
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(textLabel);
        southPanel.setOpaque(false);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        emptyPanel.setPreferredSize(new Dimension(800, 100));

        southPanel.add(emptyPanel);

        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    public void generateIconPanels(ImageIconAvatar iconAvatar, String name){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.setOpaque(false);

        JLabel iconLabel = new JLabel(new ImageIcon(new ImageIcon(iconAvatar.iconPath).getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH)));
        iconLabel.setOpaque(false);

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        nameLabel.setOpaque(false);

        panel.add(iconLabel);
        panel.add(nameLabel);
        northPanel.add(panel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
