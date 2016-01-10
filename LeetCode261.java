/*
261. Graph Valid Tree My Submissions Question
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Show Company Tags
Show Tags
Show Similar Problems

*/

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        return helper3(n, edges);
       // return helper2(n, edges);
       // return helper(n, edges);
       // return helper(n, edges);
    }
    
    private boolean helper3(int n, int [][]edges)
    {
        int [] id = new int[n];
        Arrays.fill(id, -1);
        int count = 1;
        
        for(int i = 0; i < edges.length; i++)
        {
            int root0 = find(id, edges[i][0]);
            int root1 = find(id, edges[i][1]);
            
            if(root0 == root1){
                return false;
            }
            id[root0] = root1;
            count++;
        }
        
        return count == n;
    }
    
    private int find(int [] id, int s)
    {
        if(id[s] == -1) return s;
        while(id[s] != s)
        {
            if(id[s] == -1)
            {
                return s;
            }
           // id[s] = id[id[s]];
            s = id[s];
        }
        return s;
    }
    
    private boolean helper2(int n, int [][]edges){
        
        if(edges == null) return false;
        if(edges.length == 0) return n == 1;
        
        UF uf = new UF(n);
        for(int i = 0; i < edges.length; i++ )
        {
            if(!uf.union(edges[i][0], edges[i][1])){
                return false;
            }
        }
        
        return uf.size() == n -1;
    }
    
    private final class UF
    {
        private int[]id;
        private int count;
        
        public UF(int n)
        {
            id = new int[n];
            for(int i = 0; i < n; i++){
                id[i] = i;
            }
            count = 0;
        }
        
        public int size()
        {
            return count;
        }
        
        public boolean union(int s, int w)
        {
            int s0 = find(s);
            int w0 = find(w);
            if(s0 == w0){
                return false;
            }else{
                id[s0] = w0;
                count++;
                return true;
            }
        }
        
        public int find(int s)
        {
            while(s != id[s])
            {
                id[s] = id[id[s]];
                s = id[s];
            }
            return s;
        }
    }
    private boolean helper0(int n, int [][] edges)
    {
        if(edges == null) return false;
        if(edges.length == 0) return n == 1;
        
        // using bfs to determine whether we have visit this node or not
        List<List<Integer>>adj = new ArrayList<List<Integer>>(n);
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < edges.length; i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[]visited = new boolean[n];
        q.add(0);
        visited[0] = true;
        int count = 1;
        
        while(!q.isEmpty()){
            int top = q.remove();
            
            for(Integer num : adj.get(top)){
                if(visited[num] && num != top){
                    return false;
                }else if(!visited[num]){
                    visited[num] = true;
                    q.add(num);
                    count++;
                }
            }
        }
        
        return count == n;
    }
    
    
    private int count = 0;
    private boolean hasCycle = false;
    private boolean helper(int n, int [][]edges)
    {
        if(edges == null ) return false;
        if(edges.length == 0) return n == 1;
        
        List<List<Integer>>adj = new ArrayList<List<Integer>>(n);
        for(int i = 0; i < n; i++){
            adj.add(new LinkedList<Integer>());
        }

        for(int i = 0; i < edges.length; i++){
                 adj.get(edges[i][0]).add(edges[i][1]);
                 adj.get(edges[i][1]).add(edges[i][0]);
        }
        
        boolean [] visited = new boolean[n];
        count = 1;
        dfs(adj, visited, 0, 0);
        
        if(hasCycle || count < n){
            return false;
        }
        return true;
    }
    
    private void dfs(List<List<Integer>> adj, boolean [] visited, int s, int w)
    {
        visited[s] = true;
      
        for(Integer num : adj.get(s))
        {
            if(hasCycle){
                return;
            }else if(!visited[num]){
               count++;
               dfs(adj, visited, num, s);
           }else if(num != w){
               hasCycle = true;
           }
        }
    }
    
}