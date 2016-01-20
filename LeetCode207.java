/*
207. Course Schedule My Submissions Question
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return help(numCourses, prerequisites);
    }
    
    private boolean help(int numCourses, int[][] requires)
    {
        if(numCourses <= 1) return true;
        
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
        
        return finder.order().size() > 1;
    }
    
    private class cycleFinder
    {
        private boolean hasCycle;
        private boolean [] onstack;
        private boolean [] visited;
        private List<List<Integer>> adj;
        private Stack<Integer> reverseOrder;
        
        public cycleFinder(int n, List<List<Integer>> adj)
        {
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
        public List<Integer> order()
        {
            List<Integer> res = new LinkedList<>();
            if(hasCycle) return res;
            
            while(!reverseOrder.isEmpty()){
                res.add(0, reverseOrder.pop());
            }
            return res;
        }
    }
}