/*
39. Combination Sum My Submissions Question
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return help(candidates, target);
    }
    
    private List<List<Integer>> help(int[]candidates, int target)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if(candidates == null || candidates.length == 0) return res;
        
        Arrays.sort(candidates);
        List<Integer> candidate = new ArrayList<>();
        
        dfs(candidates, target, 0, candidate, res);
        
        return res;
    }
    
    private void dfs(int[] candidates, int target, int pos, List<Integer> candidate, List<List<Integer>> res)
    {
        if( 0 == target)
        {
            res.add(new ArrayList<Integer>(candidate));
            return;
        }
        
        if(0 > target || pos == candidates.length || candidates[pos] > target){
            return;
        }
        
        for(int i = pos; i < candidates.length; i++)
        {
           /* if(i >pos && candidates[i] == candidates[i-1]){
                continue;
            }*/
            
            candidate.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, candidate, res);
            candidate.remove(candidate.size()-1);
        }
    }
}