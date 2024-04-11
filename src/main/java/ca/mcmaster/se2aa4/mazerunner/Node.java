package ca.mcmaster.se2aa4.mazerunner;

public class Node {
    private static int counter = 0;
    private int column;
    private int row;
    private int id;
    public static Node createNode(int row, int column){
        Node n = new Node();
        n.row = row;
        n.column = column;
        n.id = counter;
        counter++;
        return n;

    }
    private Node(){

    }
}
