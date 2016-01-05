/*
259. 3Sum Smaller My Submissions Question
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
*/

public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        return helper(nums, target);
    }
    
    private int helper(int[]nums, int target)
    {
        if(nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        
        int res = 0;
        for(int i = 0; i <nums.length -2; i++)
        {
            int r = nums.length - 1;
            for(int j = i + 1; j < nums.length -1 && j < r; )
            {
                int sum = nums[i] + nums[j] + nums[r];
                if(sum < target)
                {
                    res += r - j;
                    j++;
                }else{
                    r--;
                }
            }
        }
        
        return res;
    }
}