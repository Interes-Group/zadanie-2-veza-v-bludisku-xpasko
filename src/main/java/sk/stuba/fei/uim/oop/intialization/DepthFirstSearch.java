package sk.stuba.fei.uim.oop.intialization;

import sk.stuba.fei.uim.oop.Cell;

import java.util.*;


public class DepthFirstSearch {

    private final Player player;
    private final Cell[][] maze = new Cell[31][31];

    public DepthFirstSearch(Player player) {
        this.player = player;
    }

    public Cell[][] getMaze() {
        return maze;
    }


    public void generatemaze() {

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                  maze[i][j] = new Cell(j,i);
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

        maze[1][1].setBeggining(true);
        player.setPosx(1);
        player.setPosy(1);

        recursion(r, c);

    }

    public void recursion(int r, int c) {
        Integer[] randDirs = generateRandomDirections();

        for (Integer randDir : randDirs) {
            switch (randDir) {
                case 1:
                    if (r - 2 <= 0) continue;
                    if (maze[r - 2][c].iswall) {
                        maze[r - 2][c].setIswall(false);
                        maze[r - 1][c].setIswall(false);
                        recursion(r - 2, c);
                    }
                    break;
                case 2:
                    if (c + 2 >= 31 - 1) continue;
                    if (maze[r][c + 2].iswall) {
                        maze[r][c + 2].setIswall(false);
                        maze[r][c + 1].setIswall(false);
                        recursion(r, c + 2);
                    }
                    break;
                case 3:
                    if (r + 2 >= 31 - 1) continue;
                    if (maze[r + 2][c].iswall) {
                        maze[r + 2][c].setIswall(false);
                        maze[r + 1][c].setIswall(false);
                        recursion(r + 2, c);
                    }
                    break;
                case 4:
                    if (c - 2 <= 0) continue;
                    if (maze[r][c - 2].iswall) {
                        maze[r][c - 2].setIswall(false);
                        maze[r][c - 1].setIswall(false);
                        recursion(r, c - 2);
                    }
                    break;
            }
        }
    }

    public Integer[] generateRandomDirections(){
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            randoms.add(i + 1);
        }
        Collections.shuffle(randoms);
        return randoms.toArray(new Integer[4]);
    }

}