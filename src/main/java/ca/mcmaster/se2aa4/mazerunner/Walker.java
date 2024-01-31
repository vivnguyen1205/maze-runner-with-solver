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
        }
        else {
            r+=path.charAt(i);
        }
    }
    return newPath;
   }
   public boolean checkPath(String path) {
       path=convertPath(path);
       System.out.println(path);
       System.exit(0);
       for (int i = 0; i < path.length(); i++) {
           step(path.charAt(i));
           int r = maze.getLocationType(this.location);
           if (r == Maze.WALL) {
               System.out.println("hit the wall");
               return false;
           } else if (r == Maze.INVALID) {
               System.out.println("hit invalid");
               return false;
           } else if (r == Maze.EXIT) {
               System.out.println("hit exit");
               if(i==path.length()-1){
                   return true;
               }
               else{
                   System.out.println("Reached exit but there are more instructions");
                   return false;
               }
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

