package ca.mcmaster.se2aa4.mazerunner;

public class Coordinate {
    private int row;
    private int col;

    Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;

    }
    public int getCol(){
        return col;

    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public void goNorth(){
        this.row--;
    }
    public void goSouth(){
        this.row++;
    }
    public void goEast(){
        this.col++;
    }
    public void goWest(){
        this.col--;
    }
}
