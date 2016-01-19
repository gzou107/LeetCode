/*
80. Remove Duplicates from Sorted Array II My Submissions Question
Total Accepted: 63627 Total Submissions: 200523 Difficulty: Medium
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
*/


public class Solution {
    public int removeDuplicates(int[] nums) {
        return help(nums);
    }
    
    private int help(int [] nums)
    {
        if(nums == null) return 0;
        
        int dStart = 0;
        int dEnd = 0;
        int len = 0;
        while(dEnd < nums.length)
        {
            if(nums[dEnd] == nums[dStart])
            {
                if(dEnd - dStart + 1 <=2)
                {
                   nums[len++] = nums[dEnd]; 
                }else{
                    // does nothing here
                }
            }else{
                nums[len++] = nums[dEnd];
                dStart = dEnd;
            }
            dEnd++;
        }
        return len;
    }
}