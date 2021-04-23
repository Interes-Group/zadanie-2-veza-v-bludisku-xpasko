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
    JPanel p2 = new JPanel();
    JFrame f1 = new JFrame();
    private int count = 1;

    public CreateMaze(DepthFirstSearch maze,Player player,CreateButtons buttons){
        this.Maze = maze;
        this.player = player;
        this.buttons = buttons;
    }


    public void mazeMapMaker(){
         var maze = Maze.getMaze();

         f1.setTitle("Rook in a Maze GAME");
         f1.setSize(630,780);
         f1.setLocationRelativeTo(null);
         f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                 int keyCode = e.getKeyCode();
                 switch (keyCode){
                     case KeyEvent.VK_UP:
                         moveCondition(maze,'u');
                         break;
                     case KeyEvent.VK_DOWN:
                         moveCondition(maze,'d');
                         break;
                     case KeyEvent.VK_LEFT:
                         moveCondition(maze,'l');
                         break;
                     case KeyEvent.VK_RIGHT:
                         moveCondition(maze,'r');
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
        var maze = Maze.getMaze();
        var b1 = buttons.getB1();
        var b2 = buttons.getB2();
        var b3 = buttons.getB3();
        var b4 = buttons.getB4();
        var b5 = buttons.getB5();
        b1.addActionListener(e -> reset());
        b2.addActionListener(e -> moveCondition(maze,'u')); //UP
        b3.addActionListener(e -> moveCondition(maze,'d'));//Down
        b4.addActionListener(e -> moveCondition(maze,'r'));//Right
        b5.addActionListener(e -> moveCondition(maze,'l'));//Left
    }

    public void mouse(){
        var maze = Maze.getMaze();
        highlighttiles();

        f1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var mouseX = e.getX();
                var mouseY = e.getY();
                if(e.getX() <= 620 && e.getY() <= 660) {
                    if (((mouseX - 10) / 20) >= player.getPosx() && ((mouseY - 50 / 20)) >= (player.getPosy())) {
                        System.out.println(mouseX);
                        System.out.println(mouseY);
                        repaint();
                    }
                    if (maze[((mouseY - 50) / 20)][(mouseX - 10) / 20].isReachable) {
                        player.setPosx((mouseX - 10) / 20);
                        player.setPosy((mouseY - 50) / 20);
                        repaint();
                        fixallreachable();
                        highlighttiles();
                        if(player.getPosy() == 29 && player.getPosx() == 29) reset();
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

               var mouseX = e.getX();
               var mouseY = e.getY();

               if(e.getX() <= 620 && e.getY() <= 660){
                   if(maze[(mouseY-50)/20][(mouseX-10)/20].isReachable){
                       maze[(mouseY-50)/20][(mouseX-10)/20].setReachablegreen(true);

                       if (maze[((mouseY-50)/20)][(mouseX-10)/20] != maze[prevposy][prevposx]){
                           maze[prevposy][prevposx].setReachablegreen(false);
                       }
                       prevposx = ((mouseX-10)/20);
                       prevposy = ((mouseY-50)/20);
                       repaint();
                    }

               }
            }

        });
    }

    private void highlighttiles(){
        var maze = Maze.getMaze();
        int i = 1;
        boolean testR = true;
        boolean testL = true;
        boolean testU = true;
        boolean testD = true;
        do {
            if (testR) {
                if (!maze[player.getPosy()][player.getPosx() + i].iswall)
                    maze[player.getPosy()][player.getPosx() + i].setReachable(true);
                else testR = false;
            }
            if (testL) {
                if (!maze[player.getPosy() + i][player.getPosx()].iswall)
                    maze[player.getPosy() + i][player.getPosx()].setReachable(true);
                else testL = false;
            }
            if (testU) {
                if (!maze[player.getPosy()][player.getPosx() - i].iswall)
                    maze[player.getPosy()][player.getPosx() - i].setReachable(true);
                else testU = false;
            }
            if (testD) {
                if (!maze[player.getPosy() - i][player.getPosx()].iswall)
                    maze[player.getPosy() - i][player.getPosx()].setReachable(true);
                else testD = false;
            }
            i++;
        } while (testR || testL || testU || testD);
    }


    private void fixallreachable(){
        var maze = Maze.getMaze();

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                maze[i][j].setReachablegreen(false);
                maze[i][j].setReachable(false);
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
        if(player.getPosy() == 29 && player.getPosx() == 29) reset();
        repaint();
    }

    public void reset(){
        Maze.generatemaze();
        var counter = buttons.getCounter();
        counter.setText("Pocet uspesnych: " + count);
        count++;
        highlighttiles();
        repaint();
    }

}



