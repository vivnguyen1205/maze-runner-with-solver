package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
/// problem with historyListCopy.... i am not adding the right way i think.
public class Walker {
    private Maze maze;
    private Coordinate location;
    private int direction;
    private static final int NORTH =0;
    private static final int EAST =1;
    private static final int SOUTH =2;
    private static final int WEST =3;
    public static final int VALID = 1;
    public static final int INVALID = 2;
    public static final int EXIT = 3;
    private Graph g;

    Walker(Maze maze){
        this.maze = maze;
        this.location = maze.getStart();
        this.direction = EAST;

    }
    private String compressPath(String path){ // method to factorize path
        // FFRLLFFFRRF
        // 2FR2L3F2RF
        if(path == null|| path.length()==0){
            return path;
        }
        path+=" ";
        int count = 1;
        String solution = "";
        for(int i=1;i<path.length();i++){
            if(path.charAt(i)== path.charAt(i-1)){
                count++;
            }
            else{
                if(count == 1){
                    solution+=(""+path.charAt(i-1));
                }
                else{
                    solution+=(""+count)+(""+path.charAt(i-1));
                    count = 1;
                }
            }
        }
        return solution;
    }
    private String uncompressPath(String path){ // method to unfactorize path
        String newPath = ""; // initializing empty string to put in unfactorized path in
        int repeater = 1;
        String r = "";
        for(int i =0;i <path.length();i++){
            if(Character.isLetter(path.charAt(i))){
                String add=""+path.charAt(i);
                if(r.length()>0){
                    repeater= Integer.parseInt(r);
                }

                newPath+=add.repeat(repeater);
                repeater=1;
                r = "";
            }
            else {
                r+=path.charAt(i);
            }
        }
        return newPath;

    }
    public int checkPath(String path, boolean fullSol) { // method to check if user input path is correct
        path=uncompressPath(path);
        for (int i = 0; i < path.length(); i++) {
            step(path.charAt(i));
            int r = maze.getLocationType(this.location);
            if (r == Maze.WALL) {// if reaches wall, path is invalid
                return INVALID;
            } else if (r == Maze.INVALID) { // if goes out of bounds, its invalid
                return INVALID;
            } else if (r == Maze.EXIT) {
                if(i==path.length()-1){
                    return EXIT;
                }
                else{
                    return INVALID;
                }
            }

        }
        int r = maze.getLocationType(this.location);
        //if(!fullSol && r==Maze.PASS && hasRightWall()){
        if(r==Maze.PASS){ // was above one before april 9.
            return VALID;
        }
        else{
            return INVALID;
        }
    }
    public int tryStep(char instruction){
        step(instruction);
        if(maze.getLocationType(this.location)==Maze.EXIT){
            return Walker.EXIT;
        }
        else if(maze.getLocationType(this.location)==Maze.PASS && hasRightWall()){
            return Walker.VALID;
        }
        else{
            return Walker.INVALID;
        }
    }



