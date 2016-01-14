/*
62. Unique Paths My Submissions Question
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
*/

public class Solution {
    public int uniquePaths(int m, int n) {
        return helper0(m, n);
    }
    
    private int helper0(int m, int n)
    {
        int [] dp = new int[n];
       
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(j == 0)
                {
                    dp[j] = 1;
                }else{
                    dp[j] += dp[j-1];
                }
            }
        }
        
        return dp[n-1];
    }
}