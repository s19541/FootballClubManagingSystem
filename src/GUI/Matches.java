package GUI;

import javax.swing.*;

public class Matches {
    private JButton button1;
    private JPanel panelMatches;
    private static JFrame frame;

    public Matches(JFrame frame){
        this.frame = frame;
        frame.setTitle("matches");
    }

    public JPanel getPanelMatches() {
        return panelMatches;
    }

    private void loadMatchScheduleFromDb(){

    }
}
