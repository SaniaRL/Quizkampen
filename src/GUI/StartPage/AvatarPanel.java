package GUI.StartPage;

import Enums.ImageIconAvatar;
import GUI.SettingsOptions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvatarPanel extends JPanel {

    JLabel iconLabel;
    JButton leftButton;
    JButton rightButton;

    int iconIndex;

    List<ImageIconAvatar> imageIconAvatarList;
    List<ImageIcon> imageIconList;
    SettingsOptions settingsOptions;

    public AvatarPanel() {

        iconLabel = new JLabel();
        leftButton = new JButton();
        rightButton = new JButton();
        imageIconAvatarList = new ArrayList<>();
        imageIconList = new ArrayList<>();

        iconIndex = 0;

        settingsOptions = new SettingsOptions();

        addIconsToList();
        addComponents();
    }

    public void addComponents(){
        setPreferredSize(new Dimension(300, 100));
        setLayout(new BorderLayout());
        setOpaque(false);

        leftButton.setText("<");
        leftButton.setFont(new Font("Sans Serif", Font.BOLD, 30));
        leftButton.setPreferredSize(new Dimension(100, 100));
        leftButton.setBorder(settingsOptions.getBorder());
        leftButton.setBackground(settingsOptions.getDetailColor());
        rightButton.setText(">");
        rightButton.setFont(new Font("Sans Serif", Font.BOLD, 30));
        rightButton.setPreferredSize(new Dimension(100, 100));
        rightButton.setBorder(settingsOptions.getBorder());
        rightButton.setBackground(settingsOptions.getDetailColor());

        add(leftButton, BorderLayout.WEST);
        generateIconLabel();
        add(iconLabel, BorderLayout.CENTER);
        add(rightButton, BorderLayout.EAST);
    }

    public void addIconsToList(){
        imageIconAvatarList = Enums.ImageIconAvatar.getAll();
        for(ImageIconAvatar imageIconAvatar : imageIconAvatarList){
            imageIconList.add(new ImageIcon(imageIconAvatar.iconPath));
        }
        Collections.shuffle(imageIconList);
    }

    public void generateIconLabel(){
        iconLabel.setPreferredSize(new Dimension(100, 100));
        iconLabel.setOpaque(false);
        iconLabel.setIcon(new ImageIcon(imageIconList.get(iconIndex).getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH)));

    }

    public void nextImage(){
        if(iconIndex == imageIconList.size() - 1){
            iconIndex = 0;
        }else{
            iconIndex++;
        }
        generateIconLabel();
    }

    public void previousImage(){
        if(iconIndex == 0){
            iconIndex = imageIconList.size()-1;
        }else{
            iconIndex--;
        }
        generateIconLabel();
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    public void setSettingsOptions(SettingsOptions settingsOptions) {
        this.settingsOptions = settingsOptions;
    }
}
