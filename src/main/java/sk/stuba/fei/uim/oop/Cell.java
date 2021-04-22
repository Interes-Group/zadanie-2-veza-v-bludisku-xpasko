package sk.stuba.fei.uim.oop;

public class Cell {

    private int x;
    private int y;
    boolean LW;
    boolean RW;
    boolean UW;
    boolean DW;
    boolean visited;


    public Cell(int x,int y){
        this.x = x;
        this.y = y;
        this.LW = true;
        this.RW = true;
        this.UW = true;
        this.DW = true;
        this.visited = false;
    }
}
