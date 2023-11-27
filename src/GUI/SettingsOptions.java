package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingsOptions {
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
    Color textColor;
    Color contrastColor;

    //WHERE TO PUT THESE?
    String player1;
    String player2;
    ImageIcon player2Icon;


    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public SettingsOptions(){
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
        textColor = Color.black;
        contrastColor = Color.BLACK;
        //TEMP:
        player1 = "SANIA";
        player2 = "ÅKE";
        icon = imageIcons.get(0);
        player2Icon = new ImageIcon("Icons/lobster.png");
    }

    public void addIcons(){
        imageIcons.add(new ImageIcon("Icons/crab.png"));
        imageIcons.add(new ImageIcon("Icons/squid.png"));
        imageIcons.add(new ImageIcon("Icons/lobster.png"));
        imageIcons.add(new ImageIcon("Icons/apa.png"));
        imageIcons.add(new ImageIcon("Icons/elefant.png"));
        imageIcons.add(new ImageIcon("Icons/gris.png"));
        imageIcons.add(new ImageIcon("Icons/kanin.png"));
        imageIcons.add(new ImageIcon("Icons/ko.png"));
        imageIcons.add(new ImageIcon("Icons/orm.png"));
        imageIcons.add(new ImageIcon("Icons/panda.png"));
        imageIcons.add(new ImageIcon("Icons/tiger.png"));
        Collections.shuffle(imageIcons);
    }

    public void setColor(String color) {
        switch (color.toLowerCase()) {
            case "black" -> {
                this.color = Color.BLACK;
                this.detailColor = Color.lightGray;
                this.backgroundImagePath = "Backgrounds/blackBackground.png";
                border = new LineBorder(this.color, 5);
                backgroundImage = new ImageIcon(backgroundImagePath).getImage();
                contrastColor = Color.WHITE;
            }
            case "violet" -> {
                this.color = new Color(112, 31, 69);
                this.detailColor = new Color(191, 112, 151);
                this.backgroundImagePath = "Backgrounds/violetBackground.png";
                border = new LineBorder(this.color, 5);
                backgroundImage = new ImageIcon(backgroundImagePath).getImage();
                contrastColor = Color.BLACK;
            }
            case "green" -> {
                this.color = new Color(54, 105, 39);
                this.detailColor = new Color(134, 191, 112);
                this.backgroundImagePath = "Backgrounds/greenBackground.png";
                border = new LineBorder(this.color, 5);
                backgroundImage = new ImageIcon(backgroundImagePath).getImage();
                contrastColor = Color.BLACK;
            }
            default -> {
                this.color = Color.BLUE;
                this.detailColor = Color.CYAN;
                this.backgroundImagePath = "Backgrounds/blueBackground.png";
                border = new LineBorder(this.color, 5);
                backgroundImage = new ImageIcon(backgroundImagePath).getImage();
                contrastColor = Color.BLACK;
            }
        }
    }
    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }


    public ImageIcon getIcon() {
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

    public Color getDetailColor() {
        return detailColor;
    }

    public Color getColor() {
        return color;
    }

    public ImageIcon getPlayer2Icon() {
        return new ImageIcon((player2Icon.getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH)));
    }

    public ImageIcon getBigPlayer2Icon() {
        return new ImageIcon((player2Icon.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH)));
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getContrastColor() {
        return contrastColor;
    }
}
