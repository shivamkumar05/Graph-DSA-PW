import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
// A class to store a graph edge
class Edge
{
    int source, dest;
 
    public Edge(int source, int dest)
    {
        this.source = source;
        this.dest = dest;
    }
}
 
// A class to represent a graph object
class Graph
{
    // A list of lists to represent an adjacency list
    List<List<Integer>> adjList = null;
 
    // Constructor
    Graph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>();
 
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
 
        // add edges to the directed graph
        for (Edge edge: edges) {
            adjList.get(edge.source).add(edge.dest);
        }
    }
}
 
public class Q2
{

    private static int DFS(Graph graph, int v, boolean[] discovered,
                           int[] departure, int time)
    {
        // mark the current node as discovered
        discovered[v] = true;
 
        // do for every edge (v, u)
        for (int u: graph.adjList.get(v))
        {
            // if `u` is not yet discovered
            if (!discovered[u]) {
                time = DFS(graph, u, discovered, departure, time);
            }
        }

        departure[v] = time++;
 
        return time;
    }
 
    // Returns true if given directed graph is DAG
    public static boolean isDAG(Graph graph, int n)
    {
        // keep track of whether a vertex is discovered or not
        boolean[] discovered = new boolean[n];
 
        // keep track of the departure time of a vertex in DFS
        int[] departure = new int[n];
 
        int time = 0;
 

        for (int i = 0; i < n; i++)
        {
            if (!discovered[i]) {
                time = DFS(graph, i, discovered, departure, time);
            }
        }
 
        // check if the given directed graph is DAG or not
        for (int u = 0; u < n; u++)
        {
            // check if (u, v) forms a back-edge.
            for (int v: graph.adjList.get(u))
            {

                if (departure[u] <= departure[v]) {
                    return false;
                }
            }
        }
 
        // no back edges
        return true;
    }
 
    public static void main(String[] args)
    {
        // List of graph edges as per the above diagram
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 3), new Edge(1, 2),
                new Edge(1, 3), new Edge(3, 2), new Edge(3, 4),
                new Edge(3, 0), new Edge(5, 6), new Edge(6, 3)
        );
 
        int n = 7;
 
        Graph graph = new Graph(edges, n);
 
        if (isDAG(graph, n)) {
            System.out.println("The graph is a DAG");
        }
        else {
            System.out.println("The graph is not a DAG");
        }
    }
}