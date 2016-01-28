/*
55. Jump Game My Submissions Question
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

public class Solution {
    public boolean canJump(int[] nums) {
        return help0(nums);
    }
    
    private boolean help0(int [] nums)
    {
        //define dp[i]: maximum steps left when reaching position i (already reached position i)!!!
        // dp[i] = Math.max(dp[i-1], A[i-1]) - 1;
        // false if dp[i] < 0
        
        //if(nums == null || nums.length == 0) return false
        int [] dp = new int[nums.length];
        dp[0] = 0;
        for(int i = 1; i < nums.length; i++)
        {
            dp[i] = Math.max(dp[i-1], nums[i-1]) - 1;
            if(dp[i] < 0) return false;
        }
        
        return dp[nums.length-1]>=0;
    }
    
    
    private boolean help(int [] nums)
    {
        if(nums == null || nums.length == 0) return false;
        
        int maxReach = nums[0];
        for(int i = 0; i < nums.length && i <=maxReach; i++)
        {
            maxReach = Math.max(maxReach, nums[i] + i);
            if(maxReach >= nums.length -1)
            {
                return true;
            }
        }
        
        return false;
    }
}