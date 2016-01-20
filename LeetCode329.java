/*
329. Longest Increasing Path in a Matrix My Submissions Question
Total Accepted: 182 Total Submissions: 692 Difficulty: Medium
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/
public class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        return help(matrix);//faster here
       // return help2(matrix);
    }
    
    private class Entry
    {
        public int idx;
        public int val;
        
        public Entry(int idx, int val)
        {
            this.idx = idx;
            this.val = val;
        }
    }
    
    private int help2(int[][]matrix)
    {
        if(matrix == null || matrix.length == 0) return 0;
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        PriorityQueue<Entry> pq = new PriorityQueue<Entry>(row*col, new Comparator<Entry>(){
            public int compare(Entry e1, Entry e2)
            {
                return e2.val - e1.val;
            }
        });
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                int idx = i * col + j;
                pq.offer(new Entry(idx, matrix[i][j]));
            }
        }
        
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        int max = 0;
        
        while(!pq.isEmpty())
        {
            Entry top = pq.poll();
            int x = top.idx/col;
            int y = top.idx%col;
            
            int up = 0;
            if(x-1>=0 && matrix[x][y] <matrix[x-1][y])
            {
                up = cache.get((x-1)*col + y);
            }
            
            int down = 0;
            if(x+1 < matrix.length && matrix[x][y] < matrix[x+1][y])
            {
                down = cache.get((x+1)*col + y);
            }
            
            int left = 0;
            if(y-1>=0 && matrix[x][y] < matrix[x][y-1])
            {
                left = cache.get(x*col + y-1);
            }
            
            int right = 0;
            if(y+1 < matrix[0].length && matrix[x][y] < matrix[x][y+1])
            {
                right = cache.get(x*col + y + 1);
            }
            
            int candidate = Math.max(Math.max(up,down), Math.max(left, right)) + 1;
            max = Math.max(max, candidate);
            cache.put(top.idx, candidate);
        }
        
        return max;
    }
    
    private int help(int[][] matrix)
    {
        if(matrix == null || matrix.length == 0) return 0;
        
        int row = matrix.length;
        int col = matrix[0].length;
        int max = 0;
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                int idx = i * col + j;
                dfs(matrix, i, j, m);
                max = Math.max(max, m.get(idx));
            }
        }
        
        return max;
    }
    
    private int dfs(int[][]matrix, int x, int y, Map<Integer, Integer> cache)
    {
        if(x <0 || x >= matrix.length || y <0 || y >= matrix[0].length) return 0;
        
        int idx = x * matrix[0].length + y;
        if(cache.containsKey(idx))
        {
            return cache.get(idx);
        }
        
        int up = 0;
        if(x-1>=0 && matrix[x][y] < matrix[x-1][y])
        {
            up = dfs(matrix, x-1, y, cache);
        }
        
        int down = 0;
        if(x+1 < matrix.length && matrix[x][y] < matrix[x+1][y])
        {
            down = dfs(matrix, x+1, y, cache);
        }
        
        int left = 0;
        if(y-1>=0 && matrix[x][y] < matrix[x][y-1])
        {
            left = dfs(matrix, x, y-1,cache);
        }
        
        int right = 0;
        if(y+1 < matrix[0].length && matrix[x][y] < matrix[x][y+1])
        {
            right = dfs(matrix, x, y+1, cache);
        }
        
        int max = Math.max(Math.max(left, right), Math.max(up, down));
        cache.put(idx, max+1);
        return max+1;
    }
}