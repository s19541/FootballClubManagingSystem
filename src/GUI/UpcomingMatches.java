package GUI;

import Controllers.MatchController;
import Model.Match;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Class for managing upcoming matches gui
 */
public class UpcomingMatches {
    private JPanel panelUpcomingMatches;
    private JList upcomingMatchesJList;
    private JButton buttonReturn;
    private JButton buttonShowFinished;
    private JButton buttonAdd;
    private JButton buttonEditSquad;
    private JPanel panelUpcomingMatchesInside;
    private List<Match> upcomingMatches;


    /**
     * Constructor which setup panel for upcoming matches
     */
    public UpcomingMatches(){
        GuiMethods.changeTitle("Upcoming matches");

        upcomingMatches = MatchController.getUpcomingMatches();
        DefaultListModel<Match> matchScheduleListModel = new DefaultListModel<>();
        matchScheduleListModel.addAll(upcomingMatches);
        upcomingMatchesJList.setModel(matchScheduleListModel);

        buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
        buttonEditSquad.addActionListener(e -> buttonEditSquadActionPerformed(e));

        buttonReturn.addActionListener(e -> buttonReturnActionPerformed(e));
        buttonShowFinished.addActionListener(e -> buttonShowFinishedActionPerformed(e));
    }

    /**
     * Gets panel for upcoming matches
     * @return panel for upcoming matches
     */
    public JPanel getPanelUpcomingMatches() {
        return panelUpcomingMatches;
    }

    /**
     * Method which change panel to add match panel
     * @param e Unused
     */
    private void buttonAddActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new AddMatch().getPanelAddMatch());
    }

    /**
     * Method which change panel to match squad panel if user select match from JList
     * @param e Unused
     */
    private void buttonEditSquadActionPerformed(ActionEvent e){
        int selectedIndex = upcomingMatchesJList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(panelUpcomingMatches.getParent(),
                    "You didn't select any match to edit!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            GuiMethods.setPanel(new MatchSquad(upcomingMatches.get(selectedIndex)).getPanelMatchSquad());
        }
    }

    /**
     * Method which change panel to main panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new Menu().getPanelMain());
    }

    /**
     * Method which change panel to finished matches panel
     * @param e Unused
     */
    private void buttonShowFinishedActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new StartedMatches().getPanelFinishedMatches());
    }
}
