package GUI;

import Controllers.MatchController;
import Model.Match;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for managing started matches gui
 */
public class StartedMatches {
    private JList finishedMatchesJList;
    private JPanel panelFinishedMatches;
    private JButton buttonReturn;
    private JButton buttonEdit;
    private JPanel panelStartedMatchesInside;
    private List<Match> startedMatches;

    /**
     * Constructor which setup panel for started matches
     */
    public StartedMatches() {
        GuiMethods.changeTitle("Started Matches");

        startedMatches = MatchController.getStartedMatches();
        DefaultListModel<Match> finishedMatchesListModel = new DefaultListModel<>();
        finishedMatchesListModel.addAll(startedMatches);
        finishedMatchesJList.setModel(finishedMatchesListModel);

        buttonReturn.addActionListener(e -> buttonReturnActionPerformed(e));
        buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));

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

    /**
     * Gets panel for started matches
     * @return panel for started matches
     */
    public JPanel getPanelFinishedMatches() {
        return panelFinishedMatches;
    }

    /**
     * Gets color of match in JList
     * @param index index of match in started matches list
     * @return color of match in JList
     */
    private Color getColor(int index){
        Color color;

        if(startedMatches.get(index).getGoalsFor() == null || startedMatches.get(index).getGoalsAgainst() == null){
            color = Color.YELLOW;
            return color;
        }

        if (startedMatches.get(index).getGoalsFor() > startedMatches.get(index).getGoalsAgainst()) {
            color = Color.GREEN;
        } else if(startedMatches.get(index).getGoalsFor() < startedMatches.get(index).getGoalsAgainst()){
            color = Color.RED;
        } else{
            color = Color.YELLOW;
        }
        return color;
    }

    /**
     * Method which change panel to upcoming matches panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }

    /**
     * Method which change panel to update match result panel if user select match in JList
     * @param e Unused
     */
    private void buttonEditActionPerformed(ActionEvent e){
        int selectedIndex = finishedMatchesJList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(panelFinishedMatches.getParent(),
                    "You didn't select any match to edit!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            GuiMethods.setPanel((new UpdateResult(startedMatches.get(selectedIndex)).getPanelUpdateResult()));
        }
    }
}
