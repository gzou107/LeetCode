/*
276. Paint Fence My Submissions Question
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

public class Solution {
    public int numWays(int n, int k) {
        return help2(n, k);
    }
    
    private int help1(int n, int k)
    {
        // induction:
        // diff[n] = (diff[n-1] + same[n-1]) * (k-1)
        // same[n] = diff[n-1]
        // total[n] = diff[n] + same[n]
        
        if(n == 0) return 0;
        if(n == 1) return k;
        
        // now n >= 2
        int diff =  k * (k-1);
        int same = k;
        for(int i = 2; i < n; i++){
            int temp = diff;
            diff = (diff + same) * ( k - 1);
            same = temp;
        }
        return diff + same;
    }
    
    private int help2(int n, int k)
    {
        int [] dp = {0, k, k*k, 0};
        
        if(n <= 2) return dp[n];
        for(int i = 2; i < n; i++ )
        {
          dp[3] = (dp[1] + dp[2]) * (k-1); // // 递推式：第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色
          dp[1] = dp[2];
          dp[2] = dp[3];
        }
        return dp[3];
            
    }
}