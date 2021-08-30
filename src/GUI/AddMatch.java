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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class for managing add match gui
 */
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

    /**
     * Constructor which setup panel for adding match
     */
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

    /**
     * Gets panel for adding match
     * @return panel for adding match
     */
    public JPanel getPanelAddMatch() {
        return panelAddMatch;
    }

    /**
     * Method which setup match date picker
     */
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

    /**
     * Method which setup match ticket prize picker
     */
    private void setupTicketPrizePicker(){
        textFieldTicketPrize.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                textFieldTicketPrizeKeyPressed(e);
            }
        });
    }

    /**
     * Method which setup match time picker
     */
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

    /**
     * Method which setup match opponent picker
     */
    private void setupOpponentPicker(){
        comboBoxOpponent.setModel(new DefaultComboBoxModel(ClubController.getClubsFromDb().toArray()));
    }

    /**
     * Method which setup match squad manager
     */
    private void setupSquadManager(){
        comboBoxFootballers.setModel(new DefaultComboBoxModel(PersonController.getFootballers().toArray()));

        buttonAddFootballer.addActionListener(e -> buttonAddFootballerActionPerformed(e));
        buttonRemoveFootballer.addActionListener(e -> buttonRemoveFootballerActionPerformed(e));
    }

    /**
     * Method which change options in combo box of days depending on selected month and year
     * @param e Unused
     */
    private void onChangeDateActionPerformed(ActionEvent e){
        int selectedDay = (int)comboBoxDay.getSelectedItem();
        comboBoxDay.setModel(new DefaultComboBoxModel(IntStream.rangeClosed(1, YearMonth.of((int)comboBoxYear.getSelectedItem(), comboBoxMonth.getSelectedIndex() + 1).lengthOfMonth()).boxed().collect(Collectors.toList()).toArray()));
        if (selectedDay <= comboBoxDay.getModel().getSize())
            comboBoxDay.setSelectedItem(selectedDay);
    }

    /**
     * Method responsible for controlling correctness of match ticket price in text field
     * @param e key event which allow to get pressed button
     */
    private void textFieldTicketPrizeKeyPressed(KeyEvent e){
        if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == 8) {
            textFieldTicketPrize.setEditable(true);
        } else {
            textFieldTicketPrize.setEditable(false);
        }
    }

    /**
     * Method responsible for controlling correctness of hour value in text field
     * @param e key event which allow to get pressed button
     */
    private void textFieldHourKeyPressed(KeyEvent e){
        String value = textFieldHour.getText();
        int l = value.length();
        if ((l == 0 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (l == 1 && Integer.parseInt(value) <= 1 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (l == 1 && Integer.parseInt(value) == 2 && e.getKeyChar() >= '0' && e.getKeyChar() <= '3') || e.getKeyChar() == 8) {
            textFieldHour.setEditable(true);
        } else {
            textFieldHour.setEditable(false);
        }
    }

    /**
     * Method responsible for controlling correctness of minute value in text field
     * @param e key event which allow to get pressed button
     */
    private void textFieldMinuteKeyPressed(KeyEvent e){
        String value = textFieldMinute.getText();
        int l = value.length();
        if ((l==0 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (l==1 && Integer.parseInt(value) <= 5 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == 8) {
            textFieldMinute.setEditable(true);
        } else {
            textFieldMinute.setEditable(false);
        }
    }

    /**
     * Method responsible for adding footballer to match squad
     * @param e Unused
     */
    private void buttonAddFootballerActionPerformed(ActionEvent e){
        Footballer footballer = (Footballer) comboBoxFootballers.getSelectedItem();
        if (footballer != null) {
            matchSquad.add(footballer);
            footballerListModel.addElement(footballer);
            comboBoxFootballers.removeItem(footballer);
        }
    }

    /**
     * Method responsible for removing footballer to match squad
     * @param e Unused
     */
    private void buttonRemoveFootballerActionPerformed(ActionEvent e){
        int[] selectedIndices = matchSquadJList.getSelectedIndices();
        if (selectedIndices.length == 0) {
            JOptionPane.showMessageDialog(panelAddMatch.getParent(),
                    "You didn't select any footballer!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            List<Footballer> footballersToDelete = new ArrayList<>();
            Arrays.stream(selectedIndices).forEach(o -> footballersToDelete.add(matchSquad.get(o)));
            footballersToDelete.stream().forEach(o -> {
                comboBoxFootballers.addItem(o);
                footballerListModel.removeElement(o);
                matchSquad.remove(o);
            });
        }
    }

    /**
     * Method which change panel to upcoming matches panel
     * @param e Unused
     */
    private void buttonReturnActionPerformed(ActionEvent e){
        GuiMethods.setPanel(new UpcomingMatches().getPanelUpcomingMatches());
    }

    /**
     * Method which add match to db and change panel to upcoming matches panel, controlling correctness of match data
     * @param e Unused
     */
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

    /**
     * Method which create match from data from components and add it to db
     * @throws Exception throw exception if match squad has invalid number of footballers
     */
    private void addMatch() throws Exception{
        Match newMatch = new Match(LocalDateTime.of((int)comboBoxYear.getSelectedItem(),(Month)comboBoxMonth.getSelectedItem(),(int)comboBoxDay.getSelectedItem(), Integer.parseInt(textFieldHour.getText()), Integer.parseInt(textFieldMinute.getText())), Integer.parseInt(textFieldTicketPrize.getText()), (Club)comboBoxOpponent.getSelectedItem());
        newMatch.setFootballers(matchSquad);
        MatchController.addMatch(newMatch);
    }

}
