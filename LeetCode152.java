/*
152. Maximum Product Subarray My Submissions Question
Total Accepted: 50076 Total Submissions: 235385 Difficulty: Medium
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

public class Solution {
    public int maxProduct(int[] nums) {
        return help(nums);
    }
    
    public int help(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        
        int lMin = nums[0];
        int lMax = nums[0];
        int gMax = lMax;
        
        for(int i = 1; i < nums.length; i++){
            
            int t = Math.min( Math.min(lMin*nums[i], nums[i]), lMax*nums[i]);
            int m = Math.max( Math.max(lMin*nums[i], nums[i]), lMax*nums[i]);
            lMin = t;
            lMax = m;
            
            gMax = Math.max(lMax, gMax);
        }
        
        return gMax;
    }
}