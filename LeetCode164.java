/*
164. Maximum Gap My Submissions Question
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Credits:
Special thanks to @porker2008 for adding this problem and creating all test cases.
*/

public class Solution {
    public int maximumGap(int[] nums) {
        return help0(nums);
    }
    
    private int help0(int[] nums)
    {
        if(nums == null || nums.length <2) return 0;
        Arrays.sort(nums);
        int max = 0;
        for(int i = 1; i < nums.length; i++)
        {
            max = Math.max(max, nums[i] - nums[i-1]);
        }
        return max;
    }
}