package GUI;

import Controllers.MatchController;
import Models.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FinishedMatches {
    private JList finishedMatchesJList;
    private JPanel panelFinishedMatches;
    private JButton buttonReturn;
    private JFrame frame;
    private List<Match> finishedMatches;

    public FinishedMatches(JFrame frame) {
        this.frame = frame;
        frame.setTitle("finished Matches");
        finishedMatches = MatchController.getFinishedMatches();
        DefaultListModel<Match> finishedMatchesListModel = new DefaultListModel<>();
        for(Match match : finishedMatches){
            finishedMatchesListModel.addElement(match);
        }
        finishedMatchesJList.setModel(finishedMatchesListModel);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UpcomingMatches(frame).getPanelUpcomingMatches());
                frame.pack();
            }
        });
        finishedMatchesJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                int index = list.locationToIndex(e.getPoint());
                if(e.getClickCount() == 2){
                    frame.setContentPane(new UpdateResult(frame, finishedMatches.get(index)).getPanelUpdateResult());
                    frame.pack();
                }
            }
        });
    }

    public JPanel getPanelFinishedMatches() {
        return panelFinishedMatches;
    }
}
