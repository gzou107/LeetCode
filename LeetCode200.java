/*
200. Number of Islands My Submissions Question
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

Credits:
*/
/*
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid==null || grid.length==0 || grid[0].length==0) return 0;
        int count = 0;
     
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    count++;
                    merge(grid, i, j);
                }
            }
        }
        return count;
    }
     
    public void merge(char[][] grid, int i, int j){
        //validity checking
        if(i<0 || j<0 || i>grid.length-1 || j>grid[0].length-1)
            return;
     
        //if current cell is water or visited
        if(grid[i][j] != '1') return;
     
        //set visited cell to '0'
        grid[i][j] = '0';
     
        //merge all adjacent land
        merge(grid, i-1, j);
        merge(grid, i+1, j);
        merge(grid, i, j-1);
        merge(grid, i, j+1);
    }
}*/

public class Solution {
    public int numIslands(char[][] grid) {
        return helper1(grid);
        
      
    }
    
    
    private int helper1(char [][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        
        int count = 0;
        final int r = grid.length;
        final int c = grid[0].length;
        
        boolean [][] visited = new boolean[r][c];
        for(int i = 0; i <r; i++){
            Arrays.fill(visited[i], false);
        }
        
        for(int i = 0; i <r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    count++;
                    bfs1(grid, visited,i, j, r, c);
                    //dfs(grid, visited, i, j);
                }
            }
        }
        
        return count;
    }
    
     private void bfs2(char[][] grid, boolean [][]visited, int row, int col, int r, int c){
        
        Queue<Integer> q1 = new ArrayDeque<Integer>();
        Queue<Integer> q2 = new ArrayDeque<Integer>();
        q1.add(row);
        q2.add(col);
        visited[row][col] = true;

        while(!q1.isEmpty())
        {
            int i = q1.remove();
            int j = q2.remove();
          //  visited[i][j] = true;
            
            if( i-1 >=0 && !visited[i-1][j] && grid[i-1][j] == '1'){
                q1.add(i-1);
                q2.add(j);
                visited[i-1][j] = true;
                
            }
        
            if(i+1< r && !visited[i+1][j] && grid[i+1][j] == '1'){
                // q.add((i+1)*c + j);
                q1.add(i+1);
                q2.add(j);
                visited[i+1][j] = true;
            }
            
            if(j-1>=0 && !visited[i][j-1] && grid[i][j-1] == '1'){
                q1.add(i);
                q2.add(j - 1);
                visited[i][j-1] = true;
            }
            
            if(j+1 < c && !visited[i][j+1] && grid[i][j + 1] == '1'){
                q1.add(i);
                q2.add(j + 1);
                visited[i][j+1] = true;
            }
        }
    }
    
    private void bfs1(char[][] grid, boolean [][]visited, int row, int col, int r, int c){
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(row * c + col);
        visited[row][col] = true;

        while(!q.isEmpty()){
            
            int top = q.remove();
            int i = top/c;
            int j = top%c;
            //visited[i][j] = true;
            
            if( i-1 >=0 && !visited[i-1][j] && grid[i-1][j] == '1'){
                q.add((i-1)*c + j);
                visited[i-1][j] = true;
            }
        
            if(i+1< r && !visited[i+1][j] && grid[i+1][j] == '1'){
                 q.add((i+1)*c + j);
                 visited[i+1][j] = true;
            }
            
            if(j-1>=0 && !visited[i][j-1] && grid[i][j-1] == '1'){
                q.add(i*c + j-1);
                visited[i][j-1] = true;
            }
            
            if(j+1 < c && !visited[i][j+1] && grid[i][j + 1] == '1'){
                q.add( i*c + j + 1);
                visited[i][j+1] = true;
            }
        }
    }
    
    private void dfs(char[][] grid, boolean [][] visited, int i, int j){
        visited[i][j] = true;
        // (i-1, j) (i+1, j), (i, j-1), (i, j+1)
        if( i-1 >=0 && !visited[i-1][j] && grid[i-1][j] == '1'){
            dfs(grid, visited, i-1, j);
        }
        
        if(i+1< grid.length && !visited[i+1][j] && grid[i+1][j] == '1'){
            dfs(grid, visited, i+1, j);
        }
        
        if(j-1>=0 && !visited[i][j-1] && grid[i][j-1] == '1'){
            dfs(grid, visited, i, j-1);
        }
        
        if(j+1 < grid[0].length && !visited[i][j+1] && grid[i][j+1] == '1'){
            dfs(grid, visited, i, j+1);
        }
    }
}