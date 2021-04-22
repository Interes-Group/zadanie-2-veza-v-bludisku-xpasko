package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Game{

    Player player = new Player();

    public void play(){

       Player player = new Player();
       CreateButtons button = new CreateButtons();
       DepthFirstSearch MazeMap = new DepthFirstSearch(player);
       MazeMap.generatemaze();
       CreateMaze CreateMaze = new CreateMaze(MazeMap,player,button);
       CreateMaze.MazeMapMaker();


    }

}

