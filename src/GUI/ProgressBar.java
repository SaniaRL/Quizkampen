package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBar extends JProgressBar {

    int millis;
    int width;
    int height;
    private Timer timer;

    public ProgressBar(int sec, int height, int width) {
        millis = sec * 1000;
        this.height = height;
        this.width = width;
        addComponents();
    }

    public void addComponents(){
        setValue(100);
        setForeground(Color.lightGray);
        setBackground(Color.WHITE);
        setBounds(0, 0, width, height);
        setVisible(true);

        timer = new Timer(millis / 100, new ActionListener() {
            int count = 100;

            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                setValue(count);

                if (count == 0) {
                    timer.stop();
                    // TODO Outside of this class
                }
            }
        });
    }

    public void start() {
        timer.start();
    }

}
