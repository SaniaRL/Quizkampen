package GUI;
import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressBar extends JProgressBar {

    int millis;
    int width;
    int height;
    private Timer timer;
    private final ProgressBarParent parent;
    AtomicInteger count;

    public ProgressBar(int sec, int height, int width, ProgressBarParent parent) {
        millis = sec * 1000;
        this.height = height;
        this.width = width;
        this.parent = parent;
        addComponents();
    }

    public void addComponents(){
        setValue(100);
        setBounds(0, 0, width, height);
        setVisible(true);

        count = new AtomicInteger(100);
        timer = new Timer(millis / 100, ActionEvent -> {
            count.getAndDecrement();
            setValue(count.get());

            if (count.get() == 0) {
                timer.stop();
                parent.onTimerEnded();
            }
        });
    }

    public void start() {
        if(timer.isRunning())timer.stop();
        count = new AtomicInteger(100);
        timer.start();
    }

    public void stop(){
        if(timer.isRunning())timer.stop();
    }
}
