import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
 
    List<Integer> in;
 
    Graph(int n)
    {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
 
        in = new ArrayList<>(Collections.nCopies(n, 0));
    }
 
    void addEdge(int u, int v)
    {
        adjList.get(u).add(v);
 
        in.set(v, in.get(v) + 1);
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
 
    public static Graph getUndirectedGraph(Graph graph, int n)
    {
        Graph g = new Graph(n);
        for (int u = 0; u < n; u++)
        {
            for (int v: graph.adjList.get(u)) {        // for every edge (u, v)
                g.addEdge(v, u);
                g.addEdge(u, v);
            }
        }
        return g;
    }
    public static boolean isConnected(Graph graph, int n)
    {
        boolean[] visited = new boolean[n];
 
        for (int i = 0; i < n; i++)
        {
            if (graph.adjList.get(i).size() > 0)
            {
                DFS(graph, i, visited);
                break;
            }
        }

        for (int i = 0; i < n; i++)
        {
            if (!visited[i] && graph.adjList.get(i).size() > 0) {
                return false;
            }
        }
 
        return true;
    }
 
    public static boolean hasEulerPath(Graph graph, int n)
    {
 
        boolean x = false, y = false;
        for (int i = 0; i < n; i++)
        {
            int out_degree = graph.adjList.get(i).size();
            int in_degree = graph.in.get(i);
 
            if (out_degree != in_degree)
            {
                if (!x && out_degree - in_degree == 1) {
                    x = true;
                }
                else if (!y && in_degree - out_degree == 1) {
                    y = true;
                }
                else {
                    return false;
                }
            }
        }

        return isConnected(getUndirectedGraph(graph, n), n);
    }
 
    public static void main(String[] args)
    {
        List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(1, 2),
                new Edge(2, 3), new Edge(3, 1), new Edge(1, 4), new Edge(4, 3),
                new Edge(3, 0), new Edge(0, 5), new Edge(5, 4)
        );
 
        int n = 6;
 
        Graph graph = new Graph(n);
 
        for (Edge edge: edges) {
            graph.addEdge(edge.source, edge.dest);
        }
 
        if (hasEulerPath(graph, n)) {
            System.out.println("The graph has an Eulerian path");
        }
        else {
            System.out.println("The Graph doesn't have an Eulerian path");
        }
    }
}