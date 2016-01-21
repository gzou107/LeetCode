/*
40. Combination Sum II My Submissions Question
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6]
*/

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return help(candidates, target);
    }
    
    private List<List<Integer>> help(int[] candidates, int target)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(candidates == null || candidates.length == 0) return res;
        
        List<Integer> candidate = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, candidate, res);
        
        return res;
    }
    
    private void dfs(int [] candidates, int target, int start, List<Integer> candidate, List<List<Integer>> res)
    {
        if(target == 0){
            res.add(new LinkedList<Integer>(candidate));
            return;
        }
        
        if( start == candidates.length || target < 0 || candidates[start] > target)
        {
            return;
        }
        
        for(int i = start; i < candidates.length; i++)
        {
            // to do dedup, we only start from start, not from 0!!!
            // as from abstraction point, we can see only [start, len-1]
            if(i > start && candidates[i-1] == candidates[i]){
                continue;
            }
            
            if(candidates[i] <= target)
            {
                candidate.add(candidates[i]);
                dfs(candidates, target - candidates[i], i+1, candidate, res);
                candidate.remove(candidate.size()-1);
            }
        }
    }
}