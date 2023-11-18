package GUI;

import javax.swing.*;
import java.awt.*;

public class ChooseCategoryPage extends JPanel {

    JPanel northPanel;
    JPanel southPanel;

    String path;
    Image backgroundImage;

    JLabel categoryOption1;
    JLabel categoryOption2;
    JLabel categoryOption3;

    Color colorTheme;
    Color buttonColor;


    public ChooseCategoryPage(){
        northPanel = new JPanel();
        southPanel = new JPanel();

        path = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(path).getImage();

        categoryOption1 = new JLabel();
        categoryOption2 = new JLabel();
        categoryOption3 = new JLabel();

        colorTheme = new Color(190, 103, 208);
        buttonColor= new Color(93,246,246);


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
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void generateNorthPanel(){
        northPanel.setLayout(new GridLayout(3, 1));
        northPanel.setPreferredSize(new Dimension(800,300));
        northPanel.setOpaque(false);

        JLabel empty1 = new JLabel();
        empty1.setPreferredSize(new Dimension(800, 100));

        JLabel empty2 = new JLabel();
        empty1.setPreferredSize(new Dimension(800, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);


        JLabel text = new JLabel("Välj en kategori", SwingConstants.CENTER);
        text.setFont(new Font("Open Sans", Font.PLAIN, 54));
        text.setPreferredSize(new Dimension(800, 100));
        text.setOpaque(false);

        panel.add(text, BorderLayout.CENTER);

        northPanel.add(empty1);
        northPanel.add(text);
        northPanel.add(empty2);
    }

    public void generateSouthPanel(){
        southPanel.setLayout(new GridLayout(4, 1));
        southPanel.setPreferredSize(new Dimension(800,500));
        southPanel.setOpaque(false);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(800, 50));

        generateCategoryLabels(categoryOption1, southPanel);
        generateCategoryLabels(categoryOption2, southPanel);
        generateCategoryLabels(categoryOption3, southPanel);

    }
    public void generateCategoryLabels(JLabel label, JPanel panel){
        //Just nu gör denna alla svart för att jag ska gitta i rätt kategori och inte har fixat
        //ikonerna för alla olika kategorier, lagt dem i en lista och blandat
        JPanel labelPanel = new JPanel();
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setVisible(true);
        label.setPreferredSize(new Dimension(600, 100));

        labelPanel.setPreferredSize(new Dimension(800, 150));
        labelPanel.setOpaque(false);

        labelPanel.add(label, SwingConstants.CENTER);
        panel.add(labelPanel);
    }
}
