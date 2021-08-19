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
    private JButton buttonReturn;
    private JPanel panelAddFootballerInside;
    private List<Footballer> footballers;
    private Match match;

    public AddFootballer(Match match) {
        this.match = match;
        GuiMethods.changeTitle("Add player to squad vs " + match.getClub().getName());

        DefaultListModel<Footballer> footballerListModel = new DefaultListModel<>();
        footballers = new ArrayList<>();
        for(Footballer footballer : MatchController.getFootballersOutOfSquad(match)){
            footballers.add(footballer);
            footballerListModel.addElement(footballer);
        }
        addFootballerJList.setModel(footballerListModel);

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    buttonAddActionPerformed(e);
            }
        });

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    buttonReturnActionPerformed(e);
            }
        });

    }

    public JPanel getPanelAddFootballer() {
        return panelAddFootballer;
    }

    private void buttonAddActionPerformed(ActionEvent e){
        MatchController.addFootballerToSquad(match, footballers.get(addFootballerJList.getSelectedIndex()));
        GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
    }

    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
    }
}
