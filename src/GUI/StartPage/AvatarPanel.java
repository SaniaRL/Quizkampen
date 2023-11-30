package GUI.StartPage;

import Enums.ImageIconAvatar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvatarPanel extends JPanel {

    JLabel iconLabel;
    JButton leftButton;
    JButton rightButton;

    List<ImageIconAvatar> imageIconAvatarList;
    List<ImageIcon> imageIconList;

    public AvatarPanel() {

        iconLabel = new JLabel();
        leftButton = new JButton();
        rightButton = new JButton();
        imageIconAvatarList = new ArrayList<>();
        imageIconList = new ArrayList<>();

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
        rightButton.setText(">");
        rightButton.setFont(new Font("Sans Serif", Font.BOLD, 30));
        rightButton.setPreferredSize(new Dimension(100, 100));

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
        iconLabel.setIcon(new ImageIcon(imageIconList.get(0).getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH)));

    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }
}
