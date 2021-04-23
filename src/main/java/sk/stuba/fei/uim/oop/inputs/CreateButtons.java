package sk.stuba.fei.uim.oop.inputs;

import sk.stuba.fei.uim.oop.CreateMaze;
import sk.stuba.fei.uim.oop.intialization.DepthFirstSearch;

import javax.swing.*;
import java.awt.*;

public class CreateButtons extends JPanel{

    public CreateButtons(CreateMaze maze, DepthFirstSearch DFS) {
        this.maze = maze;
        this.DFS = DFS;
    }

    private final CreateMaze maze;
    private final DepthFirstSearch DFS;

    JButton b1 = new JButton("RESET");
    JButton b2 = new JButton("↑");
    JButton b3 = new JButton("↓");
    JButton b4 = new JButton("→");
    JButton b5 = new JButton("←");
    JLabel counter = new JLabel("Pocet uspesnych: 0");
    JPanel p2 = new JPanel();


    public JPanel getP2() {
        return p2;
    }
    public JLabel getCounter() {
        return counter;
    }

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
        p1.add(counter);


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

    public void buttonAction(){
        b1.addActionListener(e -> maze.reset(false));
        b2.addActionListener(e -> maze.moveCondition(DFS.getMaze(),'u')); //UP
        b3.addActionListener(e -> maze.moveCondition(DFS.getMaze(),'d'));//Down
        b4.addActionListener(e -> maze.moveCondition(DFS.getMaze(),'r'));//Right
        b5.addActionListener(e -> maze.moveCondition(DFS.getMaze(),'l'));//Left
    }
}
