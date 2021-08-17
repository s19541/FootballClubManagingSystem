package GUI;

import Controllers.MatchController;
import Model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StartedMatches {
    private JList finishedMatchesJList;
    private JPanel panelFinishedMatches;
    private JButton buttonReturn;
    private JButton buttonEdit;
    private JPanel panelStartedMatchesInside;
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

        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = finishedMatchesJList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(frame,
                            "You didn't select any match to edit!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setContentPane(new UpdateResult(frame, finishedMatches.get(selectedIndex)).getPanelUpdateResult());
                    frame.pack();
                }
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
