package GUI;

import javax.swing.*;
import java.awt.Font;

public class MenuCreator {

    public static JMenuBar createMenu(ContentFrame contentFrame) {
        JMenuBar menuBar = new JMenuBar();
        Font menuFont = new Font("Arial", Font.BOLD, 14);

        JMenu settingsMenu = new JMenu("Settings");
        JMenu backgroundMenu = new JMenu("Customize background");
        JMenu avatarMenu = new JMenu("Select avatar");

        settingsMenu.setFont(menuFont);

        JMenuItem itemSelectViolet = new JMenuItem("Violet");
        JMenuItem itemSelectGreen = new JMenuItem("Green");
        JMenuItem itemSelectBlue = new JMenuItem("Blue");
        JMenuItem itemSelectPig = new JMenuItem("Pig");
        JMenuItem itemSelectLobster = new JMenuItem("Lobster");
        JMenuItem itemSelectMonkey = new JMenuItem("Monkey");
        JMenuItem itemSelectCrab = new JMenuItem("Crab");
        JMenuItem itemSelectTiger = new JMenuItem("Tiger");
        JMenuItem itemExit = new JMenuItem("Exit the game");

        // Lägger till item:en till contentFrame
        contentFrame.itemSelectViolet = itemSelectViolet;
        contentFrame.itemSelectGreen = itemSelectGreen;
        contentFrame.itemSelectBlue = itemSelectBlue;
        contentFrame.itemSelectPig = itemSelectPig;
        contentFrame.itemSelectLobster = itemSelectLobster;
        contentFrame.itemSelectMonkey = itemSelectMonkey;
        contentFrame.itemSelectCrab = itemSelectCrab;
        contentFrame.itemSelectTiger = itemSelectTiger;
        contentFrame.settingsMenu = settingsMenu;
        contentFrame.backgroundMenu = backgroundMenu;
        contentFrame.avatarMenu = avatarMenu;
        contentFrame.itemExit = itemExit;

        settingsMenu.add(backgroundMenu);
        settingsMenu.add(avatarMenu);
        settingsMenu.add(itemExit);

        backgroundMenu.add(itemSelectViolet);
        backgroundMenu.add(itemSelectGreen);
        backgroundMenu.add(itemSelectBlue);
        avatarMenu.add(itemSelectPig);
        avatarMenu.add(itemSelectLobster);
        avatarMenu.add(itemSelectMonkey);
        avatarMenu.add(itemSelectCrab);
        avatarMenu.add(itemSelectTiger);

        menuBar.add(settingsMenu);

        return menuBar;
    }

}
