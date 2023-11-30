package GUI;

import Enums.ImageIconAvatar;

import javax.swing.*;
import java.awt.Font;

public class MenuCreator extends JMenuBar{
    JMenu settingsMenu = new JMenu("Settings");
    JMenu backgroundMenu = new JMenu("Customize background");
    JMenu avatarMenu = new JMenu("Select avatar");
    JMenuItem itemSelectViolet = new JMenuItem("Violet");
    JMenuItem itemSelectGreen = new JMenuItem("Green");
    JMenuItem itemSelectBlue = new JMenuItem("Blue");
    JMenuItem itemSelectRed = new JMenuItem("Red");
    JMenuItem itemSelectYellow = new JMenuItem("Yellow");
    JMenuItem itemSelectPig = new JMenuItem("Pig");
    JMenuItem itemSelectLobster = new JMenuItem("Lobster");
    JMenuItem itemSelectMonkey = new JMenuItem("Monkey");
    JMenuItem itemSelectCrab = new JMenuItem("Crab");
    JMenuItem itemSelectTiger = new JMenuItem("Tiger");
    JMenuItem itemSelectCow = new JMenuItem("Cow");
    JMenuItem itemSelectSquid = new JMenuItem("Squid");
    JMenuItem itemSelectElephant = new JMenuItem("Elephant");
    JMenuItem itemSelectPanda = new JMenuItem("Panda");
    JMenuItem itemSelectSnake = new JMenuItem("Snake");
    JMenuItem itemSelectBunny = new JMenuItem("Bunny");
    JMenuItem itemExit = new JMenuItem("Exit the game");
    ContentFrame contentFrame;

    public MenuCreator(ContentFrame contentFrame){
        this.contentFrame = contentFrame;
        Font menuFont = new Font("Arial", Font.BOLD, 14);
        settingsMenu.setFont(menuFont);

        buildMenu();
    }

    public JMenuBar createMenu(ContentFrame contentFrame) {
        Font menuFont = new Font("Arial", Font.BOLD, 14);
        settingsMenu.setFont(menuFont);
        buildMenu();

        return new JMenuBar();
    }

    public void buildMenu() {
        backgroundMenu.add(itemSelectViolet);
        backgroundMenu.add(itemSelectGreen);
        backgroundMenu.add(itemSelectBlue);
        backgroundMenu.add(itemSelectRed);
        backgroundMenu.add(itemSelectYellow);

        avatarMenu.add(itemSelectPig);
        avatarMenu.add(itemSelectLobster);
        avatarMenu.add(itemSelectMonkey);
        avatarMenu.add(itemSelectCrab);
        avatarMenu.add(itemSelectTiger);
        avatarMenu.add(itemSelectCow);
        avatarMenu.add(itemSelectSquid);
        avatarMenu.add(itemSelectElephant);
        avatarMenu.add(itemSelectPanda);
        avatarMenu.add(itemSelectSnake);
        avatarMenu.add(itemSelectBunny);

        settingsMenu.add(backgroundMenu);
        settingsMenu.add(avatarMenu);
        settingsMenu.add(itemExit);

        add(settingsMenu);
    }
    public void addActionListenerToSettingsMenu() {
        itemSelectViolet.addActionListener(e -> {
            contentFrame.settingsOptions.setColor("violet");
            contentFrame.menuDesignAction();
        });
        itemSelectGreen.addActionListener(e -> {
            contentFrame.settingsOptions.setColor("green");
            contentFrame.menuDesignAction();
        });
        itemSelectRed.addActionListener(e -> {
            contentFrame.settingsOptions.setColor("red");
            contentFrame.menuDesignAction();
        });
        itemSelectYellow.addActionListener(e -> {
            contentFrame.settingsOptions.setColor("yellow");
            contentFrame.menuDesignAction();
        });
        itemSelectBlue.addActionListener(e -> {
            contentFrame.settingsOptions.setColor("blue");
            contentFrame.menuDesignAction();
        });
        itemSelectPig.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.PIG.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectLobster.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.LOBSTER.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectMonkey.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.MONKEY.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectCrab.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.CRAB.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectTiger.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.TIGER.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectCow.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.COW.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectSquid.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.SQUID.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectElephant.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.ELEPHANT.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectPanda.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.PANDA.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectSnake.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.SNAKE.iconPath);
            setIconAndPlayerNameAction();
        });
        itemSelectBunny.addActionListener(e -> {
            contentFrame.settingsOptions.setIcon(ImageIconAvatar.BUNNY.iconPath);
            setIconAndPlayerNameAction();
        });
        itemExit.addActionListener(e -> System.exit(0));
    }

    private void setIconAndPlayerNameAction(){
        contentFrame.setIconAndPlayerName();
        contentFrame.getContentPane().revalidate();
        contentFrame.getContentPane().repaint();
    }

}
