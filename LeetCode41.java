/*
41. First Missing Positive My Submissions Question
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

public class Solution {
    public int firstMissingPositive(int[] nums) {
        return help1(nums);
    }
    
    private int help1(int [] nums)
    {
        if(nums == null || nums.length == 0) return 1;
        
        for(int i = 0; i < nums.length;)
        {
            int j = nums[i];
            if(j <= 0 || j > nums.length){
                i++;
                continue;
            }else if(nums[j-1] != j){
                int temp = nums[j-1];
                nums[j-1] = j;
                nums[i] = temp;
            }else{
                i++;
            }
        }
        
        for(int i = 0; i < nums.length; i++)
        {
            if(i + 1 != nums[i]){
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}