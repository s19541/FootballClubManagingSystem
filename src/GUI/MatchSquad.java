package GUI;

import Controllers.MatchController;
import Model.Footballer;
import Model.Match;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for managing match squad gui
 */
public class MatchSquad {
    private JList matchSquadJList;
    private JPanel panelMatchSquad;
    private JButton buttonReturn;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JPanel panelMatchSquadInside;
    Match match;

    /**
     * Constructor which setup panel for match squad
     * @param match Match which squad is displayed
     */
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

    /**
     * Gets panel for match squad
     * @return panel for match squad
     */
    public JPanel getPanelMatchSquad() {
        return panelMatchSquad;
    }

    /**
     * Method which change panel to add footballer panel if match squad doesn't have maximum number of players
     * @param e Unused
     */
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

    /**
     * Method which remove footballer from squad if user select footballer and squad doesn't have minimum number of players
     * @param e Unused
     */
    private void buttonRemoveActionPerformed(ActionEvent e){
        int[] selectedIndices = matchSquadJList.getSelectedIndices();

        if (selectedIndices.length == 0) {
            JOptionPane.showMessageDialog(panelMatchSquad.getParent(),
                    "You didn't select any footballer!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (match.getFootballers().size() - selectedIndices.length < 14) {
                JOptionPane.showMessageDialog(panelMatchSquad.getParent(),
                        "Squad must have at least 14 footballers!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                List<Footballer> footballersToDelete = new ArrayList<>();
                Arrays.stream(selectedIndices).forEach(o -> footballersToDelete.add(match.getFootballers().get(o)));
                footballersToDelete.stream().forEach(o -> MatchController.removeFootballerFromSquad(match, o));
                GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
            }
        }
    }

    /**
     * Method which change panel to upcoming matches panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }
}


