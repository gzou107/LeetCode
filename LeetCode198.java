/*
House Robber My Submissions Question
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        if(nums.length == 1) return nums[0];
        
        int [] opt = new int[nums.length];
        opt[0] = nums[0];
        int max = nums[0];
        opt[1] = Math.max(nums[0], nums[1]);
        max = max < opt[1]? opt[1]: max;
        
        for(int i = 2; i < nums.length; i++){
            opt[i] = Math.max(opt[i-1], opt[i-2] + nums[i]);
            if(opt[i] > max){
                max = opt[i];
            }
        }
        
        return max;
    }
}