    public String findRightHandPath(){
        // F, LF, RF, LLF
        String solution = "";
        int success = 0;
        while(true){
            Coordinate currentLocation=location.copy();
            int currentDirection = this.direction;

            success = tryStep('F');
            if(success == Walker.EXIT ){
                solution+="F";
                break;
            }
            else if(success== Walker.VALID){
                solution+="F";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            // 1 ======================================
            success = checkPath("FRF", false);
            if(success == Walker.EXIT ){
                solution+="FRF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="FRF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            // ======================================
            success = checkPath("FLF", false);
            if(success == Walker.EXIT ){
                solution+="FLF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="FLF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            // ======================================
            success = checkPath("RF", false);
            if(success == Walker.EXIT ){
                solution+="RF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="RF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }

            success = checkPath("LF", false);
            if(success == Walker.EXIT ){
                solution+="LF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="LF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            success = checkPath("RRF", false);
            if(success == Walker.EXIT ){
                solution+="RRF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="RRF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            // ======================================
            success = checkPath("LRF", false);
            if(success == Walker.EXIT ){
                solution+="LRF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="LRF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            // ======================================
            success = checkPath("RLF", false);
            if(success == Walker.EXIT ){
                solution+="RLF";
                break;
            }
            else if(success== Walker.VALID){
                solution+="RLF";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
            // ======================================
            success = checkPath("LFR", false);
            if(success == Walker.EXIT ){
                solution+="LFR";
                break;
            }
            else if(success== Walker.VALID){
                solution+="LFR";
                continue;
            }
            else{
                location=currentLocation.copy();
                this.direction = currentDirection;
            }
        }
        solution=compressPath(solution);
        return solution;
    }
    private boolean hasRightWall(){
        if(this.direction==NORTH){
            Coordinate c = new Coordinate(this.location.getRow(),this.location.getCol()+1);
            if(maze.getLocationType(c)== Maze.WALL){
                return true;
            }
        }
        if(this.direction==EAST){
            Coordinate c = new Coordinate(this.location.getRow()+1,this.location.getCol());
            if(maze.getLocationType(c)== Maze.WALL){
                return true;
            }
        }
        if(this.direction==SOUTH){
            Coordinate c = new Coordinate(this.location.getRow(),this.location.getCol()-1);
            if(maze.getLocationType(c)== Maze.WALL){
                return true;
            }

        }
        if(this.direction==WEST){
            Coordinate c = new Coordinate(this.location.getRow()-1,this.location.getCol());
            if(maze.getLocationType(c)== Maze.WALL){
                return true;
            }
        }
        return false;
    }
    public void step(char instruction){
        //System.out.println("stepping instruction = " + instruction);
        if(instruction == 'L'){
            if(this.direction ==0){
                this.direction = 3;
            }
            else{
                this.direction--;
            }

        } else if (instruction == 'R') {
            if(this.direction ==3){
                this.direction = 0;
            }
            else{
                this.direction++;
            }

        } else if (instruction=='F') {
            if(this.direction==NORTH){
                this.location.goNorth();
            }
            else if(this.direction== EAST){
                this.location.goEast();
            }
            else if(this.direction== SOUTH){
                this.location.goSouth();
            }
            else if(this.direction== WEST){
                this.location.goWest();
            }

        }
    }
    public String findDijkstraPath(){
        //create a graph as big as the maze to mimic the maze
        g = new Graph(maze.getNumRows(),maze.getNumCols());
        //create a new node that is the START of the maze
        Node newNode = Node.createNode(this.location, Node.START);
        //add the node to the graph
        g.addNode(newNode, null, 0, ""); // go from null to newNode
        Coordinate currentLocation = this.location;
        String history = "";
        //keeps track of where the walker has walked
        ArrayList<Coordinate> historyList = new ArrayList<>();
        // this method will go across the whole maze, and find all the nodes
        findDijkstraPathHelper(this.location, this.direction, newNode, 0, history,currentLocation, historyList);
        //after this function executes the graph is complete
        //print it for debugging purposes
        g.printGraph();

        // if we found the end, then the getEndNode will not be null
        if (Node.getEndNode()== null){
            return "There is no solution.";
        } else {
            String path = g.findDijkstraPath(); // returns the shortest path using the dijksstra's algorithm
            return path;
        }
    }

    //creates a copy of every coordinate within the history
    private ArrayList<Coordinate> deepCopyHistory(ArrayList<Coordinate> list){
        ArrayList<Coordinate> newList = new ArrayList<Coordinate>();
        for (Coordinate c: list){
            newList.add(c.copy());
        }
        return newList;
    }

    private boolean isInHistoryList(Coordinate c, ArrayList<Coordinate> list){
        for (Coordinate c1: list){
            if (c1.getRow() == c.getRow() && c1.getCol() == c.getCol()){
                return true;
            }
        }
        return false;
    }

    //is this an intersection or a fork
    private boolean isIntersection(int sF, int sR, int sL){
        if (sF != INVALID && sR!=INVALID || sF != INVALID && sL!=INVALID ){
            return true;
        }
        else if (sR != INVALID && sL!=INVALID ){
            return true;
        } else {
            return false;
        }



    }

    // the location and the direction must be dynamic, so include these in the parameters here.

    // input parameters:
    // paramLocation: the location where the walker currently is
    // paramDirection: the direction where the walker is currently going (north, west, east...)
    // lastNode:  the node from which the walker came here
    // the rest of the parameters: are used for tracking where the walker has been
    // so that we never go back to where we were to avoid an infinite loop
    private String findDijkstraPathHelper(Coordinate paramLocation, int paramDirection, Node lastNode, int distance, String history, Coordinate tryLocation, ArrayList<Coordinate> historyList){

        // creates a deep copy of the history list, to
        // ensure that other recursive calls to this method do not break it.
        ArrayList<Coordinate> historyListCopy = deepCopyHistory(historyList);

        //check if the coordinate where we are we have already been to or not
        if(isInHistoryList(paramLocation, historyListCopy)){
            return ""; // we have already stepped here, so exit to ensure we don't go the same way agaon
        } else {
            //haven't been here yet, so add this spot to the history
            historyListCopy.add(paramLocation.copy());
        }
        String historyCopy = history;


        //should i be adding to the history now?
        //historyListCopy.add(paramLocation.copy());
        // 1 ======================================

        int oD = this.direction;
        Coordinate oL = this.location.copy();
        this.location = paramLocation.copy();
        this.direction = paramDirection;
        //try each path and then reset the location and direction to what it was before.
        int sF = checkPath("F", false);
        this.direction = paramDirection; this.location = paramLocation.copy();
        int sR = checkPath("RF", false); this.direction = paramDirection; this.location =paramLocation.copy();
        int sL = checkPath("LF", false); this.direction = paramDirection; this.location = paramLocation.copy();


        // we are at an intersection, if there is more than one direction we can go.
        boolean intersection = isIntersection(sF, sR, sL);

        /////////////
        // when intersectioning, make sure that the maze isn't ruined, and the direction is set to new one maybe
        ///////////////
        // only can go forward
        int newDirection = this.direction;

        ////////////////////////////////////////////////////////////////////////
        //if (sF != INVALID && sL == INVALID && sR == INVALID) {
        String hs = historyCopy;
        if (sF != INVALID) {  // we can go forward
            historyCopy += "F";
            Coordinate newCoordinate = this.location.copy();
            //check to ensure that the newCoordinate is not in the list of history and if it is don't go here

            switch (this.direction){  // don't change the direction and go where we were going
                case EAST: newCoordinate.goEast();break;
                case NORTH: newCoordinate.goNorth();break;
                case SOUTH: newCoordinate.goSouth();break;
                case WEST: newCoordinate.goWest();
            }
            if (true) { //!isInHistoryList(newCoordinate, historyListCopy)
                this.direction = oD;this.location = oL.copy();
                if (sF == Walker.EXIT){ // we reached an exit
                    Node newNode = Node.createNode(newCoordinate, Node.EXIT);
                    //add the exit to the graph
                    g.addNode(newNode, lastNode, distance, historyCopy);
                }
                else if (intersection){
                    Node newNode = Node.createNode(newCoordinate, Node.FORK);
                    g.addNode(newNode, lastNode, distance, historyCopy); //go from lastnode to newNode
                    findDijkstraPathHelper(newCoordinate, newDirection, newNode,0, historyCopy,newCoordinate, historyListCopy);
                }
                else {
                    //historyListCopy.add(tryLocation.copy());
                    findDijkstraPathHelper(newCoordinate, newDirection, lastNode,distance+1, historyCopy,newCoordinate, historyListCopy);
                }
            }
        }
        ////////////////////////////////////////////////////
        this.location = paramLocation.copy();//tryLocation.copy();   /////// NEW
        this.direction = paramDirection;
        // only can go right
        historyCopy = hs;
        ////////////////////////////////////////////////////////////////////////
        if (sR != INVALID) {
            historyCopy += "RF";
            Coordinate newCoordinate = this.location.copy();
            //check to ensure that the newCoordinate is not in the list of history and if it is don't go here

            switch (this.direction){
                case EAST: newCoordinate.goSouth();newDirection = SOUTH; break;
                case NORTH: newCoordinate.goEast();newDirection = EAST;break;
                case SOUTH: newCoordinate.goWest();newDirection  = WEST;break;
                case WEST: newCoordinate.goNorth();newDirection = NORTH;
            }
            if (true) {
                this.direction = oD;this.location = oL.copy();
                //if (sF == Walker.EXIT){
                if (sR == Walker.EXIT){
                    Node newNode = Node.createNode(newCoordinate, Node.EXIT);
                    g.addNode(newNode, lastNode, distance, historyCopy); //go from lastnode to newNode
                }
                else if (intersection){
                    Node newNode = Node.createNode(newCoordinate, Node.FORK);
                    g.addNode(newNode, lastNode, distance, historyCopy); //go from lastnode to newNode
                    findDijkstraPathHelper(newCoordinate, newDirection,newNode,0, historyCopy,newCoordinate, historyListCopy);
                }
                else {
                    // historyListCopy.add(tryLocation.copy());
                    findDijkstraPathHelper(newCoordinate, newDirection, lastNode,distance+1, historyCopy,newCoordinate, historyListCopy);
                }
            }
        }
        ////////////////////////////////////////////////////
        // only can go left
        this.location = paramLocation.copy();//tryLocation.copy();  //NEW
        this.direction = paramDirection;
        historyCopy = hs;
        ////////////////////////////////////////////////////////////////////////
        if (sL != INVALID) {
            historyCopy += "LF";
            Coordinate newCoordinate = this.location.copy();
            //check to ensure that the newCoordinate is not in the list of history and if it is don't go here

            switch (this.direction){
                case EAST: newCoordinate.goNorth();newDirection = NORTH;break;
                case NORTH: newCoordinate.goWest();newDirection = WEST;break;
                case SOUTH: newCoordinate.goEast();newDirection=EAST;break;
                case WEST: newCoordinate.goSouth();newDirection = SOUTH;
            }
            if (true) {
                this.direction = oD;this.location = oL.copy();
                //if (sF == Walker.EXIT){
                if (sL == Walker.EXIT){
                    Node newNode = Node.createNode(newCoordinate, Node.EXIT);
                    g.addNode(newNode, lastNode, distance, historyCopy); //go from lastnode to newNode
                }
                else if (intersection){
                    Node newNode = Node.createNode(newCoordinate, Node.FORK);
                    g.addNode(newNode, lastNode, distance, historyCopy); //go from lastnode to newNode
                    findDijkstraPathHelper(newCoordinate, newDirection, newNode,0, historyCopy,newCoordinate, historyListCopy);
                }
                else {
                    //historyListCopy.add(tryLocation.copy());
                    findDijkstraPathHelper(newCoordinate, newDirection, lastNode,distance+1, historyCopy,newCoordinate, historyListCopy);
                }
            }
        }
        ////////////////////////////////////////////////////



        this.location = oL;

        return "";

    }


}

