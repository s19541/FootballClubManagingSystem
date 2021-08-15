package GUI;

import Controllers.MatchController;
import Model.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
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
    private Thread timer;

    public UpdateResult(JFrame frame, Match updatingMatch) {
        this.frame = frame;
        this.updatingMatch = updatingMatch;
        frame.setTitle("Update result");
        clubLabel.setText(updatingMatch.getClub().getName());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        dateLabel.setText(updatingMatch.getDate().format(format));
        if(updatingMatch.getGoalsFor() != -1){
            textFieldGoalsFor.setText(String.valueOf(updatingMatch.getGoalsFor()));
            textFieldGoalsAgainst.setText(String.valueOf(updatingMatch.getGoalsAgainst()));
        }
        if(updatingMatch.isRunning()){
            timer  = new Thread(){
                @Override
                public void run() {
                    while(true)
                        dateLabel.setText(updatingMatch.getDate().format(format)
                            + " (" + ((LocalDateTime.now().getMinute() - updatingMatch.getDate().getMinute()) >= 10 ? (LocalDateTime.now().getMinute() - updatingMatch.getDate().getMinute()) : "0" + (LocalDateTime.now().getMinute() - updatingMatch.getDate().getMinute()))
                            + ":" + ((LocalDateTime.now().getSecond() - updatingMatch.getDate().getSecond()) >= 10 ? (LocalDateTime.now().getSecond() - updatingMatch.getDate().getSecond()) : "0" + (LocalDateTime.now().getSecond() - updatingMatch.getDate().getSecond())) + ")");
                }
            };
            timer.start();
        }
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer != null)
                    timer.stop();
                frame.setContentPane(new StartedMatches(frame).getPanelFinishedMatches());
                frame.pack();
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer != null)
                    timer.stop();
                if(textFieldGoalsFor.getText().equals(""))
                    textFieldGoalsFor.setText("0");
                if(textFieldGoalsAgainst.getText().equals(""))
                    textFieldGoalsAgainst.setText("0");
                if(Integer.parseInt(textFieldGoalsFor.getText()) < 0 || Integer.parseInt(textFieldGoalsAgainst.getText()) < 0) {
                        JOptionPane.showMessageDialog(frame,
                                "Result can't be negative!",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                }else {
                    updatingMatch.setGoalsFor(Integer.parseInt(textFieldGoalsFor.getText()));
                    updatingMatch.setGoalsAgainst(Integer.parseInt(textFieldGoalsAgainst.getText()));
                    MatchController.updateMatch(updatingMatch);
                    frame.setContentPane(new StartedMatches(frame).getPanelFinishedMatches());
                    frame.pack();
                }
            }
        });
    }

    public JPanel getPanelUpdateResult() {
        return panelUpdateResult;
    }
}
