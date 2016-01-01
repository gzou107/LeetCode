/*
317. Shortest Distance from All Buildings My Submissions Question
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

public class Solution {
    public int shortestDistance(int[][] grid) {
        return helper(grid);
    }
    
    private int helper(int[][]grid)
    {
        int buildingCount = 0;
        int[][]dist = new int[grid.length][grid[0].length];
        int[][]count = new int[grid.length][grid[0].length];
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 1)
                {
                    buildingCount++;
                   // bfs(grid,i,j,dist,count);
                   bfs2(grid, i, j, dist,count);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(dist[i][j] !=0 && count[i][j] == buildingCount && dist[i][j] <min )
                {
                    min = dist[i][j];
                }
            }
        }
         
        return min == Integer.MAX_VALUE?-1: min;

    }
    
    private void bfs(int[][]grid, int r, int c, int[][]dist, int[][]count)
    {
        boolean [][]visited = new boolean[grid.length][grid[0].length];
        Queue<Integer>q = new ArrayDeque<Integer>();
        q.add(r);
        q.add(c);
        int level = 0;
        int currCount = 2;
        int nextCount = 0;
        int d = 1;
       // int[][]dir = {{-1,0},{1,0},{0,-1},{0,1}};
        
        while(!q.isEmpty())
        {
            
            while(currCount > 0)
            {
                int row = q.remove();
                int col = q.remove();
                currCount -=2;
                
                if(row - 1 >= 0 && !visited[row-1][col] && grid[row-1][col] == 0){
                    count[row-1][col]++;
                    dist[row-1][col] += d;
                    visited[row-1][col] = true;
                    q.add(row-1);
                    q.add(col);
                    nextCount += 2;
                }
                
                if(row+1 < grid.length && !visited[row+1][col] && grid[row+1][col] == 0)
                {
                    count[row+1][col]++;
                    dist[row+1][col] += d;
                    visited[row+1][col] = true;
                    q.add(row+1);
                    q.add(col);
                    nextCount +=2;
                    
                }
                
                if(col -1 >=0 && !visited[row][col-1] && grid[row][col-1] == 0)
                {
                    count[row][col-1]++;
                    dist[row][col-1] += d;
                    visited[row][col-1] = true;
                    q.add(row);
                    q.add(col-1);
                    nextCount +=2;
                }
                
                if(col + 1 < grid[0].length && !visited[row][col+1] && grid[row][col+1] == 0)
                {
                    count[row][col+1]++;
                    dist[row][col+1] +=d;
                    visited[row][col+1] = true;
                    q.add(row);
                    q.add(col+1);
                    nextCount +=2;
                }
            }
            
            currCount = nextCount;
            nextCount = 0;
            d++;
        }
    }
    
     private void bfs2(int[][]grid, int r, int c, int[][]dist, int[][]count)
    {
        boolean [][]visited = new boolean[grid.length][grid[0].length];
        Deque<Integer>q = new ArrayDeque<Integer>();
        q.add(r);
        q.add(c);
        int d = 1;
        int[][]dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i = 0; i < size; i+= 2) //becareful, we remove 2 elements here
            {
                int row = q.removeFirst();
                int col = q.removeFirst();
                
                for(int x = 0; x < dirs.length; x++)
                {
                    int newRow = row + dirs[x][0];
                    int newCol = col + dirs[x][1];
                    
                    if(newRow>=0 && newRow < grid.length && newCol >=0 && newCol < grid[0].length && !visited[newRow][newCol] && grid[newRow][newCol] == 0)
                    {
                        q.addLast(newRow);
                        q.addLast(newCol);
                        dist[newRow][newCol] += d;
                        count[newRow][newCol]++;
                        visited[newRow][newCol] = true;
                    }
                }
            }
            d++;
        }
    }
}