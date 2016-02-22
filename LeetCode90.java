/*
90. Subsets II My Submissions Question
Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        return help0(nums);
    }
    
    private List<List<Integer>> help0(int[]nums)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null) return result;
        
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
 // https://leetcode.com/discuss/34975/accepted-solution-backtracking-only-lines-easy-understand   
    private void dfs(int [] nums, int pos, List<Integer>candidate, List<List<Integer>>result)
    {
        if(pos <= nums.length){
            result.add(new ArrayList<Integer>(candidate));
        }
        
        for(int i = pos; i < nums.length; i++)
        {
            // if we have visit this value before, we don't use it anymore
            if(i == pos || nums[i] != nums[i-1])
            {
                candidate.add(nums[i]);
                dfs(nums, i+1, candidate, result);
                candidate.remove(candidate.size()-1);
            }
        }
    }
}