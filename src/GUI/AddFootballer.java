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
     * @param match Match to which player is added
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
        int[] selectedIndices = addFootballerJList.getSelectedIndices();
        if(selectedIndices.length == 0){
            JOptionPane.showMessageDialog(panelAddFootballer.getParent(),
                    "You didn't select any footballer!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if(match.getFootballers().size() + selectedIndices.length > 20){
            JOptionPane.showMessageDialog(panelAddFootballer.getParent(),
                    "Squad can't have more than 20 footballers!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            List<Footballer> footballersToAdd = new ArrayList<>();
            Arrays.stream(selectedIndices).forEach(o -> footballersToAdd.add(footballers.get(o)));
            footballersToAdd.stream().forEach(o -> {
                MatchController.addFootballerToSquad(match, o);
            });

            GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
        }
    }

    /**
     * Method which panel to match squad panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new MatchSquad(match).getPanelMatchSquad());
    }
}
