/*
45. Jump Game II My Submissions Question
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
*/

public class Solution {
    public int jump(int[] nums) {
        return help(nums);
    }
    
    private int help(int [] nums)
    {
        int minStepNeeded =  0;
        int reached = 0;
        int maxReach = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
            if(i > reached)
            {
                minStepNeeded++;
                reached = maxReach;
            }
            
            maxReach = Math.max(maxReach, nums[i] + i);
        }
        
        return minStepNeeded;
    }
}