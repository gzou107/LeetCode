/*
153. Find Minimum in Rotated Sorted Array My Submissions Question
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

public class Solution {
    public int findMin(int[] nums) {
        
        return helper1(nums);
    }
    
    private int helper1(int[]nums){
        if(nums ==null || nums.length < 1) return -1;
        
        int l = 0;
        int r = nums.length - 1;
        while(l<r){
            int m = l + (r - l)/2;
            
            if(nums[m] < nums[r]){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return nums[l];
    }
}