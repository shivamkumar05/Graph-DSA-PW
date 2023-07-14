import java.util.*;

class Graph {
    private int V; // number of vertices
    private LinkedList<Integer>[] adj; // adjacency list

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    boolean isReachable(int s, int d) {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];

        // Perform DFS from source vertex
        DFS(s, visited, parent);

        // If the destination vertex is visited, it is reachable
        if (visited[d]) {
            // Print the path
            List<Integer> path = new ArrayList<>();
            int curr = d;
            while (curr != -1) {
                path.add(curr);
                curr = parent[curr];
            }
            Collections.reverse(path);
            System.out.print("Path: ");
            for (int i : path) {
                System.out.print(i + " ");
            }
            System.out.println();
            return true;
        }
        return false;
    }

    // Recursive DFS
    private void DFS(int v, boolean[] visited, int[] parent) {
        visited[v] = true;
        for (int n : adj[v]) {
            if (!visited[n]) {
                parent[n] = v;
                DFS(n, visited, parent);
            }
        }
    }
}

public class Q3 {
    public static void main(String args[]) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);

        int source = 0;
        int destination = 7;
        if (graph.isReachable(source, destination)) {
            System.out.println("Destination vertex is reachable from source vertex.");
        } else {
            System.out.println("Destination vertex is not reachable from source vertex.");
        }
    }
}
