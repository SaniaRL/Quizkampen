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

        generateCategoryLabels();

        generateNorthPanel();
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
        northPanel.setPreferredSize(new Dimension(800,200));
        northPanel.setOpaque(false);

        JLabel empty = new JLabel();
        empty.setPreferredSize(new Dimension(800, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);


        JLabel text = new JLabel("Välj en kategori", SwingConstants.CENTER);
        text.setFont(new Font("Open Sans", Font.PLAIN, 54));
        text.setPreferredSize(new Dimension(800, 100));
        text.setOpaque(false);

        panel.add(text, BorderLayout.CENTER);

        northPanel.add(empty);
        northPanel.add(text);
    }
    public void generateCategoryLabels(JLabel label){
        //Just nu gör denna alla fult rosa för att jag ska gitta i rätt kategori

        
    }

}
