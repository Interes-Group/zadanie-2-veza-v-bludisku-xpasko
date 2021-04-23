package sk.stuba.fei.uim.oop.intialization;

import sk.stuba.fei.uim.oop.CreateMaze;
import sk.stuba.fei.uim.oop.intialization.CreateButtons;
import sk.stuba.fei.uim.oop.intialization.DepthFirstSearch;
import sk.stuba.fei.uim.oop.intialization.Player;

public class Game{

    public void play(){

       Player player = new Player();
       CreateButtons button = new CreateButtons();
       DepthFirstSearch MazeMap = new DepthFirstSearch(player);
       MazeMap.generatemaze();
       CreateMaze CreateMaze = new CreateMaze(MazeMap,player,button);
       CreateMaze.mazeMapMaker();
    }
}

