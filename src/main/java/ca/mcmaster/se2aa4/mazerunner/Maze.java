package ca.mcmaster.se2aa4.mazerunner;
import ca.mcmaster.se2aa4.mazerunner.Coordinate;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    // turn into 2D array??
    // identify the dimensions of the maze
    // identify the entrance and the exit of the maze
    public static final int WALL=1;
    public static final int PASS =2;
    public static final int EXIT =3;
    public static final int ENTRANCE =4;
    public static final int INVALID =-1;

    private int numRows;
    private int numCols;
    private Coordinate start;





    private ArrayList<String> maze;


    private void loadMaze(String filename)throws FileNotFoundException, IOException {

        this.maze = new ArrayList<>();
        int maxCols = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if(line.length() != 0){
                maxCols = line.length();
            }else{
                line = " ".repeat(maxCols);
            }
            maze.add(line);
        }
        this.numCols = maxCols;
        this.numRows = this.maze.size();
        System.out.println(this.numCols);
        System.out.println(this.numRows);
    }


    Maze(String filename)throws FileNotFoundException, IOException{
        loadMaze(filename);
        findStart();




    }
    private void findStart(){
        for(int row = 0; row< this.numRows; row++){
            Coordinate c = new Coordinate(row, 0);
            if(getLocationType(c)== ENTRANCE){
                this.start = c;
                return;
            }

        }
        System.out.println("this is the start");
        System.out.println(this.start.getRow());
        System.out.println(this.start.getCol());

    }
    public Coordinate getStart(){
        return this.start;
    }
    public int getLocationType(Coordinate location){
        System.out.println("getting location type ");
        System.out.println(location.getRow());
        System.out.println(location.getCol());
        if(location.getRow()<0|| location.getRow() >= this.numRows){
            return INVALID;
        }
        if(location.getCol()<0|| location.getCol() >= this.numCols){
            return INVALID;
        }
        if(this.maze.get(location.getRow()).charAt(location.getCol()) == '#'){
            return WALL;
        }

        if(this.maze.get(location.getRow()).charAt(location.getCol()) == ' ' && location.getCol()== this.numCols-1 ){
            return EXIT;
        }
        if(this.maze.get(location.getRow()).charAt(location.getCol()) == ' ' && location.getCol()== 0 ){
            return ENTRANCE;
        }
        if(this.maze.get(location.getRow()).charAt(location.getCol()) == ' '){
            return PASS;
        }
        return INVALID;
    }


    // Other methods for maze manipulation (if needed)
}

