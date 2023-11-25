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
        titleFont = new Font("Cabin", Font.BOLD, 50);
        bigText = new Font("Cabin", Font.BOLD, 34);
        smallText = new Font("Cabin", Font.BOLD, 22);
    }

    public void addIcons(){
        imageIcons.add(new ImageIcon("Icons/crab.png"));
        imageIcons.add(new ImageIcon("Icons/worm.png"));
        imageIcons.add(new ImageIcon("Icons/squid.png"));
        imageIcons.add(new ImageIcon("Icons/lobster.png"));
        Collections.shuffle(imageIcons);
    }

    public ImageIcon getIcon() {
        return icon;
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
}
