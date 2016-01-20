/*
286. Walls and Gates My Submissions Question
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
  */

public class Solution {
    public void wallsAndGates(int[][] rooms) {
        help0(rooms);
        //help1(rooms);
    }
    // 7 ms
    private void help0(int [][] rooms)
    {
        if(rooms == null || rooms.length == 0) return;
        
        int row = rooms.length;
        int col = rooms[0].length;
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(rooms[i][j] == 0){
                    dfs(rooms, i, j, 0);
                }
            }
        }
        //does nothing here
    }
    
    private void dfs(int[][]rooms, int x, int y, int d)
    {
        if( x <0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] < d) return; // rooms[x][y] ==d, we'do nothing here, as we enter point rooms[x][y] ===d
        
        rooms[x][y] = d;
        dfs(rooms, x-1, y, d+1);
        dfs(rooms, x+1, y, d+1);
        dfs(rooms, x, y-1, d+1);
        dfs(rooms, x, y+1, d+1);
    }
    
    // 21 ms
    private void help2(int[][]rooms)
    {
        if(rooms == null || rooms.length == 0) return;
        
        int row = rooms.length;
        int col = rooms[0].length;
        boolean [][] visited = new boolean[row][col];
        int d = 0;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < row; i++ )
        {
            for(int j = 0; j < col; j++)
            {
                if(rooms[i][j] == 0)
                {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        
        int size = q.size();
        int nextDistanceCount = 0;
        while(size > 0)
        {
            while(size > 0) // handle all the entry within same level
            {
                int [] top = q.poll();
                size--;
                int x = top[0];
                int y = top[1];
                
                rooms[x][y] = d;
                if(x-1>=0 && !visited[x-1][y] && rooms[x-1][y] == Integer.MAX_VALUE )
                {
                    visited[x-1][y] = true;
                    q.add(new int[]{x-1, y});
                    nextDistanceCount++;
                }
                
                if(x+1 < row && !visited[x+1][y] && rooms[x+1][y] == Integer.MAX_VALUE)
                {
                    visited[x+1][y] = true;
                    q.add(new int[]{x+1, y});
                    nextDistanceCount++;
                }
                
                if(y-1>=0 && !visited[x][y-1] && rooms[x][y-1] == Integer.MAX_VALUE)
                {
                    visited[x][y-1] = true;
                    q.add(new int[]{x, y-1});
                    nextDistanceCount++;
                }
                
                if(y+1 < col && !visited[x][y+1] && rooms[x][y+1] == Integer.MAX_VALUE)
                {
                    visited[x][y+1] = true;
                    q.add(new int[]{x, y+1});
                    nextDistanceCount++;
                }
                
            }
            
            size = nextDistanceCount;
            nextDistanceCount = 0; // need reset the counter!!!
            d++;
        }
        
        // done here
    }
    
    // 14 ms
    private void help1(int[][]rooms)
    {
        if(rooms == null || rooms.length == 0) return;
        
        int row = rooms.length;
        int col = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < row; i++ )
        {
            for(int j = 0; j < col; j++)
            {
                if(rooms[i][j] == 0)
                {
                    q.add(new int[]{i, j});
                }
            }
        }
        
        int size = q.size();
        while(size > 0)
        {
            size = q.size();
            for(int i = 0; i < size; i++) // handle all the entry within same level
            {
                int [] top = q.poll();
                int x = top[0];
                int y = top[1];
                
                if(x-1>=0  && rooms[x-1][y] == Integer.MAX_VALUE )
                {
                    rooms[x-1][y] = rooms[x][y] + 1;
                    q.add(new int[]{x-1, y});
                }
                
                if(x+1 < row && rooms[x+1][y] == Integer.MAX_VALUE)
                {
                    rooms[x+1][y] = rooms[x][y] + 1;
                    q.add(new int[]{x+1, y});
                }
                
                if(y-1>=0 && rooms[x][y-1] == Integer.MAX_VALUE)
                {
                    rooms[x][y-1] = rooms[x][y] + 1;
                    q.add(new int[]{x, y-1});
                }
                
                if(y+1 < col && rooms[x][y+1] == Integer.MAX_VALUE)
                {
                    rooms[x][y+1] = rooms[x][y] + 1;
                    q.add(new int[]{x, y+1});
                }
                
            }
        }
        
        // done here
    }
}