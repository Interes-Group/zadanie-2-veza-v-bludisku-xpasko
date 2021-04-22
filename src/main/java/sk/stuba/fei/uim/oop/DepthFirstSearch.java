package sk.stuba.fei.uim.oop;

import java.util.*;


public class DepthFirstSearch {




    private Player player;
    private Cell cell;
    private Cell[][] maze2 = new Cell[31][31];


    public Cell[][] getMaze() {
        return maze2;
    }


    public DepthFirstSearch(Player player) {
        this.player = player;
    }

    public Cell[][] generatemaze() {

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                  maze2[i][j] = new Cell(j,i);
            }
        }

        Random rand = new Random();

        int r = rand.nextInt(31);
        while (r % 2 == 0) {
            r = rand.nextInt(31);
        }

        int c = rand.nextInt(31);
        while (c % 2 == 0) {
            c = rand.nextInt(31);
        }


        maze2[1][1].setBeggining(true);
        player.setPosx(1);
        player.setPosy(1);

        recursion(r, c);

        return maze2;
    }

    // 0 - cesta
    // 1 - stena

    public void recursion(int r, int c) {
        Integer[] randDirs = generateRandomDirections();

        for (int i = 0; i < randDirs.length; i++) {
            switch (randDirs[i]) {
                case 1:
                    if (r - 2 <= 0) continue;
                    if (maze2[r - 2][c].iswall) {
                        maze2[r-2][c].setIswall(false);
                        maze2[r-1][c].setIswall(false);
                        recursion(r - 2,c);
                    }
                    break;
                case 2:
                    if(c + 2 >= 31 - 1) continue;
                    if(maze2[r][c+2].iswall){
                        maze2[r][c+2].setIswall(false);
                        maze2[r][c+1].setIswall(false);
                        recursion(r,c+2);
                    }
                    break;
                case 3:
                    if(r + 2 >= 31 - 1) continue;
                    if(maze2[r+2][c].iswall){
                        maze2[r+2][c].setIswall(false);
                        maze2[r+1][c].setIswall(false);
                        recursion(r + 2,c);
                    }
                    break;
                case 4:
                    if(c - 2 <= 0) continue;
                    if(maze2[r][c-2].iswall){
                        maze2[r][c-2].setIswall(false);
                        maze2[r][c-1].setIswall(false);
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