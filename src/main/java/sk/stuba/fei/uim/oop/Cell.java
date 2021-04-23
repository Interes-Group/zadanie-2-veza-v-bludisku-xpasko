package sk.stuba.fei.uim.oop;

public class Cell {

    public int x;
    public int y;
    public boolean iswall;
    public boolean isBeggining;
    public boolean isFinish;
    public boolean isReachable;

    public boolean isReachablegreen() {
        return isReachablegreen;
    }

    public void setReachablegreen(boolean reachablegreen) {
        isReachablegreen = reachablegreen;
    }

    boolean isReachablegreen;

    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.iswall = true;
        this.isBeggining = false;
        this.isFinish = false;
        this.isReachable = false;
        this.isReachablegreen = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBeggining(boolean beggining) {
        isBeggining = beggining;
    }

    public void setIswall(boolean iswall) {
        this.iswall = iswall;
    }

    public boolean isIswall() {
        return iswall;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }


}
