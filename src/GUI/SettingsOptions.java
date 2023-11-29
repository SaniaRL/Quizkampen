package GUI;

import Enums.ImageIconAvatar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingsOptions {
    private ImageIcon icon;
    private Color color;
    private Color detailColor;
    private String backgroundImagePath;
    private Image backgroundImage;
    private LineBorder border;
    private final List<ImageIcon> imageIcons;
    private final Font titleFont;
    private final Font bigText;
    private final Font smallText;
    private final Color textColor;
    private Color contrastColor;

    //WHERE TO PUT THESE?
    private String player1;
    private String player2;
    private ImageIcon player2Icon;


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
        player1 = "YOU";
        player2 = "OPPONENT";
        icon = imageIcons.get(0);
        player2Icon = new ImageIcon("Icons/questionmark.png");
    }

    public void addIcons(){

        imageIcons.add(new ImageIcon(ImageIconAvatar.CRAB.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.SQUID.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.LOBSTER.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.MONKEY.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.ELEPHANT.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.PIG.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.BUNNY.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.COW.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.SNAKE.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.PANDA.iconPath));
        imageIcons.add(new ImageIcon(ImageIconAvatar.TIGER.iconPath));
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

    public ImageIcon getIcon() {
        return new ImageIcon((icon.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH)));
    }

    public ImageIcon getBigIcon() {
        return new ImageIcon((icon.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH)));
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
        return new ImageIcon((player2Icon.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH)));
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

    public void setIcon(String iconPath) {
        this.icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setPlayer2Icon(String iconPath) {
        this.player2Icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
    }
    public void setPlayer2Icon(ImageIcon icon) {
        this.player2Icon = icon;
    }
}
