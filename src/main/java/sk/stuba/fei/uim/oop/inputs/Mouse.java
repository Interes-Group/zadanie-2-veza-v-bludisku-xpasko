package sk.stuba.fei.uim.oop.inputs;

import sk.stuba.fei.uim.oop.CreateMaze;
import sk.stuba.fei.uim.oop.intialization.DepthFirstSearch;
import sk.stuba.fei.uim.oop.intialization.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class Mouse {

    private final Player player;
    private final CreateMaze maze;
    private final DepthFirstSearch DFS;


    public Mouse(CreateMaze maze, DepthFirstSearch DFS,Player player) {
        this.maze = maze;
        this.DFS = DFS;
        this.player = player;
    }

    public void mouseAction(){
        maze.highlightTiles();
        maze.getF1().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getX() <= 620 && e.getY() <= 660) {
                    if (((e.getX() - 10) / 20) >= player.getPosX() && ((e.getY() - 50 / 20)) >= (player.getPosY())) {
                        maze.repaint();
                    }
                    if (DFS.getMaze()[((e.getY() - 50) / 20)][(e.getX() - 10) / 20].isReachable()) {
                        player.setPosX((e.getX() - 10) / 20);
                        player.setPosY((e.getY() - 50) / 20);
                        maze.repaint();
                        maze.fixAllReachable();
                        maze.highlightTiles();
                        if(player.getPosY() == 29 && player.getPosX() == 29) maze.reset(true);
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

        maze.getF1().addMouseMotionListener(new MouseMotionListener() {
            int prevPosX = 0;
            int prevPosY = 0;

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(e.getX() <= 620 && e.getY() <= 660){
                    if(DFS.getMaze()[(e.getY()-50)/20][(e.getX()-10)/20].isReachable()){
                        DFS.getMaze()[(e.getY()-50)/20][(e.getX()-10)/20].setReachableGreen(true);

                        if (DFS.getMaze()[((e.getY()-50)/20)][(e.getX()-10)/20] != DFS.getMaze()[prevPosY][prevPosX]){
                            DFS.getMaze()[prevPosY][prevPosX].setReachableGreen(false);
                        }
                        prevPosX = ((e.getX()-10)/20);
                        prevPosY = ((e.getY()-50)/20);
                        maze.repaint();
                    }
                }
            }

        });
    }
}
