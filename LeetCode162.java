/*
162. Find Peak Element My Submissions Question
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

public class Solution {
    public int findPeakElement(int[] nums) {
        //return help(nums);
        return help2(nums);
    }
    
    private int help(int [] nums)
    {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return 0;
        
        int l = 0;
        int r = nums.length-1;
        while( l < r -1)
        {
            int m = l + (r - l)/2;
            if(nums[m] < nums[m+1])
            {
                l = m;
            }else{
                r = m;
            }
        }
        
        return nums[l] < nums[r] ? r : l;
    }
    
    private int help2(int [] nums)
    {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return 0;
        
        int l = 0;
        int r = nums.length - 1;
        while(l < r) // stops at l == r
        {
            int m = l + (r-l)/2;
            
            if(nums[m] < nums[m+1]){
                l = m + 1;
            }else{
                r = m;
            }
        }
        
        return l;
    }
}