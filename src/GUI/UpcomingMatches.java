package GUI;

import Controllers.MatchController;
import Model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpcomingMatches {
    private JPanel panelUpcomingMatches;
    private JList matchScheduleJList;
    private JButton buttonReturn;
    private JButton buttonShowFinished;
    private JButton buttonAdd;
    private JButton buttonEditSquad;
    private JPanel panelUpcomingMatchesInside;
    private List<Match> upcomingMatches;

    public UpcomingMatches(){
        GuiMethods.changeTitle("Match schedule");

        upcomingMatches = MatchController.getMatchSchedule();
        DefaultListModel<Match> matchScheduleListModel = new DefaultListModel<>();
        matchScheduleListModel.addAll(upcomingMatches);
        matchScheduleJList.setModel(matchScheduleListModel);

        buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
        buttonEditSquad.addActionListener(e -> buttonEditSquadActionPerformed(e));

        buttonReturn.addActionListener(e -> buttonReturnActionPerformed(e));
        buttonShowFinished.addActionListener(e -> buttonShowFinishedActionPerformed(e));
    }

    public JPanel getPanelUpcomingMatches() {
        return panelUpcomingMatches;
    }

    private void buttonAddActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new AddMatch().getPanelAddMatch());
    }

    private void buttonEditSquadActionPerformed(ActionEvent e){
        int selectedIndex = matchScheduleJList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(panelUpcomingMatches.getParent(),
                    "You didn't select any match to edit!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            GuiMethods.setPanel(new MatchSquad(upcomingMatches.get(selectedIndex)).getPanelMatchSquad());
        }
    }

    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new Menu().getPanelMain());
    }

    private void buttonShowFinishedActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new StartedMatches().getPanelFinishedMatches());
    }
}
