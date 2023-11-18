package GUI;

import javax.swing.*;
import java.awt.*;

public class ChooseCategoryPage extends JPanel {

    JPanel northPanel;
    JPanel southPanel;

    JLabel categoryOption1;
    JLabel categoryOption2;
    JLabel categoryOption3;

    Color colorTheme;
    Color buttonColor;


    public ChooseCategoryPage(){
        northPanel = new JPanel();
        southPanel = new JPanel();

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

        generateCategoryPanels();

        generateNorthPanel();
        add(northPanel, BorderLayout.NORTH);

        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void generateNorthPanel(){
        northPanel.setLayout(new GridLayout(3, 1));
        northPanel.setPreferredSize(new Dimension(800,200));
        northPanel.setBackground(colorTheme);
        northPanel.setOpaque(true);
        northPanel.setVisible(true);

        JLabel empty = new JLabel();
        empty.setPreferredSize(new Dimension(800, 100));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);


        JLabel text = new JLabel("Välj en kategori", SwingConstants.CENTER);
        text.setFont(new Font("Open Sans", Font.PLAIN, 54));
        text.setPreferredSize(new Dimension(800, 100));
//        text.setVerticalAlignment(SwingConstants.CENTER);

   //     empty.add(text, BorderLayout.WEST);
        panel.add(text, BorderLayout.CENTER);
//        panel.add(text, BorderLayout.CENTER);

        northPanel.add(empty);
        northPanel.add(text);
    }
    public void generateCategoryPanels(){
        categoryOption1.setBackground(Color.blue);
        categoryOption2.setBackground(Color.GREEN);
        categoryOption3.setBackground(Color.ORANGE);
    }

}
