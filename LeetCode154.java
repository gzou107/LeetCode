/*
154. Find Minimum in Rotated Sorted Array II My Submissions Question
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

public class Solution {
    public int findMin(int[] nums) {
        return helper(nums);
    }
    
    private int helper(int [] nums){
        if (nums == null || nums.length <= 0){
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        
        while (l < r){
            int m = l + (r - l)/2;
            
            if(nums[m] < nums[r]){
                r = m;
            }else if(nums[m] > nums[r]){
                l = m + 1;
            }else{
                r--;
            }
        }
        
        return nums[l];
    }
}