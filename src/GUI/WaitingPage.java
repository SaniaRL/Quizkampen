package GUI;

import javax.swing.*;
import java.awt.*;

public class WaitingLabel extends JPanel {

    String backgroundImagePath;
    Image backgroundImage;
    String text;

    public WaitingLabel(String backgroundImagePath, String text){
        this.backgroundImagePath = backgroundImagePath;
        this.backgroundImage = (new ImageIcon(backgroundImagePath)).getImage();
    }
}
