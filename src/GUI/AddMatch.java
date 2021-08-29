package GUI;



import Controllers.ClubController;
import Controllers.MatchController;
import Controllers.PersonController;
import Model.Club;
import Model.Footballer;
import Model.Match;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddMatch {
    private JPanel panelAddMatch;
    private JButton buttonReturn;
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
    private JButton buttonRemoveFootballer;
    private JPanel panelAddMatchInside;
    private JPanel panelDate;
    private JPanel panelTime;
    private List<Footballer> matchSquad;
    private DefaultListModel<Footballer> footballerListModel;

    public AddMatch() {
        GuiMethods.changeTitle("Add match");
        matchSquad = new ArrayList<>();
        footballerListModel = new DefaultListModel<>();
        matchSquadJList.setModel(footballerListModel);

        setupDatePicker();
        setupTimePicker();
        setupTicketPrizePicker();
        setupOpponentPicker();
        setupSquadManager();

        buttonReturn.addActionListener(e -> buttonReturnActionPerformed(e));
        buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
    }

    public JPanel getPanelAddMatch() {
        return panelAddMatch;
    }

    private void setupDatePicker(){
        comboBoxMonth.setModel(new DefaultComboBoxModel(Month.values()));
        comboBoxMonth.setSelectedItem(LocalDate.now().getMonth());
        comboBoxYear.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(LocalDate.now().getYear()-2, LocalDate.now().getYear()+2).boxed().collect(Collectors.toList()).toArray()));
        comboBoxYear.setSelectedItem(LocalDate.now().getYear());
        comboBoxDay.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(1, YearMonth.of((int)comboBoxYear.getSelectedItem(), comboBoxMonth.getSelectedIndex() + 1).lengthOfMonth()).boxed().collect(Collectors.toList()).toArray()));
        comboBoxDay.setSelectedItem(LocalDate.now().getDayOfMonth());

        ActionListener onChangeDateListener = e -> onChangeDateActionPerformed(e);
        comboBoxMonth.addActionListener(onChangeDateListener);
        comboBoxYear.addActionListener(onChangeDateListener);
    }

    private void setupTicketPrizePicker(){
        textFieldTicketPrize.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                textFieldTicketPrizeKeyPressed(e);
            }
        });
    }

    private void setupTimePicker(){
        textFieldHour.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                textFieldHourKeyPressed(e);
            }
        });

        textFieldMinute.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                textFieldMinuteKeyPressed(e);
            }
        });
    }

    private void setupOpponentPicker(){
        comboBoxOpponent.setModel(new DefaultComboBoxModel(ClubController.getClubsFromDb().toArray()));
    }

    private void setupSquadManager(){
        comboBoxFootballers.setModel(new DefaultComboBoxModel(PersonController.getFootballers().toArray()));

        buttonAddFootballer.addActionListener(e -> buttonAddFootballerActionPerformed(e));
        buttonRemoveFootballer.addActionListener(e -> buttonRemoveFootballerActionPerformed(e));
    }

    private void onChangeDateActionPerformed(ActionEvent e){
        int selectedDay = (int)comboBoxDay.getSelectedItem();
        comboBoxDay.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(1, YearMonth.of((int)comboBoxYear.getSelectedItem(), comboBoxMonth.getSelectedIndex() + 1).lengthOfMonth()).boxed().collect(Collectors.toList()).toArray()));
        if (selectedDay <= comboBoxDay.getModel().getSize())
            comboBoxDay.setSelectedItem(selectedDay);
    }

    private void textFieldTicketPrizeKeyPressed(KeyEvent e){
        if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == 8) {
            textFieldTicketPrize.setEditable(true);
        } else {
            textFieldTicketPrize.setEditable(false);
        }
    }

    private void textFieldHourKeyPressed(KeyEvent e){
        String value = textFieldHour.getText();
        int l = value.length();
        if ((l == 0 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (l == 1 && Integer.parseInt(value) <= 1 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (l == 1 && Integer.parseInt(value) == 2 && e.getKeyChar() >= '0' && e.getKeyChar() <= '3') || e.getKeyChar() == 8) {
            textFieldHour.setEditable(true);
        } else {
            textFieldHour.setEditable(false);
        }
    }

    private void textFieldMinuteKeyPressed(KeyEvent e){
        String value = textFieldMinute.getText();
        int l = value.length();
        if ((l==0 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (l==1 && Integer.parseInt(value) <= 5 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == 8) {
            textFieldMinute.setEditable(true);
        } else {
            textFieldMinute.setEditable(false);
        }
    }

    private void buttonAddFootballerActionPerformed(ActionEvent e){
        Footballer footballer = (Footballer) comboBoxFootballers.getSelectedItem();
        if (footballer != null) {
            matchSquad.add(footballer);
            footballerListModel.addElement(footballer);
            comboBoxFootballers.removeItem(footballer);
        }
    }

    private void buttonRemoveFootballerActionPerformed(ActionEvent e){
        int selectedIndex = matchSquadJList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(panelAddMatch.getParent(),
                    "You didn't select any footballer!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            comboBoxFootballers.addItem(matchSquad.get(selectedIndex));
            footballerListModel.removeElement(matchSquad.get(selectedIndex));
            matchSquad.remove(selectedIndex);
        }
    }

    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }

    private void buttonAddActionPerformed(ActionEvent e){
        try{
            addMatch();
            GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            if(exception.getMessage().equals("For input string: \"\""))
                JOptionPane.showMessageDialog(panelAddMatch.getParent(),
                        "You have to fill all text fields",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(panelAddMatch.getParent(),
                        exception.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMatch() throws Exception{
        Match newMatch = new Match(LocalDateTime.of((int)comboBoxYear.getSelectedItem(),(Month)comboBoxMonth.getSelectedItem(),(int)comboBoxDay.getSelectedItem(), Integer.parseInt(textFieldHour.getText()), Integer.parseInt(textFieldMinute.getText())), Integer.parseInt(textFieldTicketPrize.getText()), (Club)comboBoxOpponent.getSelectedItem());
        newMatch.setFootballers(matchSquad);
        MatchController.addMatch(newMatch);
    }

}
