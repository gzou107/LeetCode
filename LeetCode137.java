/*
137. Single Number II My Submissions Question
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class Solution {
    public int singleNumber(int[] nums) {
        //return help0(nums);
        return help1(nums);
    }
    // https://segmentfault.com/a/1190000003702862
    private int help0(int [] nums)
    {
        int result = 0;
        int[]bits = new int[32];
        for(int i = 0; i < 32; i++)
        {
            for(int j = 0; j < nums.length; j++)
            {
                bits[i] += (nums[j]>>i)&1;
            }
            
            result |= ((bits[i]%3)<<i);
        }
        return result;
    }
    
    private int help1(int [] nums)
    {
        int ones = 0;
        int twos = 0;
        int threes =0;
        for(int i = 0; i < nums.length; i++)
        {
            twos |= ones & nums[i];
            ones ^= nums[i];
            threes = ones& twos;
            ones &=~threes;
            twos &=~threes;
        }
        return ones;
    }
}