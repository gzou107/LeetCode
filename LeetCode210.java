/*
210. Course Schedule II My Submissions Question
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return help(numCourses, prerequisites);
    }
    
    private int[] help(int numCourses, int[][] requires)
    {
        if(numCourses <= 0) return new int[0];
        
        List<List<Integer>> adj = new ArrayList<List<Integer>>(numCourses);
        for(int i = 0; i < numCourses; i++){
            adj.add(new LinkedList<>());
        }
        
        for(int i = 0; i < requires.length; i++)
        {
            int[] pair = requires[i];
            adj.get(pair[0]).add(pair[1]);
        }
        
        cycleFinder finder = new cycleFinder(numCourses, adj);
        
        return finder.order();
    }
    
    private class cycleFinder
    {
        private boolean hasCycle;
        private boolean [] onstack;
        private boolean [] visited;
        private List<List<Integer>> adj;
        private Stack<Integer> reverseOrder;
        private int V;
        
        public cycleFinder(int n, List<List<Integer>> adj)
        {
            V = n;
            onstack = new boolean[n];
            visited = new boolean[n];
            reverseOrder = new Stack<Integer>();
            
            for(int i = 0; i < n; i++)
            {
                if(!visited[i]){
                    dfs(i, adj);
               }
            }
        }
        
        private void dfs(int s, List<List<Integer>> adj)
        {
            visited[s] = true;
            onstack[s] = true;
            
            for(Integer num : adj.get(s))
            {
                if(hasCycle){
                    return;
                }else if(!visited[num])
                {
                    dfs(num, adj);
                }else if(onstack[num]){
                    hasCycle = true;
                }
            }
            reverseOrder.add(s);
            onstack[s] = false;
        }
        
        public int[] order()
        {
            if(hasCycle) return new int[0];
            
            int [] res = new int[V];
            int i = V-1;
            while(!reverseOrder.isEmpty()){
                res[i--] = reverseOrder.pop();
            }
            return res;
        }
    }
}