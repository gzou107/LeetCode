/*
1. Two Sum My Submissions Question

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.
*/

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        return help(nums, target);
    }
    
    private int[]help(int[]nums, int target)
    {
        int[]result = new int[2];
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++)
        {
            if(m.containsKey(target - nums[i]))
            {
                result[0] = m.get(target - nums[i]);
                result[1] = i;
                break;
            }
            m.put(nums[i], i);
        }
        return result;
    }
}