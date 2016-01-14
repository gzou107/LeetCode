/*
63. Unique Paths II My Submissions Question
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/


public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return helper0(obstacleGrid);
       // return helper1(obstacleGrid);
    }
    
    private int helper0(int [][]obstacleGrid )
    {
            int width = obstacleGrid[0].length;
            int[] dp = new int[width];
            dp[0] = 1;
            for (int[] row : obstacleGrid) 
            {
                for (int j = 0; j < width; j++) 
                {
                    if (row[j] == 1)
                        dp[j] = 0; // reset to 0 if starts with 1
                    else if (j > 0)
                        dp[j] += dp[j - 1];
                }
            }
            return dp[width - 1];
    
    }
    
    
    private int helper1(int [][] obstacles)
    {
        if(obstacles == null || obstacles.length <= 0) return 0;
        
        int row = obstacles.length;
        int col = obstacles[0].length;
        
        int [] curr = new int[col];
        int [] prev = new int[col];
        for(int i = 0; i < col; i++)
        {
            if(obstacles[0][i] == 1 || (i > 0 && curr[i-1] == 0))
            {
                curr[i] = 0;
            }else{
                curr[i] = 1;
            }
        }
        System.arraycopy(curr, 0, prev, 0, col);
        
        for(int i = 1; i < row; i++)
        {
            if(prev[0] == 0 || obstacles[i][0] == 1)
            {
                curr[0] = 0;
            }else{
                curr[0] = 1;
            }
            
            for(int j = 1; j < col; j++)
            {
                if(obstacles[i][j] == 1)
                {
                    curr[j] = 0;
                }else{
                    curr[j] = curr[j-1] + prev[j];
                }
            }
            System.arraycopy(curr, 0, prev, 0, col);
        }
        
        return curr[col-1];
    }
}