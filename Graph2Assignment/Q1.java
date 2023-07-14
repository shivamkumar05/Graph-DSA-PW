import java.util.*;
 
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
 
class Graph
{

    List<List<Integer>> adjList = null;
 
    // Constructor
    Graph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>(n);
 
        for (int i = 0; i < n; i++) {
            adjList.add(i, new ArrayList<>());
        }
 
        // add edges to the undirected graph
        for (Edge edge: edges)
        {
            int src = edge.source;
            int dest = edge.dest;
 
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }
}
 
public class Q1
{
    // Function to perform DFS traversal on the graph on a graph
    public static boolean DFS(Graph graph, int v, boolean[] discovered, int parent)
    {
        // mark the current node as discovered
        discovered[v] = true;
 
        // do for every edge (v, w)
        for (int w: graph.adjList.get(v))
        {
            // if `w` is not discovered
            if (!discovered[w])
            {
                if (DFS(graph, w, discovered, v)) {
                    return true;
                }
            }
 
            else if (w != parent)
            {
                // we found a back-edge (cycle)
                return true;
            }
        }
 
        return false;
    }
 
    public static void main(String[] args)
    {
        // List of graph edges
        List<Edge> edges = Arrays.asList(
                        new Edge(0, 1), new Edge(0, 6), new Edge(0, 7),
                        new Edge(1, 2), new Edge(1, 5), new Edge(2, 3),
                        new Edge(2, 4), new Edge(7, 8), new Edge(7, 11),
                        new Edge(8, 9), new Edge(8, 10), new Edge(10, 11)
                    );
 
        int n = 12;
 
        // build a graph from the given edges
        Graph graph = new Graph(edges, n);
 
        boolean[] discovered = new boolean[n];
 
        // Perform DFS traversal from the first vertex
        if (DFS(graph, 0, discovered, -1)) {
            System.out.println("The graph contains a cycle");
        }
        else {
            System.out.println("The graph doesn't contain any cycle");
        }
    }
}