/*
53. Maximum Subarray My Submissions Question
Total Accepted: 102600 Total Submissions: 283070 Difficulty: Medium
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.
*/
public class Solution {
    public int maxSubArray(int[] nums) {
        return help(nums);
    }
    
    private int help(int [] nums)
    {
        if(nums == null || nums.length == 0) return 0;
        
        int lMax = nums[0];
        int gMax = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
            lMax = Math.max(lMax + nums[i], nums[i]);
            gMax = Math.max(gMax, lMax);
        }
        return gMax;
    }
}