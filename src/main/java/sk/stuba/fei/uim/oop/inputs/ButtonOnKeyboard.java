package sk.stuba.fei.uim.oop.inputs;

import sk.stuba.fei.uim.oop.CreateMaze;
import sk.stuba.fei.uim.oop.intialization.DepthFirstSearch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonOnKeyboard {

    private final CreateMaze maze;
    private final DepthFirstSearch DFS;

    public ButtonOnKeyboard(CreateMaze maze,DepthFirstSearch DFS) {
        this.maze = maze;
        this.DFS = DFS;
    }

    public void buttonKeyboardAction(){
        maze.getF1().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        maze.moveCondition(DFS.getMaze(),'u');
                        break;
                    case KeyEvent.VK_DOWN:
                        maze.moveCondition(DFS.getMaze(),'d');
                        break;
                    case KeyEvent.VK_LEFT:
                        maze.moveCondition(DFS.getMaze(),'l');
                        break;
                    case KeyEvent.VK_RIGHT:
                        maze.moveCondition(DFS.getMaze(),'r');
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e){}
        });
    }
}
