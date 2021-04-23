package sk.stuba.fei.uim.oop;

public class Cell {

    public int x;
    public int y;
    private boolean isWall;
    private boolean isFinish;
    private boolean isReachable;
    private boolean isReachableGreen;

    public boolean isFinish() {
        return isFinish;
    }

    public boolean isReachableGreen() {
        return isReachableGreen;
    }

    public void setReachableGreen(boolean reachableGreen) {
        isReachableGreen = reachableGreen;
    }



    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.isWall = true;
        this.isFinish = false;
        this.isReachable = false;
        this.isReachableGreen = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setBeginning() {
    }

    public void setWall(boolean wall) {
        this.isWall = wall;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }


}
