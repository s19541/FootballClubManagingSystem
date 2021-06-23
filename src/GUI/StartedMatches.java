package GUI;

import Controllers.MatchController;
import Models.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class StartedMatches {
    private JList finishedMatchesJList;
    private JPanel panelFinishedMatches;
    private JButton buttonReturn;
    private JFrame frame;
    private List<Match> finishedMatches;

    public StartedMatches(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Started Matches");
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
        finishedMatchesJList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    setBackground(getColor(index));
                    if (isSelected) {
                        setBackground(getBackground().darker());
                    }
                    return c;
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

    private Color getColor(int index){
        Color color;
        if (finishedMatches.get(index).getGoalsFor() > finishedMatches.get(index).getGoalsAgainst()) {
            color = Color.GREEN;
        } else if(finishedMatches.get(index).getGoalsFor() < finishedMatches.get(index).getGoalsAgainst()){
            color = Color.RED;
        } else{
            color = Color.YELLOW;
        }
        return color;
    }
}
