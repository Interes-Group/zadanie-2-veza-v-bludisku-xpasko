package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateButtons extends JPanel{

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    public JButton getB4() {
        return b4;
    }

    public JButton getB5() {
        return b5;
    }

    JButton b1 = new JButton("RESET");
    JButton b2 = new JButton("↑");
    JButton b3 = new JButton("↓");
    JButton b4 = new JButton("→");
    JButton b5 = new JButton("←");
    Graphics g;

    JPanel p1 = new JPanel();
    GridBagConstraints c = new GridBagConstraints();
    GridBagLayout panelLayout = new GridBagLayout();

    public JPanel create(){

        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);

        p1.setLayout(panelLayout);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        p1.add(b1,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        p1.add(b2,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        var label = new JLabel("something");
        p1.add(label);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        p1.add(b3,c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        p1.add(b4,c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        p1.add(b5,c);

        return p1;
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == b1){
//            f1.setTitle("Something Actually happened");
//        }
//    }
}
