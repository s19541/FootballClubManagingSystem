package GUI;

import Controllers.MatchController;
import Model.Match;
import javax.swing.*;
import java.awt.event.ActionEvent;

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
     * @param match Match which squad is being displayed
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

    /**
     * Method which change panel to upcoming matches panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }
}


