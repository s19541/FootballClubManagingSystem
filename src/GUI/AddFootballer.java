package GUI;

import Controllers.MatchController;
import Model.Footballer;
import Model.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddFootballer {
    private JList addFootballerJList;
    private JPanel panelAddFootballer;
    private JButton buttonAdd;
    private JButton buttonReturnl;
    private JFrame frame;

    public AddFootballer(JFrame frame, Match match) {
        this.frame = frame;
        frame.setTitle("Add player to squad vs " + match.getClub().getName());
        DefaultListModel<Footballer> footballerListModel = new DefaultListModel<>();
        List<Footballer> footballers = new ArrayList<>();
        for(Footballer footballer : MatchController.getFootballersOutOfSquad(match)){
            footballers.add(footballer);
            footballerListModel.addElement(footballer);
        }
        addFootballerJList.setModel(footballerListModel);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    MatchController.addFootballerToSquad(match, footballers.get(addFootballerJList.getSelectedIndex()));
                    frame.setContentPane(new MatchSquad(frame, match).getPanelMatchSquad());
                    frame.pack();
            }
        });
        buttonReturnl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.setContentPane(new MatchSquad(frame, match).getPanelMatchSquad());
                    frame.pack();
            }
        });
    }

    public JPanel getPanelAddFootballer() {
        return panelAddFootballer;
    }
}
