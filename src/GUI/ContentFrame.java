package GUI;

import javax.swing.*;

public class ContentFrame extends JFrame {

    JPanel contentPanel;
    StartPage startPage;
    ChooseCategoryPage chooseCategoryPage;

    public ContentFrame(){

        contentPanel = new JPanel();
        startPage = new StartPage();
        chooseCategoryPage = new ChooseCategoryPage();

        buildFrame();
    }

    //TODO Remove main
    public static void main(String[] args){
        @SuppressWarnings("unused")
        ContentFrame contentFrame = new ContentFrame();
    }

    public void buildFrame(){
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Quizkampen");
        ImageIcon image = new ImageIcon("Icons/Sun.png");
        setIconImage(image.getImage());

        add(chooseCategoryPage);
        setVisible(true);
    }


}
