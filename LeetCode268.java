/*
268. Missing Number My Submissions Question
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

public class Solution {
    public int missingNumber(int[] nums) {
        //return helper1(nums);
        return helper2(nums);
    }
    
    private int helper1(int [] nums){
        // we're sure the number are from 0, to n
        // each should be in its corresponding postion
        // for each element, we swap to its right postion, and continue check this element
        if(nums == null || nums.length == 0) return 0;
        
        for(int i = 0; i < nums.length; i++){
            
            while(i != nums[i]){
                if(nums[i] == nums.length){
                    break; // we keep the nums.length in its current position
                }
                
                swap(nums, i, nums[i]);
            }
        }
        
        for(int i = 0; i< nums.length; i++){
            if(i != nums[i]){
                return i;
            }
        }
        return nums.length;
    }
    
    private void swap(int [] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    private int helper2(int [] nums){
        
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            ans ^= ( (i+1) ^ nums[i]);
        }
        
        return ans;
    }
}