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
            }
        });
        matchesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UpcomingMatches(frame).getPanelUpcomingMatches());
                frame.pack();
            }
        });
        ActionListener notAvailableActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "This feature isn't available yet!",
                        "INFORMATION",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        };
        personsButton.addActionListener(notAvailableActionListener);
        trainingsButton.addActionListener(notAvailableActionListener);
        leagueButton.addActionListener(notAvailableActionListener);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                DbConnectionController.stopConnectionWithDb();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        DbConnectionController.startConnectionWithDb();
        DbConnectionController.prepareExampleData();
        frame = new JFrame("Menu");
        frame.setContentPane(new Menu().panelMain);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}
