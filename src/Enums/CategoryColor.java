package Enums;

import java.awt.*;

public enum CategoryColor {
    MOVIES(new Color(255,27,27), "Film"),
    HISTORY(new Color(37,255,154), "Historia"),
    SPORT(new Color(255,180,4), "Sport"),
    MUSIC(new Color(255,82,210), "Musik"),
    ANIMALS(new Color(20,245,4), "Djur"),
    LITERATURE(new Color(255,247,2), "Litteratur"),
    TECHNOLOGY(new Color(27,203,255), "Teknologi"),
    SPACE(new Color(196,0,255), "Rymden");

    public final Color color;
    public final String colorName;
    CategoryColor(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
    }
    public static Color getColor(String category) {
        for (CategoryColor categoryColor : CategoryColor.values()) {
            if (categoryColor.colorName.equalsIgnoreCase(category)) {
                return categoryColor.color;
            }
        }
        return Color.BLACK;
    }
}
