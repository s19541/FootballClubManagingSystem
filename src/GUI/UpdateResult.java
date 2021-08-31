package GUI;

import Controllers.MatchController;
import Model.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for managing update match result gui
 */
public class UpdateResult {
    private JPanel panelUpdateResult;
    private JLabel opponentLabel;
    private JLabel dateLabel;
    private JTextField textFieldGoalsFor;
    private JTextField textFieldGoalsAgainst;
    private JButton buttonSave;
    private JButton buttonCancel;
    private Match updatingMatch;
    private Thread timer;

    /**
     * Constructor which setup panel for update match result
     * @param updatingMatch match which is being updated
     */
    public UpdateResult(Match updatingMatch) {
        this.updatingMatch = updatingMatch;
        GuiMethods.changeTitle("Update result");

        setupOpponentLabel();
        setupScore();
        setupDateLabel();

        buttonCancel.addActionListener(e -> buttonCancelActionPerformed(e));
        buttonSave.addActionListener(e -> buttonSaveActionPerformed(e));
    }

    /**
     * Gets panel for update match result
     * @return panel for update match result
     */
    public JPanel getPanelUpdateResult() {
        return panelUpdateResult;
    }

    /**
     * Method which setup opponent label
     */
    private void setupOpponentLabel(){
        opponentLabel.setText(updatingMatch.getClub().getName());
    }

    /**
     * Method which setup score of updating match
     */
    private void setupScore(){
        if(updatingMatch.getGoalsFor() != null){
            textFieldGoalsFor.setText(String.valueOf(updatingMatch.getGoalsFor()));
            textFieldGoalsAgainst.setText(String.valueOf(updatingMatch.getGoalsAgainst()));
        }
    }

    /**
     * Method which setup date label
     */
    private void setupDateLabel(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        dateLabel.setText(updatingMatch.getDate().format(format));

        if(updatingMatch.isRunning()){
            timer  = new Thread(() -> {
                while(true){
                    Duration duration = Duration.between(updatingMatch.getDate(), LocalDateTime.now());
                    dateLabel.setText(updatingMatch.getDate().format(format)
                            + " (" + (duration.toMinutes() >= 10 ? duration.toMinutes() : "0" + duration.toMinutes())
                            + ":" + (duration.toSeconds()%60 >= 10 ? duration.toSeconds()%60 : "0" + duration.toSeconds()%60) + ")");
                }
            });
            timer.start();
        }
    }

    /**
     * Method which change panel to finished matches panel
     * @param e Unused
     */
    private void buttonCancelActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new StartedMatches().getPanelFinishedMatches());
    }

    /**
     * Method which checking correctness of match result and when data are correct, save result to db and change panel to finished match panel
     * @param e Unused
     */
    private void buttonSaveActionPerformed(ActionEvent e){
        if(textFieldGoalsFor.getText().equals(""))
            textFieldGoalsFor.setText("0");
        if(textFieldGoalsAgainst.getText().equals(""))
            textFieldGoalsAgainst.setText("0");
        try {
            if (Integer.parseInt(textFieldGoalsFor.getText()) < 0 || Integer.parseInt(textFieldGoalsAgainst.getText()) < 0) {
                JOptionPane.showMessageDialog(panelUpdateResult.getParent(),
                        "Result can't be negative!",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                updatingMatch.setGoalsFor(Integer.parseInt(textFieldGoalsFor.getText()));
                updatingMatch.setGoalsAgainst(Integer.parseInt(textFieldGoalsAgainst.getText()));
                MatchController.updateMatch(updatingMatch);
                GuiMethods.setPanel(new StartedMatches().getPanelFinishedMatches());
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(panelUpdateResult.getParent(),
                    "Entered wrong result!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
