/*
300. Longest Increasing Subsequence My Submissions Question
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/

public class Solution {
    public int lengthOfLIS(int[] nums) {
        
       // return helper1(nums);
        return helper2(nums);
        
    }
    
    private int helper1(int[] nums){
        // define num[i]: longest lis with a[i] at the end
        // hence num[i] = max(num[j] + 1) for all j <i and nums[j] <= nums[i]
        
        if(nums == null || nums.length < 1){
            return 0;
        }
        
        int [] len = new int[nums.length];
        Arrays.fill(len, 0);
        int max  = 1;
        
        for(int i = 0; i < nums.length; i++)
        {
            // we don't want to do linear scan here,
            // but instead we maintain a sorted array
            for(int j = i-1; j>=0; j--)
            {
                if(nums[j] < nums[i])
                {
                    if(len[i] < len[j] + 1)
                    {
                        len[i] = len[j] + 1;
                        
                        if(max < len[i]) max = len[i];
                    }
                }
            }
            // all bigger than i, or i is the first element, set it to 1
            if(len[i] == 0){
                len[i] = 1;
            }
        }
        
        return max;
    }
    
    private int helper2(int [] nums){
        
        if(nums == null || nums.length < 1) return 0;
        
        int [] longestIndex = new int[nums.length];
        longestIndex[0] = 0;
        int len = 1;
        
        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] < nums[longestIndex[0]])
            {
                longestIndex[0] = i;
            }else if(nums[i] > nums[longestIndex[len-1]])
            {
                longestIndex[len++] = i;
            }else{
                // after first two if conditions, we know nums[i] must lie somewhere in the middle
                // now, we will find first greater(ceiling) element, and replace it with the i
                // as we can potential find a better solution in the future
                int l = 0;
                int h = len -1;
                // find the first greater or equals than nums[i], and replace it
                while(h > l){
                    int m = l + ((h-l)>>>1);
                    if(nums[longestIndex[m]] >= nums[i]){
                        h = m;
                    }else{
                        l = m + 1;
                    }
                }
                
                longestIndex[h] = i;
            }
        }
        
        return len;
    }
}