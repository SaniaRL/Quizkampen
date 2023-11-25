package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesignOptions {
    ImageIcon icon;
    Color color;
    List<ImageIcon> imageIcons = new ArrayList<>();

    //Icons as enum ??

    public DesignOptions(){
        addIcons();
        this.icon = imageIcons.get(0);
        this.color = Color.BLUE;
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

    public Color getColor() {
        return color;
    }
}
