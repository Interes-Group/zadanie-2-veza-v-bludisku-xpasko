package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateMaze extends JPanel {

    private DepthFirstSearch Maze;
    private Player player;
    private CreateButtons buttons;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JFrame f1 = new JFrame();

    public CreateMaze(DepthFirstSearch maze,Player player,CreateButtons buttons){
        this.Maze = maze;
        this.player = player;
        this.buttons = buttons;
    }


    public void MazeMapMaker(){
         var maze = Maze.getMaze();
         f1.setTitle("policko");
         f1.setSize(630,780);
         f1.setLocationRelativeTo(null);
         f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


         p1.setLayout(new GridBagLayout());

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
                         UpCondition(maze);
                         break;
                     case KeyEvent.VK_DOWN:
                         DownCondition(maze);
                         break;
                     case KeyEvent.VK_LEFT:
                         LeftCondition(maze);
                         break;
                     case KeyEvent.VK_RIGHT:
                         RightCondition(maze);
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
               if(maze[i][j].isFinish) color = Color.GREEN;
               if(maze[i][j].isReachable) color = Color.PINK;
               if(maze[i][j].isReachablegreen) color = Color.GREEN;
               if(maze[i][j].getX() == player.getPosx() && maze[i][j].getY() == player.getPosy()) color = Color.RED;

               g.setColor(color);
               g.fillRect(20 * j,20 * (i + 1),20,20);
               g.setColor(Color.BLACK);
               g.drawRect(20 * j,20 * (i + 1), 20,20);
            }
         }
    }

    public void reset(){
        Maze.generatemaze();
        repaint();
    }

    public void buttons(){
        var maze = Maze.getMaze();
        var b1 = buttons.getB1();
        var b2 = buttons.getB2();
        var b3 = buttons.getB3();
        var b4 = buttons.getB4();
        var b5 = buttons.getB5();
        b1.addActionListener(e -> reset());
        b2.addActionListener(e -> UpCondition(maze));
        b3.addActionListener(e -> DownCondition(maze));
        b4.addActionListener(e -> RightCondition(maze));
        b5.addActionListener(e -> LeftCondition(maze));

}

    public void MouseMotionListener(MouseEvent e){


    }

    public void mouse(){
        highlighttiles();
        var maze = Maze.getMaze();
        f1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var mouseX = e.getX();
                var mouseY = e.getY();
                int i = 1;
                if(((mouseX-10)/20) >= player.getPosx() && ((mouseY-50/20)) >= (player.getPosy())){
                    System.out.println(mouseX);
                    System.out.println(mouseY);
                    repaint();
                }
                if(maze[((mouseY-50)/20)][(mouseX-10)/20].isReachable){
                    player.setPosx((mouseX-10)/20);
                    player.setPosy((mouseY-50)/20);
                    repaint();
                    fixallreachable();
                    highlighttiles();
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
               if(maze[(mouseY-50)/20][(mouseX-10)/20].isReachable){
                    maze[(mouseY-50)/20][(mouseX-10)/20].setReachablegreen(true);

                    if (maze[((mouseY-50)/20)][(mouseX-10)/20] != maze[prevposy][prevposx]){
                       maze[prevposy][prevposx].setReachablegreen(false);
                   }
                    prevposx = ((mouseX-10)/20);
                    prevposy = ((mouseY-50)/20);
                    repaint();
                    //fixallreachable(true);
            }
            }
        });
        //fixallreachable(false);
    }



    private void highlighttiles(){
        var maze = Maze.getMaze();
        int i = 1;
        while(true){
            if(maze[player.getPosy()][player.getPosx()+i].iswall)
                break;

            else{
                maze[player.getPosy()][player.getPosx()+i].setReachable(true);
                i++;
            }
        }
        i = 1;
        while(true){
            if(maze[player.getPosy()+i][player.getPosx()].iswall)
                break;
            else{
                maze[player.getPosy()+i][player.getPosx()].setReachable(true);
                i++;
            }
        }
        i = 1;
        while(true){
            if(maze[player.getPosy()][player.getPosx()-i].iswall)
                break;
            else{
                maze[player.getPosy()][player.getPosx()-i].setReachable(true);
                i++;
            }
        }
        i = 1;
        while(true){
            if(maze[player.getPosy()-i][player.getPosx()].iswall)
                break;
            else{
                maze[player.getPosy()-i][player.getPosx()].setReachable(true);
                i++;
            }
        }
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

    private void RightCondition(Cell[][] maze) {
        if(!maze[player.getPosy()][player.getPosx()+1].iswall){
            player.setPosx(player.getPosx()+1);
            System.out.println("right");
            if(player.getPosy() == 29 && player.getPosx() == 29) reset();
            repaint();
        }
    }

    private void LeftCondition(Cell[][] maze) {
        if(!maze[player.getPosy()][player.getPosx()-1].iswall){
            player.setPosx(player.getPosx()-1);
            System.out.println("left");
            if(player.getPosy() == 29 && player.getPosx() == 29) reset();
            repaint();
        }
    }


    private void DownCondition(Cell[][] maze) {
        if(!maze[player.getPosy()+1][player.getPosx()].iswall){
            player.setPosy(player.getPosy()+1);
            System.out.println("down");
            if(player.getPosy() == 29 && player.getPosx() == 29) reset();
            repaint();
        }
    }

    private void UpCondition(Cell[][] maze) {
        if(!maze[player.getPosy()-1][player.getPosx()].iswall){
            player.setPosy(player.getPosy()-1);
            System.out.println("up");
            if(player.getPosy() == 29 && player.getPosx() == 29) reset();
            repaint();
        }
    }

}


