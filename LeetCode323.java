/*
323. Number of Connected Components in an Undirected Graph My Submissions Question
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        return helper(n, edges);
    }
    
    private int helper(int n, int [][]edges){
        List<List<Integer>> adjs = new ArrayList<List<Integer>>();
        for(int i = 0; i < n; i++){
            List<Integer> adj = new ArrayList<Integer>();
            adjs.add(adj);
        }
        
        for(int i = 0; i < edges.length; i++){
            int from= edges[i][0];
            int to = edges[i][1];
            adjs.get(from).add(to);
            adjs.get(to).add(from);
        }
        
        boolean [] visited = new boolean[n];
        int count = 0;
        Queue<Integer> q = new ArrayDeque<Integer>();
        for(int i = 0; i < n; i++)
        {
            if(!visited[i])
            {
                q.add(i);
                
                while(!q.isEmpty())
                {
                    int top = q.remove();
                    visited[top] = true;
                    
                    for(Integer neigh : adjs.get(top))
                    {
                        if(!visited[neigh])
                        {
                            q.add(neigh);
                        }
                    }
                }
               count++;
            }
           
        }
        
        return count;
    }
}