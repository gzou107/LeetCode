/*
35. Search Insert Position My Submissions Question
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

public class Solution {
    public int searchInsert(int[] nums, int target) {
        return help(nums, target);
    }
    
    private int help(int[]nums, int target)
    {
        //we're trying to find first equal or bigger element
        if(nums == null || nums.length == 0) return 0;
        
        int l = 0; 
        int r = nums.length-1;
        
        if(nums[l] >= target) return l;
        else if(nums[r] < target) return r+1;
        
        while(l < r)
        {
            int m = l + ((r-l) >> 1);
            if(nums[m] == target){
                return m;
            }else if(nums[m] < target){
                l = m + 1;
            }else{
                r = m;
            }
        }
        
        return l;
    }
}