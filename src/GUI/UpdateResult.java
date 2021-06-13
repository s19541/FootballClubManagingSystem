package GUI;

import Controllers.MatchController;
import Models.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class UpdateResult {
    private JPanel panelUpdateResult;
    private JLabel clubLabel;
    private JLabel dateLabel;
    private JTextField textFieldGoalsFor;
    private JTextField textFieldGoalsAgainst;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JFrame frame;
    private Match updatingMatch;

    public UpdateResult(JFrame frame, Match updatingMatch) {
        this.frame = frame;
        this.updatingMatch = updatingMatch;
        frame.setTitle("update result");
        clubLabel.setText(updatingMatch.getClub().getName());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        dateLabel.setText(updatingMatch.getDate().format(format));
        if(updatingMatch.getGoalsFor() != -1){
            textFieldGoalsFor.setText(String.valueOf(updatingMatch.getGoalsFor()));
            textFieldGoalsAgainst.setText(String.valueOf(updatingMatch.getGoalsAgainst()));
        }
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new FinishedMatches(frame).getPanelFinishedMatches());
                frame.pack();
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatingMatch.setGoalsFor(Integer.parseInt(textFieldGoalsFor.getText()));
                updatingMatch.setGoalsAgainst(Integer.parseInt(textFieldGoalsAgainst.getText()));
                MatchController.updateMatch(updatingMatch);
                frame.setContentPane(new FinishedMatches(frame).getPanelFinishedMatches());
                frame.pack();
            }
        });
    }

    public JPanel getPanelUpdateResult() {
        return panelUpdateResult;
    }
}
