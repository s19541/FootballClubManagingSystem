package GUI;

import Controllers.MatchController;
import Model.Footballer;
import Model.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchSquad {
    private JList matchSquadJList;
    private JPanel panelMatchSquad;
    private JButton buttonReturn;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JPanel panelMatchSquadInside;
    Match match;

    public MatchSquad(Match match) {
        this.match = match;
        GuiMethods.changeTitle("Match squad vs " + match.getClub().getName());

        DefaultListModel footballerListModel = new DefaultListModel<>();
        footballerListModel.addAll(match.getFootballers());
        matchSquadJList.setModel(footballerListModel);

        buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
        buttonRemove.addActionListener(e -> buttonRemoveActionPerformed(e));
        buttonReturn.addActionListener(e -> buttonReturnActionPerformed(e));
    }

    public JPanel getPanelMatchSquad() {
        return panelMatchSquad;
    }

    private void buttonAddActionPerformed(ActionEvent e){
        if(match.getFootballers().size() >= 20){
            JOptionPane.showMessageDialog(panelMatchSquad.getParent(),
                    "Squad has maximum number of footballers!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            GuiMethods.setPanel(new AddFootballer(match).getPanelAddFootballer());
        }
    }

    private void buttonRemoveActionPerformed(ActionEvent e){
        int selectedIndex = matchSquadJList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(panelMatchSquad.getParent(),
                    "You didn't select any footballer!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (match.getFootballers().size() <= 14) {
                JOptionPane.showMessageDialog(panelMatchSquad.getParent(),
                        "Squad must have at least 14 footballers!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                MatchController.removeFootballerFromSquad(match, match.getFootballers().get(matchSquadJList.getSelectedIndex()));
                GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
            }
        }
    }

    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }
}


