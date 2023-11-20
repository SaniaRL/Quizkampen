package GUI;

import javax.swing.*;
import java.awt.*;

public class WaitingPage extends JPanel {

    String backgroundImagePath;
    Image backgroundImage;
    JButton textButton;

    public WaitingPage(){
        backgroundImagePath = "Backgrounds/blueBackground.png";
        backgroundImage = (new ImageIcon(backgroundImagePath)).getImage();
        textButton = new JButton("Waiting for Opponent");

        textButton.setContentAreaFilled(false);
        textButton.setFont(new Font("Montserrat", Font.PLAIN, 40));
        textButton.setPreferredSize(new Dimension(800, 800));
        textButton.setBorder(BorderFactory.createEmptyBorder());
        add(textButton, SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public JButton getTextButton() {
        return textButton;
    }
}
