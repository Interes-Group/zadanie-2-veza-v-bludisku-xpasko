package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.intialization.CreateButtons;
import sk.stuba.fei.uim.oop.intialization.DepthFirstSearch;
import sk.stuba.fei.uim.oop.intialization.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateMaze extends JPanel {

    private final DepthFirstSearch Maze;
    private final Player player;
    private final CreateButtons buttons;
    private int count = 0;
    JFrame f1 = new JFrame();

    public CreateMaze(DepthFirstSearch maze,Player player,CreateButtons buttons){
        this.Maze = maze;
        this.player = player;
        this.buttons = buttons;
    }


    public void mazeMapMaker(){
         f1.setTitle("Rook in a Maze GAME");
         f1.setSize(630,780);
         f1.setLocationRelativeTo(null);
         f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         var p2 = buttons.getP2();
         p2 = buttons.create();
         f1.add(p2,BorderLayout.SOUTH);
         f1.add(this);
         f1.setVisible(true);

         mouse();
         buttons();

         f1.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent e) {

             }

             @Override
             public void keyPressed(KeyEvent e) {
                 switch (e.getKeyCode()){
                     case KeyEvent.VK_UP:
                         moveCondition(Maze.getMaze(),'u');
                         break;
                     case KeyEvent.VK_DOWN:
                         moveCondition(Maze.getMaze(),'d');
                         break;
                     case KeyEvent.VK_LEFT:
                         moveCondition(Maze.getMaze(),'l');
                         break;
                     case KeyEvent.VK_RIGHT:
                         moveCondition(Maze.getMaze(),'r');
                         break;
                 }
             }
             @Override
             public void keyReleased(KeyEvent e){}
         });

    }

    public void paint(Graphics g){
         super.paint(g);

         var maze = Maze.getMaze();
         Maze.getMaze()[29][29].setFinish(true);

         for(int i = 0;i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
               Color color;
               if (maze[i][j].isIswall()) color = Color.BLACK;
               else color = Color.WHITE;
               if(maze[i][j].isReachable) color = Color.PINK;
               if(maze[i][j].isReachablegreen) color = Color.GREEN;
               if(maze[i][j].isFinish) color = Color.GREEN;
               if(maze[i][j].isFinish && maze[i][j].isReachablegreen) color = Color.CYAN;
               if(maze[i][j].getX() == player.getPosx() && maze[i][j].getY() == player.getPosy()) color = Color.RED;

               g.setColor(color);
               g.fillRect(20 * j,20 * (i + 1),20,20);
               g.setColor(Color.BLACK);
               g.drawRect(20 * j,20 * (i + 1), 20,20);
            }
         }
    }

    public void buttons(){
        buttons.getB1().addActionListener(e -> reset(false));
        buttons.getB2().addActionListener(e -> moveCondition(Maze.getMaze(),'u')); //UP
        buttons.getB3().addActionListener(e -> moveCondition(Maze.getMaze(),'d'));//Down
        buttons.getB4().addActionListener(e -> moveCondition(Maze.getMaze(),'r'));//Right
        buttons.getB5().addActionListener(e -> moveCondition(Maze.getMaze(),'l'));//Left
    }

    public void mouse(){
        highlighttiles();

        f1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getX() <= 620 && e.getY() <= 660) {
                    if (((e.getX() - 10) / 20) >= player.getPosx() && ((e.getY() - 50 / 20)) >= (player.getPosy())) {
                        repaint();
                    }
                    if (Maze.getMaze()[((e.getY() - 50) / 20)][(e.getX() - 10) / 20].isReachable) {
                        player.setPosx((e.getX() - 10) / 20);
                        player.setPosy((e.getY() - 50) / 20);
                        repaint();
                        fixallreachable();
                        highlighttiles();
                        if(player.getPosy() == 29 && player.getPosx() == 29) reset(true);
                    }
                }
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        f1.addMouseMotionListener(new MouseMotionListener() {
            int prevposx = 0;
            int prevposy = 0;

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
               if(e.getX() <= 620 && e.getY() <= 660){
                   if(Maze.getMaze()[(e.getY()-50)/20][(e.getX()-10)/20].isReachable){
                       Maze.getMaze()[(e.getY()-50)/20][(e.getX()-10)/20].setReachablegreen(true);

                       if (Maze.getMaze()[((e.getY()-50)/20)][(e.getX()-10)/20] != Maze.getMaze()[prevposy][prevposx]){
                           Maze.getMaze()[prevposy][prevposx].setReachablegreen(false);
                       }
                       prevposx = ((e.getX()-10)/20);
                       prevposy = ((e.getY()-50)/20);
                       repaint();
                    }
               }
            }

        });
    }

    private void highlighttiles(){
        int i = 1;
        boolean testR = true;
        boolean testL = true;
        boolean testU = true;
        boolean testD = true;
        do {
            if (testR) {
                if (!Maze.getMaze()[player.getPosy()][player.getPosx() + i].iswall)
                    Maze.getMaze()[player.getPosy()][player.getPosx() + i].setReachable(true);
                else testR = false;
            }
            if (testL) {
                if (!Maze.getMaze()[player.getPosy() + i][player.getPosx()].iswall)
                    Maze.getMaze()[player.getPosy() + i][player.getPosx()].setReachable(true);
                else testL = false;
            }
            if (testU) {
                if (!Maze.getMaze()[player.getPosy()][player.getPosx() - i].iswall)
                    Maze.getMaze()[player.getPosy()][player.getPosx() - i].setReachable(true);
                else testU = false;
            }
            if (testD) {
                if (!Maze.getMaze()[player.getPosy() - i][player.getPosx()].iswall)
                    Maze.getMaze()[player.getPosy() - i][player.getPosx()].setReachable(true);
                else testD = false;
            }
            i++;
        } while (testR || testL || testU || testD);
    }


    private void fixallreachable(){
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                Maze.getMaze()[i][j].setReachablegreen(false);
                Maze.getMaze()[i][j].setReachable(false);
            }
        }
    }

    private void moveCondition(Cell[][] maze, char c) {
        if(c == 'r') {
            if (!maze[player.getPosy()][player.getPosx() + 1].iswall) {
                fixallreachable();
                player.setPosx(player.getPosx() + 1);
            }
        }
        if(c == 'l') {
            if (!maze[player.getPosy()][player.getPosx() - 1].iswall) {
                fixallreachable();
                player.setPosx(player.getPosx() - 1);
            }
        }
        if(c == 'd') {
            if (!maze[player.getPosy() + 1][player.getPosx()].iswall) {
                fixallreachable();
                player.setPosy(player.getPosy() + 1);
            }
        }
        if(c == 'u'){
            if(!maze[player.getPosy()-1][player.getPosx()].iswall){
                fixallreachable();
                player.setPosy(player.getPosy()-1);
            }
        }
        highlighttiles();
        if(player.getPosy() == 29 && player.getPosx() == 29) reset(true);
        repaint();
    }

    public void reset(boolean win){
        Maze.generatemaze();
        var counter = buttons.getCounter();
        if(win) count++;
        counter.setText("Pocet uspesnych: " + count);
        highlighttiles();
        repaint();
    }

}



