package GUI;

import Model.Footballer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomCellRenderer extends JPanel implements ListCellRenderer<Object> {

    /*@Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object object, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        setLayout(new BorderLayout());

        ImageIcon deleteImage = new ImageIcon("delete_image.png");
        Image image = deleteImage.getImage();
        deleteImage = new ImageIcon(image.getScaledInstance(10,10, Image.SCALE_SMOOTH));

        JLabel label1 = new JLabel(object.toString(),JLabel.LEFT);
        JLabel label = new JLabel(deleteImage , JLabel.RIGHT);

        this.add(label1, BorderLayout.WEST);
        this.add(label, BorderLayout.EAST);
        System.out.println("321");


        //setText(object.toString());
        //setIcon(deleteImage);

        //setIconTextGap(20);
        //setHorizontalAlignment(SwingConstants.LEFT);
        //setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        return this;
    }*/
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    JLabel name = new JLabel(" ");
    JButton col1 = new JButton(" ");



    public CustomCellRenderer() {
        setLayout(new BorderLayout());
        add(name, BorderLayout.WEST);
        add(col1, BorderLayout.EAST);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100, 20);
    }

    /*@Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }*/



    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());


        name.setText(value.toString());
        ImageIcon deleteImage = new ImageIcon("delete_image.png");
        Image image = deleteImage.getImage();
        deleteImage = new ImageIcon(image.getScaledInstance(10,10, Image.SCALE_SMOOTH));
        col1.setIcon(deleteImage);
        col1.setMargin(new Insets(0, 0, 0, 0));
        col1.setBackground( null);
        col1.setBorderPainted(false);
        col1.setBorder(null);
        col1.setOpaque(false);
        col1.setContentAreaFilled(false);


        name.setForeground(getForeground());
        col1.setForeground(getForeground());

        setEnabled(list.isEnabled());

        name.setFont(list.getFont());
        col1.setFont(list.getFont());
        col1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
            }
        });

        return this;
    }
}
