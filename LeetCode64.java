/*
64. Minimum Path Sum My Submissions Question
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/


public class Solution {
    public int minPathSum(int[][] grid) {
        //return helper0(grid);
        return helper(grid);
    }
    
    private int helper(int[][] grid)
    {
        int col = grid[0].length;
        int [] dp = new int[col];
        
        dp[0] = grid[0][0];
        for(int i = 1; i < col; i++)
        {
            dp[i] = dp[i-1] + grid[0][i];
        }
        
        for(int i = 1; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(j >0)
                {
                   dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
                }else{
                   dp[j] += grid[i][0];
                }
            }
        }
        
        return dp[col-1];
    }
}