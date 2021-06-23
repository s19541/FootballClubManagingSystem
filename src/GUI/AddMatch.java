package GUI;



import Controllers.MatchController;
import Models.Club;
import Models.Footballer;
import Models.Match;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddMatch {
    private JPanel panelAddMatch;
    private JButton buttonAddMatch;
    private JList matchSquadJList;
    private JButton buttonAdd;
    private JTextField textFieldTicketPrize;
    private JButton buttonAddFootballer;
    private JComboBox comboBoxOpponent;
    private JComboBox comboBoxFootballers;
    private JComboBox comboBoxDay;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxYear;
    private JTextField textFieldHour;
    private JTextField textFieldMinute;

    public AddMatch(JFrame frame) {
        frame.setTitle("Add match");
        List<Footballer> matchSquad = new ArrayList<>();
        DefaultListModel<Footballer> footballerListModel = new DefaultListModel<>();
        for(Footballer footballer : matchSquad){
            footballerListModel.addElement(footballer);
        }
        matchSquadJList.setModel(footballerListModel);
        textFieldTicketPrize.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8) {
                    textFieldTicketPrize.setEditable(true);
                } else {
                    textFieldTicketPrize.setEditable(false);
                }
            }
        });
        textFieldHour.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textFieldHour.getText();
                int l = value.length();
                if ((l == 0 && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (l == 1 && Integer.parseInt(value) <= 1 && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (l == 1 && Integer.parseInt(value) == 2 && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '3') || ke.getKeyChar() == 8) {
                    textFieldHour.setEditable(true);
                } else {
                    textFieldHour.setEditable(false);
                }
            }
        });
        textFieldMinute.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textFieldMinute.getText();
                int l = value.length();
                if ((l==0 && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (l==1 && Integer.parseInt(value) <= 5 && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8) {
                    textFieldMinute.setEditable(true);
                } else {
                    textFieldMinute.setEditable(false);
                }
            }
        });
        comboBoxOpponent.setModel(new DefaultComboBoxModel(MatchController.getClubsFromDb().toArray()));
        Match match = new Match();
        comboBoxFootballers.setModel(new DefaultComboBoxModel(MatchController.getFootballersOutOfSquad(match).toArray()));
        comboBoxMonth.setModel(new DefaultComboBoxModel(Month.values()));
        comboBoxMonth.setSelectedItem(LocalDate.now().getMonth());
        comboBoxYear.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(LocalDate.now().getYear(), 2025).boxed().collect(Collectors.toList()).toArray()));
        comboBoxDay.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(1, YearMonth.of((int)comboBoxYear.getSelectedItem(), comboBoxMonth.getSelectedIndex() + 1).lengthOfMonth()).boxed().collect(Collectors.toList()).toArray()));
        comboBoxDay.setSelectedItem(LocalDate.now().getDayOfMonth());

        matchSquadJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                int index = list.locationToIndex(e.getPoint());
                if(e.getClickCount() == 2){
                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure to remove this footballer from squad?", "Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        comboBoxFootballers.addItem(matchSquad.get(index));
                        footballerListModel.removeElement(matchSquad.get(index));
                        matchSquad.remove(index);
                        //frame.setContentPane(new MatchSquad(frame, match).getPanelMatchSquad());
                        //frame.pack();
                    }
                }

            }
        });

        buttonAddMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new UpcomingMatches(frame).getPanelUpcomingMatches());
                frame.pack();
            }
        });
        buttonAddFootballer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Footballer footballer = (Footballer) comboBoxFootballers.getSelectedItem();
                if (footballer != null) {
                    matchSquad.add(footballer);
                    footballerListModel.addElement(footballer);
                    comboBoxFootballers.removeItem(footballer);
                }
            }
        });
        ActionListener onChangeDateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedDay = (int)comboBoxDay.getSelectedItem();
                comboBoxDay.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(1, YearMonth.of((int)comboBoxYear.getSelectedItem(), comboBoxMonth.getSelectedIndex() + 1).lengthOfMonth()).boxed().collect(Collectors.toList()).toArray()));
                if (selectedDay <= comboBoxDay.getModel().getSize())
                    comboBoxDay.setSelectedItem(selectedDay);
            }
        };
        comboBoxMonth.addActionListener(onChangeDateListener);
        comboBoxYear.addActionListener(onChangeDateListener);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Match newMatch = new Match(LocalDateTime.of((int)comboBoxYear.getSelectedItem(),(Month)comboBoxMonth.getSelectedItem(),(int)comboBoxDay.getSelectedItem(), Integer.parseInt(textFieldHour.getText()), Integer.parseInt(textFieldMinute.getText())), Integer.parseInt(textFieldTicketPrize.getText()), (Club)comboBoxOpponent.getSelectedItem());
                    newMatch.setFootballers(matchSquad);
                    MatchController.addMatch(newMatch);
                    frame.setContentPane(new UpcomingMatches(frame).getPanelUpcomingMatches());
                    frame.pack();
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                    if(exception.getMessage().equals("For input string: \"\""))
                        JOptionPane.showMessageDialog(frame,
                                "You have to fill all text fields",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame,
                                exception.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanelAddMatch() {
        return panelAddMatch;
    }
}
