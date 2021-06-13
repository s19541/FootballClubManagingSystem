package GUI;

import Controllers.MatchController;
import Models.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UpcomingMatches {
    private JPanel panelUpcomingMatches;
    private JList matchScheduleJList;
    private JButton buttonReturn;
    private JButton buttonShowFinished;
    private static JFrame frame;
    private List<Match> upcomingMatches;

    public UpcomingMatches(JFrame frame){
        this.frame = frame;
        frame.setTitle("match schedule");
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
                frame.setContentPane(new FinishedMatches(frame).getPanelFinishedMatches());
                frame.pack();
            }
        });
        matchScheduleJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                int index = list.locationToIndex(e.getPoint());
                if(e.getClickCount() == 2){
                    frame.setContentPane(new MatchSquad(frame, upcomingMatches.get(index)).getPanelMatchSquad());
                    frame.pack();
                }
            }
        });
    }

    public JPanel getPanelUpcomingMatches() {
        return panelUpcomingMatches;
    }

}
