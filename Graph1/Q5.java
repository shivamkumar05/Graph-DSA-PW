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
 
class Graph
{
    List<List<Integer>> adjList;
 
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
 
public class Q5
{
    public static void DFS(Graph graph, int v, boolean[] discovered)
    {
        // mark the current node as discovered
        discovered[v] = true;
 
        // do for every edge (v, u)
        for (int u: graph.adjList.get(v))
        {
            // `u` is not discovered
            if (!discovered[u]) {
                DFS(graph, u, discovered);
            }
        }
    }
 
    public static int findRootVertex(Graph graph, int n)
    {
        boolean[] visited = new boolean[n];
 
        int v = 0;
        for (int i = 0; i < n; i++)
        {
            if (!visited[i])
            {
                DFS(graph, i, visited);
                v = i;
            }
        }
 
  
        Arrays.fill(visited, false);
 
        DFS(graph, v, visited);
 
        for (int i = 0; i < n; i++)
        {
            if (!visited[i]) {
                return -1;
            }
        }
 
        return v;
    }
 
    public static void main(String[] args)
    {
        List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(1, 2),
                    new Edge(2, 3), new Edge(3, 0), new Edge(4, 3),
                    new Edge(4, 5), new Edge(5, 0));
 
        int n = 6;
 
        Graph graph = new Graph(edges, n);
 
        // find the root vertex in the graph
        int root = findRootVertex(graph, n);
 
        if (root != -1) {
            System.out.println("The root vertex is " + root);
        }
        else {
            System.out.println("The root vertex does not exist");
        }
    }
}