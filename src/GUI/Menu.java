package GUI;

import Controllers.DbConnectionController;
import Controllers.MatchController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Menu {
    private JButton personsButton;
    private JPanel panelMain;
    private JButton matchesButton;
    private JButton trainingsButton;
    private JButton leagueButton;
    private static JFrame frame;
    public Menu() {
        trainingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(MatchController.getMatchSchedule());
                System.out.println("saddsadsa");
            }
        });
        matchesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Matches(frame).getPanelMatches());
                frame.pack();
            }
        });
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                DbConnectionController.stopConnectionWithDb();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        DbConnectionController.startConnectionWithDb();
        frame = new JFrame("Menu");
        frame.setContentPane(new Menu().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
