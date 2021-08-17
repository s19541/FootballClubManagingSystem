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
    private static JFrame frame;
    private List<Match> upcomingMatches;

    public UpcomingMatches(JFrame frame){
        this.frame = frame;
        frame.setTitle("Match schedule");
        upcomingMatches = MatchController.getMatchSchedule();
        DefaultListModel<Match> matchScheduleListModel = new DefaultListModel<>();
        for(Match match : upcomingMatches){
            matchScheduleListModel.addElement(match);
        }
        matchScheduleJList.setModel(matchScheduleListModel);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Menu().getPanelMain());
                frame.pack();
            }
        });
        buttonShowFinished.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new StartedMatches(frame).getPanelFinishedMatches());
                frame.pack();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new AddMatch(frame).getPanelAddMatch());
                frame.pack();
            }
        });
        buttonEditSquad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = matchScheduleJList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(frame,
                            "You didn't select any match to edit!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setContentPane(new MatchSquad(frame, upcomingMatches.get(selectedIndex)).getPanelMatchSquad());
                    frame.pack();
                }
            }
        });
    }

    public JPanel getPanelUpcomingMatches() {
        return panelUpcomingMatches;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
