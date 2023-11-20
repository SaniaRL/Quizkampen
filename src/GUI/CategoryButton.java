package GUI;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CategoryButton extends JButton {

    String category;
    String backgroundImagePath;
    Image backgroundImage;
    ImageIcon background;

    public CategoryButton(String category, String backgroundImagePath) {
        this.category = category;
        this.backgroundImagePath = backgroundImagePath;
        this.backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        this.background = new ImageIcon(backgroundImagePath);

        setIcon(background);
        setText(category);
        setFont(new Font("Arial", Font.PLAIN, 22));
        setForeground(Color.WHITE);
        setBorder(new LineBorder(Color.BLUE, 5));

    }
}