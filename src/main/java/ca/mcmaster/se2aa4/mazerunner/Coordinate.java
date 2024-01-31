package ca.mcmaster.se2aa4.mazerunner;
// class to change the coordiate every time the walker makes a move, in every direction
public class Coordinate {
    private int row;
    private int col;

    Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }
    public Coordinate copy(){
        return new Coordinate(this.row, this.col);
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
    public void goNorth(){ // subtract from row number when moving north
        this.row--;
    }
    public void goSouth(){ // add to row number if going south
        this.row++;
    }
    public void goEast(){ //
        this.col++;
    }
    public void goWest(){
        this.col--;
    }
}
