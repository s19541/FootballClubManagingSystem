package GUI;

import Controllers.DbConnectionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Application for managing football club
 *
 * @author Marcin Wałachowski
 * @version 1.0
 * @since 2021-08-30
 */
public class Menu {
    private JButton peopleButton;
    private JPanel panelMain;
    private JButton matchesButton;
    private JButton trainingsButton;
    private JButton leagueButton;

    /**
     * Constructor which setup main panel
     */
    public Menu() {
        matchesButton.addActionListener(e -> buttonMatchesActionPerformed(e));
        ActionListener notAvailableActionListener = e -> notAvailableActionPerformed(e);

        peopleButton.addActionListener(notAvailableActionListener);
        trainingsButton.addActionListener(notAvailableActionListener);
        leagueButton.addActionListener(notAvailableActionListener);
    }

    /**
     * Main method which prepare application and open main menu
     * @param args Unused
     */
    public static void main(String[] args) {
        DbConnectionController.startConnectionWithDb();
        DbConnectionController.prepareExampleData();
        GuiMethods.setupFrame();
        GuiMethods.setPanel(new Menu().panelMain);
    }

    /**
     * Gets main panel
     * @return main panel
     */
    public JPanel getPanelMain() {
        return panelMain;
    }

    /**
     * Method which show message about no feature available
     * @param e Unused
     */
    private void notAvailableActionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(panelMain.getParent(),
                "This feature isn't available yet!",
                "INFORMATION",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method which change panel to upcoming matches panel
     * @param e Unused
     */
    private void buttonMatchesActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }
}
