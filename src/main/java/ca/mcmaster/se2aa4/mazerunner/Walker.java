// Walker Class - class

package ca.mcmaster.se2aa4.mazerunner;
// class to
import ca.mcmaster.se2aa4.mazerunner.Coordinate;
public class Walker {
    private Maze maze;
    private Coordinate location;



    Walker(Maze maze){

    }
    private String compressPath(String path){ ///takes canonical form to factored form
        String factoredPath = "";
        for(int i =0; i<path.length();i++){
            if(path.charAt(i)== Character.isLetter()){
                factoredPath+=path.charAt(i);
            } else if (// the character is a number, repreat the letter x times and add to factored solution)

        }
    }
    private String uncompressPath(String path){ // takes input of factored form and turns into canonical form

        String newPath = "";
        int repeater = 1;
        String r = "";
        for(int i =0;i <path.length();i++) {
            if (Character.isLetter(path.charAt(i))) {

            }
        }
        return newPath;
    }
    public int checkPath(String path, boolean fullSol) { //checks if user input works
        path=uncompressPath(path);

    }

    public String findPath(){
        String solution = "";
        //start at entrance given by the findENtrance method, check next empty space and that there is a wall to the right, contunue through maze and check that there is a wall to the right for every step

        return solution;
    }

}