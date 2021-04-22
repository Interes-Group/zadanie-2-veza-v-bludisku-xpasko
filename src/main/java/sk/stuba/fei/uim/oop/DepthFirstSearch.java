package sk.stuba.fei.uim.oop;

import java.util.*;


public class DepthFirstSearch {


    public DepthFirstSearch(Player player) {
        this.player = player;
    }

    private Player player;


    public int[][] getMaze() {
        return maze;
    }


    private int[][] maze = new int[13][13];

    public int[][] generatemaze() {

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                maze[i][j] = 1;
            }
        }

        Random rand = new Random();

        int r = rand.nextInt(13);
        while (r % 2 == 0) {
            r = rand.nextInt(13);
        }

        int c = rand.nextInt(13);
        while (c % 2 == 0) {
            c = rand.nextInt(13);
        }

        maze[r][c] = 0;
        player.setPosx(r);
        player.setPosy(c);

        recursion(r, c);

        return maze;
    }

    public void recursion(int r, int c) {
        Integer[] randDirs = generateRandomDirections();

        for (int i = 0; i < randDirs.length; i++) {
            switch (randDirs[i]) {
                case 1:
                    if (r - 2 <= 0) continue;
                    if (maze[r - 2][c] != 0) {
                        maze[r-2][c] = 0;
                        maze[r-1][c] = 0;
                        recursion(r - 2,c);
                    }
                    break;
                case 2:
                    if(c + 2 >= 13 - 1) continue;
                    if(maze[r][c+2] != 0){
                        maze[r][c+2] = 0;
                        maze[r][c+1] = 0;
                        recursion(r,c+2);
                    }
                    break;
                case 3:
                    if(r + 2 >= 13 - 1) continue;
                    if(maze[r+2][c] != 0){
                        maze[r+2][c] = 0;
                        maze[r+1][c] = 0;
                        recursion(r + 2,c);
                    }
                    break;
                case 4:
                    if(c - 2 <= 0) continue;
                    if(maze[r][c-2] != 0){
                        maze[r][c-2] = 0;
                        maze[r][c-1] = 0;
                        recursion(r,c-2);
                    }
                    break;
            }
        }
    }

    public Integer[] generateRandomDirections(){
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            randoms.add(i + 1);
        }
        Collections.shuffle(randoms);
        return randoms.toArray(new Integer[4]);
    }
}