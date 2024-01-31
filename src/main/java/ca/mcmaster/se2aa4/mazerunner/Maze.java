package ca.mcmaster.se2aa4.mazerunner;
import ca.mcmaster.se2aa4.mazerunner.Coordinate;
// MAZE is class to process the maze, find the entrance/exit and wall/passageway

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


    private void loadMaze(String filename)throws FileNotFoundException, IOException { // method to turn maze file into array list

        this.maze = new ArrayList<>(); //
        int maxCols = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if(line.length() != 0){
                maxCols = line.length();
            }else{
                line = " ".repeat(maxCols); // adding spaces to empty lines
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
    private void findStart(){ // finding entrance of maze
        for(int row = 0; row< this.numRows; row++){
            Coordinate c = new Coordinate(row, 0);
            if(getLocationType(c)== ENTRANCE){ //if the location type is type ENTRANCE, it has a '_'
                this.start = c;
                System.out.println("this is the start");
                System.out.println(this.start.getRow());
                System.out.println(this.start.getCol());
                return;
            }

        }


    }
    public Coordinate getStart(){
        return this.start;
    }
    public int getLocationType(Coordinate location){
        System.out.println("getting location type "); // assigning values to each coordinate on the maze depending on what the character at that location si
        System.out.println(location.getRow());
        System.out.println(location.getCol());
        if(location.getRow()<0|| location.getRow() >= this.numRows){ // if rownumber is greater than num rows or coordinate is less than 0, invalid
            return INVALID;
        }
        if(location.getCol()<0|| location.getCol() >= this.numCols){ // if colnumber is greater than num cols or coordinate is less than 0, invalid
            return INVALID;
        }
        if(this.maze.get(location.getRow()).charAt(location.getCol()) == '#'){ // if coordinate contains #, it is wall
            return WALL;
        }

        if(this.maze.get(location.getRow()).charAt(location.getCol()) == ' ' && location.getCol()== this.numCols-1 ){ // if empty space and the column number is maxcolumun number, it is entrance
            return EXIT;
        }
        if(this.maze.get(location.getRow()).charAt(location.getCol()) == ' ' && location.getCol()== 0 ){ // if location has empty space wat column 0, it is entrance
            return ENTRANCE;
        }
        if(this.maze.get(location.getRow()).charAt(location.getCol()) == ' '){ // if location contains "_" empty space, it is passageway
            return PASS;
        }
        return INVALID; // otherwise, coordinate is invalid
    }


    // Other methods for maze manipulation (if needed)
}

