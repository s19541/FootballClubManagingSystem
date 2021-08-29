package GUI;

import Controllers.DbConnectionController;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class GuiMethods extends JFrame{
    private static JFrame frame;

    public static void setupFrame(){
        frame = new JFrame("Menu");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("football_image.png");
        frame.setIconImage(img.getImage());
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                DbConnectionController.stopConnectionWithDb();
                System.exit(0);
            }
        });
    }

    public static void setPanel(JPanel panel){
        frame.setContentPane(panel);
        frame.pack();
    }

    public static void changeTitle(String title){
        frame.setTitle(title);
    }
}
