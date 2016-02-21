/*
213. House Robber II My Submissions Question
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/

public class Solution {
    public int rob(int[] nums) {
        return help0(nums);
        //return help1(nums);
    }
    
    private int help0(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        
        return Math.max(robHelper(nums, 0, nums.length-2), robHelper(nums, 1, nums.length-1));
    }
    
    private int robHelper(int[]nums, int l, int r)
    {
        int pre = 0;
        int curr = 0;
        for(int i = l; i <= r; i++)
        {
            int temp = Math.max(pre + nums[i], curr);
            pre = curr;
            curr = temp;
        }
        return curr;
    }
    private int help1(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        if(nums.length == 1) return nums[0];
        // two scenarios: one is to take house 0 in the optimal sol
        //                the other is NOT to take house 0 in the optimal sol
        int []max1 = new int[1];
        int []max2 = new int[2];
        dp1(nums, new int[nums.length], max1);
        dp2(nums, new int[nums.length], max2);
        return Math.max(max1[0], max2[0]);
    }
    // take nums[0]
    private void dp1(int [] nums, int [] dp, int [] max)
    {
        dp[0] = nums[0];
        dp[1] = nums[0];
        max[0] = dp[0];
        
        for(int i = 2; i < nums.length-1; i++)
        {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
            max[0] = Math.max(dp[i], max[0]);
        }
    }
    
    // not take nums[0]
    private void dp2(int[]nums, int[]dp, int[] max)
    {
        dp[0] = 0;
        dp[1] = nums[1];
        max[0] = dp[1];
        
        for(int i = 2; i < nums.length; i++)
        {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
            max[0] = Math.max(dp[i], max[0]);  
        }
    }
}