/*
136. Single Number My Submissions Question
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class Solution {
    public int singleNumber(int[] nums) {
        return helper(nums);
    }
    
    private int helper(int[] nums)
    {
        int res = 0;
        for(int i = 0; i < nums.length; i++)
        {
            res ^= nums[i];
        }
        return res;
    }
    
    private int helper1(int[] nums)
    {
        Set<Integer> s = new HashSet<Integer>();
        for(Integer number : nums)
        {
            if(s.contains(number)){
                s.remove(number);
            }else{
            s.add(number);
            }
        }
        Iterator<Integer> it = s.iterator();
        return it.next();
    }
}