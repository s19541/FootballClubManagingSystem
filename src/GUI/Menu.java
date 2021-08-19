package GUI;

import Controllers.DbConnectionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton personsButton;
    private JPanel panelMain;
    private JButton matchesButton;
    private JButton trainingsButton;
    private JButton leagueButton;
    public Menu() {
        matchesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonMatchesActionPerformed(e);
            }
        });

        ActionListener notAvailableActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notAvailableActionPerformed(e);
            }
        };

        personsButton.addActionListener(notAvailableActionListener);
        trainingsButton.addActionListener(notAvailableActionListener);
        leagueButton.addActionListener(notAvailableActionListener);
    }

    public static void main(String[] args) {
        DbConnectionController.startConnectionWithDb();
        DbConnectionController.prepareExampleData();
        GuiMethods.setupFrame();
        GuiMethods.setPanel(new Menu().panelMain);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void notAvailableActionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(panelMain.getParent(),
                "This feature isn't available yet!",
                "INFORMATION",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void buttonMatchesActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }
}
