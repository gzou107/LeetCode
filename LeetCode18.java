/*
18. 4Sum My Submissions Question
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return help(nums, target);
    }
    
    private List<List<Integer>> help(int[]nums, int target)
    {
        List<List<Integer>> result = new ArrayList<>();
        
        if(nums == null || nums.length < 4) return result;
        
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++)
        {
            if(i > 0 && nums[i] == nums[i-1])
            {
                continue;
            }
            
            for(int j = i + 1; j < nums.length - 2; j++)
            {
                if(j > i+1 && nums[j] == nums[j-1])
                {
                    continue;
                }
                
                for(int k = j + 1, m = nums.length - 1; m > k; )
                {
                    int val = nums[i] + nums[j] + nums[k] + nums[m];
                    if(val == target)
                    {
                        List<Integer> oneSol = new ArrayList<>();
                        oneSol.add(nums[i]);
                        oneSol.add(nums[j]);
                        oneSol.add(nums[k]);
                        oneSol.add(nums[m]);
                        result.add(oneSol);
                        k++;
                        m--;
                        while(k < m && nums[k] == nums[k-1]){
                            k++;
                        }
                        while(m>k && nums[m] > nums[m+1]){
                            m--;
                        }
                    }else if(val < target){
                        k++;
                    }else{
                        m--;
                    }
                }
            }
        }
        
        return result;
    }
}