package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class CreateMaze extends JFrame {

    private DepthFirstSearch Maze;
    private Player player;
    private panel buttons;
    JPanel panel = new JPanel();

    public CreateMaze(DepthFirstSearch maze,Player player,panel buttons){
        this.Maze = maze;
        this.player = player;
        this.buttons = buttons;
    }


    public void MazeMapMaker(){
         setTitle("policko");
         setSize(640,480);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
         this.add(buttons);

      }

      public void paint(Graphics g){
         super.paint(g);

         var maze = Maze.getMaze();
         Maze.getMaze()[player.getPosx()][player.getPosy()] = 2;
         Maze.getMaze()[11][11] = 3;

         for(int i = 0;i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
               Color color;
               switch (maze[i][j]){
                  case 1: color = Color.BLACK; break;
                  case 2: color = Color.RED;  break;
                  case 3: color = Color.GREEN; break;
                  default: color = Color.WHITE;
               }

               g.setColor(color);
               g.fillRect(30 * j,30 * (i + 1),30,30);
               g.setColor(Color.BLACK);
               g.drawRect(30 * j,30 * (i + 1), 30,30);

                }
         }
    }
}

