/*
78. Subsets My Submissions Question
Total Accepted: 81017 Total Submissions: 268553 Difficulty: Medium
Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //return help0(nums);
        //return help1(nums);
        return help2(nums);
    }
    
    private List<List<Integer>> help0(int[]nums)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(nums == null || nums.length == 0)
        {
            return res;
        }
        
        int step = 1;
        int j = 0;
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        Arrays.sort(nums);
        for(int i : nums)
        {
            m.put(step <<(j++), i);
        }
        
        int size = 1 <<(nums.length);
        for(int i = 0; i < size; i++)
        {
            List<Integer> oneSol = new LinkedList<Integer>();
            /*for( j = 0; j <nums.length; j++)
            {
                if( ((i >> j) & 1) == 1)
                {
                    oneSol.add(oneSol.size(), m.get(1<<j) );
                }
            }*/
            
            for( j = nums.length -1; j >= 0; j--)
            {
                if( ((i >> j) & 1) == 1)
                {
                    oneSol.add(0, m.get(1 << j));
                }
            }
            res.add(oneSol);
        }
        return res;
    }
    
    private List<List<Integer>> help1(int[]nums)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(nums == null || nums.length == 0)
        {
            return res;
        }
        
        Arrays.sort(nums);
        int size = 1 << (nums.length);
        
        for(int i = 0; i < size; i++)
        {
            List<Integer> oneSol = new LinkedList<Integer>();
            for(int j = 0; j <nums.length; j++)
            {
                if( ((i >> j) & 1) == 1)
                {
                    oneSol.add(nums[j]);
                }
            }
            res.add(oneSol);
        }
        return res;
    }
    
    private List<List<Integer>> help2(int [] nums)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(nums == null || nums.length == 0)
        {
            return res;
        }
        
        Arrays.sort(nums);
        List<Integer> candidate = new LinkedList<>();
        dfs(nums, 0, candidate, res);
        return res;
    }
    
    private void dfs(int [] nums, int i, List<Integer> oneSol, List<List<Integer>> res)
    {
        if( i == nums.length){
            res.add(new LinkedList<Integer>(oneSol));
           return;
        }
        
       
            oneSol.add(nums[i]);
            dfs(nums, i+1, oneSol, res);
            oneSol.remove(oneSol.size()-1);
            dfs(nums, i+1, oneSol, res);
        
    }
}