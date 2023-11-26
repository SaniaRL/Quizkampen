package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesignOptions {
    ImageIcon icon;
    Color color;
    Color detailColor;
    String backgroundImagePath;
    Image backgroundImage;
    LineBorder border;
    List<ImageIcon> imageIcons;
    Font titleFont;
    Font bigText;
    Font smallText;
    String player1;
    String player2;


    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public DesignOptions(){
        imageIcons = new ArrayList<>();
        addIcons();
        icon = imageIcons.get(0);
        color = Color.BLUE;
        detailColor = Color.CYAN;
        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        border = new LineBorder(color, 5);
        titleFont = new Font("Sans Serif", Font.PLAIN, 50);
        bigText = new Font("Sans Serif", Font.PLAIN, 40);
        smallText = new Font("Sans Serif", Font.PLAIN, 22);
        player1 = "YOU";
        player2 = "ÅKE";
    }

    public void addIcons(){
        imageIcons.add(new ImageIcon("Icons/crab.png"));
        imageIcons.add(new ImageIcon("Icons/worm.png"));
        imageIcons.add(new ImageIcon("Icons/squid.png"));
        imageIcons.add(new ImageIcon("Icons/lobster.png"));
        Collections.shuffle(imageIcons);
    }

    public ImageIcon getIcon() {
        return new ImageIcon((icon.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));
    }

    public ImageIcon getSmallIcon() {
        return new ImageIcon((icon.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH)));
    }

    public ImageIcon getBigIcon() {
        return new ImageIcon((icon.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH)));
    }

    public List<ImageIcon> getImageIcons() {
        return imageIcons;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Font getBigText() {
        return bigText;
    }

    public Font getSmallText() {
        return smallText;
    }

    public LineBorder getBorder() {
        return border;
    }

    public void setColor(String color) {
        switch (color.toLowerCase()){
            case "blue" -> {
                this.color = Color.BLUE;
                this.detailColor = Color.CYAN;
                this.backgroundImagePath = "Backgrounds/blueBackground.png";
                border = new LineBorder(this.color, 5);
                backgroundImage = new ImageIcon(backgroundImagePath).getImage();

            }
            case "violet" -> {
                this.color = new Color(112, 31, 69);
                this.detailColor = new Color(191, 112, 151);
                this.backgroundImagePath = "Backgrounds/violetBackground.png";
                border = new LineBorder(this.color, 5);
                backgroundImage = new ImageIcon(backgroundImagePath).getImage();
            }
        }
    }

    public Color getDetailColor() {
        return detailColor;
    }

    public Color getColor() {
        return color;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
