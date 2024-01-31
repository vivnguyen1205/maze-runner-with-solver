package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Coordinate;

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


   Walker(Maze maze){
       this.maze = maze;
       this.location = maze.getStart();
       this.direction = EAST;

   }
   private String convertPath(String path){
    String newPath = "";
    int repeater = 1;
    String r = "";
    for(int i =0;i <path.length();i++){
        if(Character.isLetter(path.charAt(i))){
            String add=""+path.charAt(i);
            if(r.length()>0){
                repeater= Integer.parseInt(r);
                System.out.println("repeater ="+repeater);
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
   public int checkPath(String path, boolean fullSol) {
       path=convertPath(path);
       for (int i = 0; i < path.length(); i++) {
           step(path.charAt(i));
           int r = maze.getLocationType(this.location);
           if (r == Maze.WALL) {
               System.out.println("hit the wall");
               return INVALID;
           } else if (r == Maze.INVALID) {
               System.out.println("hit invalid");
               return INVALID;
           } else if (r == Maze.EXIT) {
               System.out.println("hit exit");
               if(i==path.length()-1){
                   return EXIT;
               }
               else{
                   System.out.println("Reached exit but there are more instructions");
                   return INVALID;
               }
           }
       }
       int r = maze.getLocationType(this.location);
       if(!fullSol && r==Maze.PASS && hasRightWall()){
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
   public String findPath(){
       // F, LF, RF, LLF
       String solution = "";
       int success = 0;
       while(true){
           System.out.println("======== success at location"+ location.getRow()+","+location.getCol());
           Coordinate currentLocation=location.copy();
           // ======================================
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
           }
           // ======================================
            // ======================================

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
           }
           // ======================================
           // ======================================

           success = checkPath("LLF", false);
           if(success == Walker.EXIT ){
               solution+="LLF";
               break;
           }
           else if(success== Walker.VALID){
               solution+="LLF";
               continue;
           }
           else{
               location=currentLocation.copy();
           }
           // ======================================
       }

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
       System.out.println("stepping instruction = " + instruction);
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
            System.out.println("move to location ="+this.location.getRow() + "," + this.location.getCol());
       }

}

