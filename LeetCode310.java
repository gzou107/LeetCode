/*
Minimum Height Trees My Submissions Question
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/


public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
       return helper0(n, edges);
    }
    
    private List<Integer> helper0(int n, int [][] edges){
        
        List<Integer> ans = new ArrayList<Integer>();
        if( n <= 1){
            return Collections.singletonList(0);
        }
        
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<Integer>());
        }
        
        int[] degree = new int[n];
        for(int i = 0; i < edges.length; i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            degree[edges[i][0]]++;
            
            adj.get(edges[i][1]).add(edges[i][0]);
            degree[edges[i][1]]++;
        }
        
        // find all leaves
        List<Integer> leaves = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            if(degree[i] == 1){
                leaves.add(i);
            }
        }
        
        // iterate remove all leaves until 2 or 1 leaf left
        while(n > 2){
             n -= leaves.size();
             
             List<Integer> newLeaves = new ArrayList<Integer>();
             for(Integer i : leaves){
                 for(Integer j : adj.get(i)){
                     degree[j]--;
                     
                     if(degree[j] == 1){
                         newLeaves.add(j);
                     }
                 }
             }
             
             leaves = newLeaves;
        }
        
        return leaves;
    }
    
    // running time complexity O(v (V + E) )
    private List<Integer> helper1(int n, int[][] edges)
    {
        // we first build the graph
        Graph g = new Solution.Graph(n, edges);
        
        // now we calculate for each node, the max distance from it to all other nodes
        TreeMap<Integer, List<Integer>> distances = new TreeMap<Integer, List<Integer>>();
        for(int i = 0; i < n; i++){
            BFS b = new Solution.BFS(g, i);
            if(!distances.containsKey(b.maxDistance()) ){
                distances.put(b.maxDistance(), new ArrayList<Integer>());
            }
            
            distances.get(b.maxDistance()).add(i);
        }
        
        // now we find the sets of nodes ( at most 2), which has the smallest of max distance
        return distances.get(distances.firstKey());
    }
    public static class Graph
    {
    public final int V;
    public int E;
    private List<List<Integer>>adj;
    
    public Graph(int v, int[][] edges){
       this.V = v;
       this.E = 0;
       this.adj = new ArrayList<List<Integer>>();
       
       for(int i = 0; i < v; i++)
       {
           adj.add(new ArrayList<Integer>());
       }
       
       for(int i = 0; i < edges.length; i++){
           addEdge(edges[i]);
       }
    }
    
    public Iterable<Integer> adj(int i){
        return adj.get(i);
    }
    
    public void addEdge(int[]edge){
       adj.get(edge[0]).add(edge[1]);
       adj.get(edge[1]).add(edge[0]);
       E++;
    }
  }
  
  public static class BFS{
    
    private boolean [] visited;
    private int [] distance;
    
    public BFS(Graph g, int s){
        distance = new int[g.V];
        visited = new boolean[g.V];
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(s);
        distance[s] = 0;
        visited[s] = true; // whenever added into q, we need mark it as visited
       
       while(!q.isEmpty()){
           Integer top = q.poll();
           
           for(Integer i : g.adj(top)){
               if(!visited[i]){
                   visited[i] = true;
                   q.add(i);
                   distance[i] = distance[top] + 1;
               }
           }
       }
       
    }
    
    public int maxDistance(){
        int max = distance[0];
        for(int i = 1; i < distance.length; i++){
            if(max < distance[i]){
                max = distance[i];
            }
        }
        return max;
    }
    
  }
}
