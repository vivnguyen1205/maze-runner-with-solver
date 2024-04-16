package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    private int[][] nodes;


    // private String [][] nodesDirections;
    HashMap<String,String> nodesDirections;



    private int numCols;
    private int numRows;
    private int startNode;
    private int exitNode;
    public Graph(int numRows, int numColumns){
        // make a matrix of ints as big as the maze
        // where each int represents the node ID.
        nodes = new int[numRows*numColumns][numRows*numColumns];
        numCols = numColumns;
        this.numRows = numRows;
        startNode = -1; //set to -1 to indicate we haven't yet found the start or the end
        exitNode = -1;
        //nodesDirections = new String[numRows*numColumns][numRows*numColumns];
        nodesDirections = new HashMap();
    }
    //this is the method that adds a new node to the graph

    // to go from originNode to the distination node, distance (how many steps),
    // direction is steps (e.g. FFFRF)
    public void addNode(Node destinationNode, Node originNode, int distance, String directions){
        System.out.print("\nAdding node:");
        if (originNode == null){
            startNode = destinationNode.getID();
        } else {
            int start = originNode.getID();
            int end = destinationNode.getID();
            this.nodes[start][end] = distance+1;




            //this.nodesDirections[start][end] = directions;
            //the nodesDirection associates the key (start | end) with the directions
            String startEnd = start + "|" + end;
            nodesDirections.put(startEnd, directions);


            // debuggin
            System.out.println("ADDING DIRECTIONS = " + directions);
            //maybe + 2 or + 1
            System.out.println("" + start + " to " + end + " distance of " + distance);
        }

        //check if the destinationNode and originalNode is an exit
        if (originNode!=null && originNode.getType()==Node.EXIT){
            this.exitNode = originNode.getID();
        } else if (destinationNode.getType() == Node.EXIT){
            this.exitNode = destinationNode.getID();
        }

    }
    // for debugging purposes only: prints the graph
    public void printGraph(){
        Node.printAllNodes();
        System.out.println("====== Printing Graph (R=" + numRows + ", C=" + numCols + ")=====");
        System.out.print("  ");
        for (int col = 0; col < numCols; col++ ){
            System.out.print(col);
        }
        System.out.println("");

        for (int row = 0; row < numRows; row++){
            System.out.print(row + " ");
            for (int col=0; col < numCols; col++){
                System.out.print(this.nodes[row][col]);
            }
            System.out.println("");
        }
        System.out.println("===========");
        System.out.println("Start Node = " + startNode);
        System.out.println("Exit Node = " + exitNode);
        System.out.println("===========");
    }



    public List<Integer> dijkstra(int startNode, int endNode) {
        int n = nodes.length;
        int[] distances = new int[n];
        int[] predecessors = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[startNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.getDistance() - b.getDistance());
        ///pq.add(     new Node(startNode, 0));
        Node x = Node.getNodeByID(0);
        x.setDistance(0);
        pq.add (Node.getNodeByID(0));
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentNodeId = currentNode.getID();

            if (visited[currentNodeId]) continue;
            visited[currentNodeId] = true;

            for (int i = 0; i < n; i++) {
                if (nodes[currentNodeId][i] > 0 && !visited[i]) { // Check for a valid, non-visited edge
                    int newDist = distances[currentNodeId] + nodes[currentNodeId][i];
                    if (newDist < distances[i]) {
                        distances[i] = newDist;
                        predecessors[i] = currentNodeId;
                        Node y = Node.getNodeByID(i);
                        y.setDistance(newDist);
                        pq.add(y);
                    }
                }
            }
        }

        return construct(predecessors, startNode, endNode);
    }

    // Method to reconstruct the path from startNode to endNode
    private List<Integer> construct(int[] predecessors, int startNode, int endNode) {
        List<Integer> path = new ArrayList<>();
        for (int at = endNode; at != -1; at = predecessors[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        // If the path starts with the startNode, it's valid
        if (path.get(0) == startNode) {
            return path;
        }
        return Collections.emptyList(); // Return an empty list if no path exists
    }


    public String findDijkstraPath(){

        List<Integer> solution =  dijkstra(this.startNode, this.exitNode);
        // the solution is a list of integer (node ids) that inform how to get from start to end
        // for example  6, 4, 3, 9
        // means that we start at node id 6, then we go to 4, then 3, then 9 and 9 is the exit
        System.out.println("========= SHORTEST SOLUTION IS ============");
        int from = 0;
        int to = 0;
        String finalSolution = "";
        //go through the solution path
        for (int i=1;i<solution.size();i++){
            System.out.print(from + " --> " + solution.get(i) + ":  ");// debug
            String key = from + "|" + solution.get(i);  // they is the from | to

            System.out.println(this.nodesDirections.get(key)); // debug


            finalSolution = this.nodesDirections.get(key);  // the final iteration is the solution

            from = solution.get(i);

        }
        System.out.println("=================");

        // return the steps to get from start to end.
        return finalSolution;
    }


}
