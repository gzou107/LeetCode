/*
238. Product of Array Except Self My Submissions Question
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        return helper1(nums);
    }
    
    private int [] helper1(int [] nums){
        int [] ans = new int[nums.length];
        int [] left = new int[nums.length];
        int [] right = new int [nums.length];
        // left[i] stores the product between [0, i-1], both inclusive
        // right[i] stores the product between [i+1, nums.length-1] both inclusive
        left[0] = 1;
        for(int i = 1; i < nums.length; i++){
            left[i] = left[i-1] * nums[i-1];
        }
        
        right[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            right[i] = right[i+1] * nums[i+1];
        }
        
        for(int i = 0; i < nums.length; i++){
            ans[i] = left[i] * right[i];
        }
        
        return ans;
    }
}