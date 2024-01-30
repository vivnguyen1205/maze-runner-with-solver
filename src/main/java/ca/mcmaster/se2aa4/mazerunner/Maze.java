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

    private void loadMaze(String filename)
    // take file maze and turn into 2d arraay
    int[][] arr = new Array; // initializing new array
    BufferedReader reader = new BufferedReader(new FileReader(filename)); // loading in file
        for(int i=0; i< numberofrows; i++){
        for(int j=0;j<numberofcolumns;j++){
            // implement logic to add each character from the maze file into the 2D array individually

        }
    }


    Maze(String filename)throws FileNotFoundException, IOException{
        loadMaze(filename);
        findStart();




    }
    private void findStart(){ // finding entrance of maze
        for(int row = 0; row< this.numRows; row++){
            if(maze.charat(i)== '_'){ //if the location type is type ENTRANCE, it has a '_'
                entrance = (numrows,numcols); //variable to hold entrance coordinate
                return;
            }

        }


    }
    private void findExit(){ // finding entrance of maze
        for(int i=0; i<num; row++){
            if(maze.charat(i)== '_'){ //if the location type is type ENTRANCE, it has a '_'
                entrance = (numrows,numcols); //variable to hold entrance coordinate
                return;
            }

        }
        public Coordinate getStart(){
            return this.start;
        }

        public int getLocationType(Coordinate location){
            for(int i=0; int i<numrows; i++){
                for(int j =0; int j<numcols;j++){ // assign value WALL to spots with #
                    if(maze.charat(i,j)== '#)
                    type = Wall;
                    else if(maze.charat(i,j))){ // assign value PASS to spots with a '_'
                        type = pass;
                    }
                    else{
                        type = invalid;
                    }
                }
            }
        }


    }

