/*
312. Burst Balloons My Submissions Question
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

public class Solution {
    public int maxCoins(int[] nums) {
        
        return helper3(nums);
    }
 
     // help1 is easier to understand, recusrive solution
    private int helper1(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int [] num = new int[n+2];
        for(int i = 1; i <= n; i++)
        {
            num[i] = nums[i-1];
        }
        num[0] = num[n+1] = 1;
        // dp[i][j]: the max we can get from baloon i+1 to j+1, where i from -1 and j to n-1
        int [][] dp = new int[n+2][n+2];
        
        solve(num, dp, 1, n);// i<=k<=j, and k-1 and k+1, hence [1,n]
        return dp[1][n];
    }
    
    private int solve(int[] num, int [][] dp, int i, int j)
    {
        if(dp[i][j] > 0) return dp[i][j];
        
        for(int k = i; k<=j; k++)
        {
            dp[i][j] = Math.max(dp[i][j], num[i-1]*num[k]*num[j+1] + solve(num, dp, i, k-1) + solve(num, dp, k+1, j) );
        }
        return dp[i][j];
    }   
    
    // faster, but harder to understand,
    // iterative solution
    private int helper3(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int [] num = new int[n+2];
        for(int i = 1; i <= n; i++){
            num[i] = nums[i-1];
        }
        num[0] = num[n + 1] = 1;
        
        n+= 2;
        int[][] dp = new int[n][n];
        for(int len = 2; len < n; len++)
        {
            for(int left = 0; left < n - len; left++)
            {
                int right = left + len;
                for(int j = left + 1; j < right; j++) // j from left+1 to right-1, as we multiply num[left], num[right], hence j can't be neither of them
                {
                    
                    dp[left][right] = Math.max(dp[left][right], num[left]*num[j]*num[right] + dp[left][j] + dp[j][right]);
                }
            }
        }
        
        return dp[0][n-1];
    }

    private int helper2(int [] nums)
    {
         if (nums == null || nums.length == 0) return 0;

         int[][] dp = new int[nums.length][nums.length];
         
         // dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + nums[i-1]*nums[k]* nums[j+1] + dp[k+1][j] )
         // two dimension dp, so in order to calculate dp[i][j], we need first calculate all the one
         // dp[i][k] i<=k<=j and dp[k][j] i<=k<=j
         
        for (int len = 1; len <= nums.length; len++) 
        {
            for (int start = 0; start <= nums.length - len; start++) 
            {
                int end = start + len - 1;
                for (int i = start; i <= end; i++) 
                {
                    int coins = nums[i] * getValue(nums, start - 1) * getValue(nums, end + 1);
                    coins += i != start ? dp[start][i - 1] : 0; // If not first one, we can add subrange on its left.
                    coins += i != end ? dp[i + 1][end] : 0; // If not last one, we can add subrange on its right
                    dp[start][end] = Math.max(dp[start][end], coins);
                }
           }
        }
        return dp[0][nums.length - 1];
    }

    private int getValue(int[] nums, int i) { // Deal with num[-1] and num[num.length]
        if (i < 0 || i >= nums.length) {
            return 1;
        }
        return nums[i];
    }
}