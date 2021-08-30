package GUI;

import Controllers.MatchController;
import Model.Footballer;
import Model.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Class for managing add footballer gui
 */
public class AddFootballer {
    private JList addFootballerJList;
    private JPanel panelAddFootballer;
    private JButton buttonAdd;
    private JButton buttonReturn;
    private JPanel panelAddFootballerInside;
    private List<Footballer> footballers;
    private Match match;

    /**
     * Constructor which setup panel for adding footballer to match
     * @param match Match to which player is being added
     */
    public AddFootballer(Match match) {
        this.match = match;
        GuiMethods.changeTitle("Add player to squad vs " + match.getClub().getName());

        DefaultListModel<Footballer> footballerListModel = new DefaultListModel<>();
        footballers = MatchController.getFootballersOutOfSquad(match);
        footballerListModel.addAll(footballers);
        addFootballerJList.setModel(footballerListModel);

        buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
        buttonReturn.addActionListener(e -> buttonReturnActionPerformed(e));
    }

    /**
     * Gets panel for adding footballer to match
     * @return panel for adding footballer to match
     */
    public JPanel getPanelAddFootballer() {
        return panelAddFootballer;
    }

    /**
     * Method which add footballer to squad and change panel to match squad panel
     * @param e Unused
     */
    private void buttonAddActionPerformed(ActionEvent e){
        MatchController.addFootballerToSquad(match, footballers.get(addFootballerJList.getSelectedIndex()));
        GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
    }

    /**
     * Method which panel to match squad panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
    }
}
