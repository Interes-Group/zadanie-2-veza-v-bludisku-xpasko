package sk.stuba.fei.uim.oop;

public class Game{


    public void play(){

       Player player = new Player();
       panel p = new panel();
       CreateButtons Maze = new CreateButtons();
       DepthFirstSearch MazeMap = new DepthFirstSearch(player);
       MazeMap.generatemaze();
       CreateMaze CreateMaze = new CreateMaze(MazeMap,player,p);
       CreateMaze.MazeMapMaker();



    }
}
