/*
15. 3Sum My Submissions Question
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //return helper0(nums);
        return helper(nums);
    }
    
    private List<List<Integer>> helper0(int [] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3) return result;
        Arrays.sort(nums);
    
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;        // Skip same results
            int target = 0 - nums[i];
            int j = i + 1, k = len - 1;
            while(j < k) {
                if(nums[j] + nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while(j < k && nums[j] == nums[j + 1]) j++;  // Skip same results
                    while(j < k && nums[k] == nums[k - 1]) k--;  // Skip same results
                    j++; k--;
                } else if(nums[j] + nums[k] < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }
    private List<List<Integer>> helper(int [] nums)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length <  3) return res;
        
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len -2; i++)
        {
            if(i>=1 && nums[i] == nums[i-1]) continue;
       
            int target = 0 - nums[i];
            int r = len - 1;
            int j = i + 1;
            while(j < r)
            {
                int sum = nums[j] +nums[r];
                if(sum == target)
                {
                   // the new constructor below is costy
                   // List<Integer> oneSol = new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[r]));
                    res.add(Arrays.asList(nums[i], nums[j], nums[r]));
                    j++;
                    while(j < r && nums[j] == nums[j-1]){
                        j++;
                    }
                        
                    r--;
                    while(r > j && nums[r] == nums[r+1])
                    {
                        r--;
                    }
                    }else if(sum < target){
                        j++;
                    }else{
                        r--;
                    }
                }
        }
        
        return res;
    }
}