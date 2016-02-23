/*
47. Permutations II My Submissions Question
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        return help(nums);
    }
    
    private List<List<Integer>> help(int [] nums)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        
        Arrays.sort(nums);
        List<Integer> onePerm = new ArrayList<Integer>();
        for(int num : nums)
        {
            onePerm.add(num);
        }
        result.add(onePerm);
        
        while(hasNextPermutation(nums))
        {
            List<Integer> candidate = new ArrayList<Integer>();
            for(int num : nums)
            {
                candidate.add(num);
            }
            result.add(candidate);
        }
        return result;
    }
    
    private boolean hasNextPermutation(int[] nums)
    {
        // find the first inversion i, such that nums[i] < nums[i+1], if no such inversion exist, return false
        // find the first index j such that nums[j] > nums[i], swap i & j
        // reverse all the num between i-1 and nums.length-1
        int i = nums.length-2;
        while(i >=0)
        {
            if(nums[i] < nums[i+1]){
                break;
            }
            i--;
        }
        if(i <0) return false;
        
        int j = nums.length -1;
        while(nums[j] <= nums[i]){
            j--;
        }
        swap(nums, i, j);
        
        swapRange(nums, i+1, nums.length-1);
        
        return true;
    }
    
    private void swap(int [] nums, int i, int j)
    {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    private void swapRange(int [] nums, int i, int j)
    {
        while(i < j)
        {
            swap(nums, i++, j--);
        }
    }
}