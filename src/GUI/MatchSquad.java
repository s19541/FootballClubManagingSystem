package GUI;

import Controllers.MatchController;
import Model.Footballer;
import Model.Match;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchSquad {
    private JList matchSquadJList;
    private JPanel panelMatchSquad;
    private JButton buttonReturn;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JPanel panelMatchSquadInside;
    private JFrame frame;

    public MatchSquad(JFrame frame, Match match) {
        this.frame = frame;
        frame.setTitle("Match squad vs " + match.getClub().getName());

        DefaultListModel footballerListModel = new DefaultListModel<>();

        ImageIcon deleteImage = new ImageIcon("delete_image.png");
        Image image = deleteImage.getImage();
        deleteImage = new ImageIcon(image.getScaledInstance(10,10, Image.SCALE_SMOOTH));

        for(Footballer footballer : match.getFootballers()){
            footballerListModel.addElement(footballer);
        }
        matchSquadJList.setModel(footballerListModel);

        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UpcomingMatches(frame).getPanelUpcomingMatches());
                frame.pack();
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(match.getFootballers().size() >= 20){
                    JOptionPane.showMessageDialog(frame,
                            "Squad has maximum number of footballers!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    frame.setContentPane(new AddFootballer(frame, match).getPanelAddFootballer());
                    frame.pack();
                }
            }
        });
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = matchSquadJList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(frame,
                            "You didn't select any footballer!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (match.getFootballers().size() <= 14) {
                        JOptionPane.showMessageDialog(frame,
                                "Squad must have at least 14 footballers!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        MatchController.removeFootballerFromSquad(match, match.getFootballers().get(matchSquadJList.getSelectedIndex()));
                        frame.setContentPane(new MatchSquad(frame, match).getPanelMatchSquad());
                        frame.pack();
                    }
                }
            }
        });
    }

    public JPanel getPanelMatchSquad() {
        return panelMatchSquad;
    }
}


