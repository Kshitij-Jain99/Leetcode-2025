//2493. Divide Nodes Into the Maximum Number of Groups
// Approach B: Union-Find for component detection
// TC = O(V+E), SC = O(V+E)
/*
1.Construct the graph using adjacency lists.
2.Identify connected components using DFS.
  -> This avoids redundant work when processing nodes within the same component.
  -> first extracting components and then applying BFS only where needed.
3.For each component, use BFS to determine the maximum travel distance.
  -> BFS is used directly while computing levels, detecting conflicts early and skipping unnecessary checks.
4.Return the sum of maximum travel distances for each component.
5.The previous approach could lead to O(V(V + E)) due to multiple BFS calls.
  -> This approach splits the graph first (DFS) and only applies BFS once per component, avoiding redundant BFS calls.
6.Bipartiteness Check
   -> BFS only on components (O(V + E)): Reduces redundant checks
   -> Aprroach A: BFS on all nodes (O(V(V + E)))
7.Connected Components
  -> Explicitly identified using DFS: Avoids duplicate BFS calls
  -> Approach A: Implicitly identified using BFS: Redundant BFS calls
  */
import java.util.*;

public class Jan30_B {
    public int magnificentSets(int n, int[][] edges) {
        //Stroing the adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int node = 1; node <= n; node++) {
            graph.put(node, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        Map<Integer, List<Integer>> components = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int component = 1;
        for (int node = 1; node <= n; node++) {
            if(visited.contains(node)) continue;
            visited.add(node);
            components.put(component, new ArrayList<>());
            dfsComponents(component++, node, graph, components, visited);    
        }
        int[] componentsMaxTravel = new int[component];
        int finalRes = 0;
        for(int comp = 1; comp < component; comp++) {
            for (int compNode : components.get(comp)) {
                
                int compRes = bfs(compNode, graph);
                if(compRes == -1) return -1;
                componentsMaxTravel[comp] = Math.max(componentsMaxTravel[comp], compRes);
            }
            finalRes += componentsMaxTravel[comp];
        }

        return finalRes;
        
    }
    //O(V+E)
    private void dfsComponents(int component, int node, Map<Integer, List<Integer>> graph, Map<Integer, List<Integer>> components, Set<Integer> visited) {
        components.get(component).add(node);
        for (int neighbor : graph.get(node)) {
            // happens when there is more than one spanning tree. I.E you can arrive at a node in more than one way
            if(visited.contains(neighbor)) continue;
            visited.add(neighbor);
            dfsComponents(component, neighbor, graph, components, visited);
        }
    }
    //O(V+E)
    private int bfs(int node, Map<Integer, List<Integer>> graph) {
        int reach = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> curLevel = new HashSet<>();
        
        queue.offer(node);
        visited.add(node);
        
        while (!queue.isEmpty()) {
            int n = queue.size();
            Set<Integer> nextLevel = new HashSet<>();

            
            
            for (int i = 0; i < n; i++) {
                int curNode = queue.poll();

                for(int neighbor : graph.get(curNode)) {
                    if(curLevel.contains(neighbor)) return -1; // Colored with different color == not bipartite
                    if(visited.contains(neighbor)) continue;
                    nextLevel.add(neighbor);
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }

            curLevel = nextLevel;
            reach++;
        }
        
        return reach;
    }
}
