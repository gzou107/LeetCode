/*
34. Search for a Range My Submissions Question
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        return help(nums, target);
    }
    
    private int[] help(int[]nums, int target)
    {
        int[]res = new int[]{-1,-1};
        if(nums == null || nums.length == 0 || nums[0]> target || nums[nums.length-1] < target){
            return res;
        }
        
        // find the first equals to target
        int l = 0;
        int r = nums.length-1;
        while( l < r){
            int m = l + ((r-l)>>1);
            if(nums[m] < target){
                l = m + 1;
            }else{
                r = m;
            }
        }
        if(nums[l] != target){
            return res;
        }
        res[0] = l;
        
        //find the last one equals to target
        l = 0;
        r = nums.length -1;
        while(l < r){
            int m = l + ((r-l+1)>>1);
            
            if(nums[m] == target){
                l = m;
            }else if(nums[m] < target){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        
        res[1] = l;
        return res;
    }
}