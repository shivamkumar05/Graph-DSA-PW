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
 
// A BFS Node
class Node
{

    int vertex, depth;
 
    public Node(int vertex, int depth)
    {
        this.vertex = vertex;
        this.depth = depth;
    }
}
 
// A class to represent a graph object
class Graph
{
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
 
public class Q4
{
    public static int findTotalPaths(Graph graph, int src, int dest, int m)
    {
        // create a queue for doing BFS
        Queue<Node> q = new ArrayDeque<>();
 
        // enqueue source vertex
        q.add(new Node(src, 0));
 
        int count = 0;
 
        // loop till queue is empty
        while (!q.isEmpty())
        {
            // dequeue front node
            Node node = q.poll();
 
            int v = node.vertex;
            int depth = node.depth;
 
            if (v == dest && depth == m) {
                count++;
            }

            if (depth > m) {
                break;
            }
 
            for (int u: graph.adjList.get(v))
            {
                q.add(new Node(u, depth + 1));
            }
        }
 
        return count;
    }
 
    public static void main(String[] args)
    {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 6), new Edge(0, 1), new Edge(1, 6), new Edge(1, 5),
                new Edge(1, 2), new Edge(2, 3), new Edge(3, 4), new Edge(5, 2),
                new Edge(5, 3), new Edge(5, 4), new Edge(6, 5), new Edge(7, 6),
                new Edge(7, 1));
 
        int n = 8;
 
        // construct graph
        Graph graph = new Graph(edges, n);
 
        int src = 0, dest = 3, m = 4;
 
        System.out.println(findTotalPaths(graph, src, dest, m));
    }
}