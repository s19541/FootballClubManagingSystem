package GUI;

import Controllers.MatchController;
import Models.Footballer;
import Models.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchSquad {
    private JList matchSquadJList;
    private JPanel panelMatchSquad;
    private JButton buttonReturn;
    private JFrame frame;

    public MatchSquad(JFrame frame, Match match) {
        this.frame = frame;
        frame.setTitle("Match squad vs " + match.getClub().getName());
        DefaultListModel<Footballer> finishedMatchesListModel = new DefaultListModel<>();
        for(Footballer footballer : MatchController.getMatchSquad(match)){
            finishedMatchesListModel.addElement(footballer);
        }
        matchSquadJList.setModel(finishedMatchesListModel);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UpcomingMatches(frame).getPanelUpcomingMatches());
                frame.pack();
            }
        });
    }

    public JPanel getPanelMatchSquad() {
        return panelMatchSquad;
    }
}
