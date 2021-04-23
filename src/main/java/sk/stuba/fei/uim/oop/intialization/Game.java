package sk.stuba.fei.uim.oop.intialization;

import sk.stuba.fei.uim.oop.CreateMaze;

public class Game{

    public Game() {
        play();
    }

    public void play(){

       Player player = new Player();
       DepthFirstSearch MazeMap = new DepthFirstSearch(player);
       MazeMap.generateMaze();
       CreateMaze CreateMaze = new CreateMaze(MazeMap,player);
       CreateMaze.mazeMapMaker();
    }
}

