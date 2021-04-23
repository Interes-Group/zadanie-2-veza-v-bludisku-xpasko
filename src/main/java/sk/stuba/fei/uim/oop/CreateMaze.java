package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.inputs.ButtonOnKeyboard;
import sk.stuba.fei.uim.oop.inputs.CreateButtons;
import sk.stuba.fei.uim.oop.inputs.Mouse;
import sk.stuba.fei.uim.oop.intialization.*;
import javax.swing.*;
import java.awt.*;

public class CreateMaze extends JPanel {

    private final DepthFirstSearch Maze;
    private final Player player;
    private final CreateButtons buttons;
    private final Mouse mouse;
    private final ButtonOnKeyboard buttonsKeyboard;
    private int count = 0;

    public JFrame getF1() {
        return f1;
    }

    JFrame f1 = new JFrame();

    public CreateMaze(DepthFirstSearch maze,Player player){
        this.Maze = maze;
        this.player = player;
        buttons = new CreateButtons(this,Maze);
        mouse = new Mouse(this,Maze,player);
        buttonsKeyboard = new ButtonOnKeyboard(this,Maze);
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
         mouse.mouseAction();
         buttons.buttonAction();
         buttonsKeyboard.buttonKeyboardAction();
    }

    public void paint(Graphics g){
         super.paint(g);

         var maze = Maze.getMaze();
         Maze.getMaze()[29][29].setFinish(true);

         for(int i = 0;i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
               Color color;
               if (maze[i][j].isWall()) color = Color.BLACK;
               else color = Color.WHITE;
               if(maze[i][j].isReachable()) color = Color.PINK;
               if(maze[i][j].isReachableGreen()) color = Color.GREEN;
               if(maze[i][j].isFinish()) color = Color.GREEN;
               if(maze[i][j].isFinish() && maze[i][j].isReachableGreen()) color = Color.CYAN;
               if(maze[i][j].getX() == player.getPosX() && maze[i][j].getY() == player.getPosY()) color = Color.RED;

               g.setColor(color);
               g.fillRect(20 * j,20 * (i + 1),20,20);
               g.setColor(Color.BLACK);
               g.drawRect(20 * j,20 * (i + 1), 20,20);
            }
         }
    }

    public void highlightTiles(){
        int i = 1;
        boolean testR = true;
        boolean testL = true;
        boolean testU = true;
        boolean testD = true;
        do {
            if (testR) {
                if (!Maze.getMaze()[player.getPosY()][player.getPosX() + i].isWall())
                    Maze.getMaze()[player.getPosY()][player.getPosX() + i].setReachable(true);
                else testR = false;
            }
            if (testL) {
                if (!Maze.getMaze()[player.getPosY() + i][player.getPosX()].isWall())
                    Maze.getMaze()[player.getPosY() + i][player.getPosX()].setReachable(true);
                else testL = false;
            }
            if (testU) {
                if (!Maze.getMaze()[player.getPosY()][player.getPosX() - i].isWall())
                    Maze.getMaze()[player.getPosY()][player.getPosX() - i].setReachable(true);
                else testU = false;
            }
            if (testD) {
                if (!Maze.getMaze()[player.getPosY() - i][player.getPosX()].isWall())
                    Maze.getMaze()[player.getPosY() - i][player.getPosX()].setReachable(true);
                else testD = false;
            }
            i++;
        } while (testR || testL || testU || testD);
    }


    public void fixAllReachable(){
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                Maze.getMaze()[i][j].setReachableGreen(false);
                Maze.getMaze()[i][j].setReachable(false);
            }
        }
    }

    public void moveCondition(Cell[][] maze, char c) {
        if(c == 'r') {
            if (!maze[player.getPosY()][player.getPosX() + 1].isWall()) {
                fixAllReachable();
                player.setPosX(player.getPosX() + 1);
            }
        }
        if(c == 'l') {
            if (!maze[player.getPosY()][player.getPosX() - 1].isWall()) {
                fixAllReachable();
                player.setPosX(player.getPosX() - 1);
            }
        }
        if(c == 'd') {
            if (!maze[player.getPosY() + 1][player.getPosX()].isWall()) {
                fixAllReachable();
                player.setPosY(player.getPosY() + 1);
            }
        }
        if(c == 'u'){
            if(!maze[player.getPosY()-1][player.getPosX()].isWall()){
                fixAllReachable();
                player.setPosY(player.getPosY()-1);
            }
        }
        highlightTiles();
        if(player.getPosY() == 29 && player.getPosX() == 29) reset(true);
        repaint();
    }

    public void reset(boolean win){
        Maze.generateMaze();
        var counter = buttons.getCounter();
        if(win) count++;
        counter.setText("Pocet uspesnych: " + count);
        highlightTiles();
        repaint();
    }

}



