package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Node {
    private static int counter = 0;     //every node gets an ID (assigned by a counter)
    private int column;
    private int row;
    private int id; //the ID of this node
    //the type, can be either START, EXIT, or FORK
    private int type;
    public static int START = 1;
    public static int EXIT = 2;
    public static int FORK = 3; // decision point


    private int distance;   //used by dijkstra , to keep the distance to this node

    //after all the nodes are computed, this function is used to
    //find the EXIT (or end) node of the maze, if there is one.
    public static Node getEndNode(){
        for (Node n: created){
            if (n.getType()==EXIT){
                return n;
            }
        }
        return null;
    }

    public int getDistance(){
        return distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }

    // a list of all the nodes that have been created so far
    private static ArrayList<Node> created = new ArrayList<>();


    //for debugging purposes, just prints out all the details of the Node.
    public String toString(){
        String ret =  "NODE #" +  id + " (" + row  + " , "  + column + ") type: ";
        if ( type== START){
            ret += "START";
        } else if (type == EXIT){
            ret += "EXIT";
        } else if (type == FORK){
            ret += "FORK";
        } else {
            ret += "WALK";
        }
        return ret;
    }

    // for debugging purposes, prints all the nodes with their details
    public static void printAllNodes(){
        System.out.println("====== PRINTING ALL NODES =========");
        for (Node n: created){
            System.out.println(n);
        }

        System.out.println("==================NODES /");
    }


    public int getType(){
        return this.type;
    }

    //after all are found, returns the node at a particular row and column
    // and if there is no node there, returns null.
    private static Node find(int row, int column){
        for (Node c: created){
            if (c.row == row && c.column == column) {
                return c; //return the node if the row and columns match to the parameters
            }
        }
        return null;
    }


    //after everything is calculated, finds a node by its ID
    public static Node getNodeByID(int id){
        for (Node c: created){
            if (c.id == id) {
                return c;   //returns the node if its ID matches
            }
        }
        return null;
    }

    // creates a node at a particular row and column in the maze

    public static Node createNode(int row, int column,int type){
        Node foundExisting = find(row, column); //check to ensure there is no node at that location
        if (foundExisting!=null){
            return foundExisting;   //if there is a node there, don't create a new one, just return the node that's there
        } else {
            //there is no existing node, so create one
            Node n = new Node();
            n.row = row;
            n.column = column;
            n.id = counter;     //assign the ID as the current counter
            counter++;  // and increment the counter so every time you create another node
            // it will assign it the next Id (0, 1, 2, 3,....)
            n.type = type;
            n.distance = 0;
            created.add(n); //add it to our list of created nodes
            return n;   //return the node that we just worked with.
        }
    }

    public int getID(){
        return this.id;
    }
    //another way to create a node
    //instead of passing it row and column and type
    //we pass it the coordinate class which contains the row and column
    //for convenience only.
    public static Node createNode(Coordinate c, int type){
        return createNode(c.getRow(),c.getCol(), type);
    }
    // this is constructor that is made private
    // so that there is no way to make a Node by calling "new Node()"
    // the only way to make a node is to call the createNode method
    private Node(){

    }
}
