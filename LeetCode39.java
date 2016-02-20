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
/*
*  2016/02/19 AIrbnb phone interview question !!!
*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return help0(candidates, target);
        //return help1(candidates, target);
    }
    
    private List<List<Integer>> help0(int[]candidate, int target)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidate == null || candidate.length == 0) return res;
        
        Arrays.sort(candidate);
        dfs(candidate, 0, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs(int[] candidate, int pos, int target, int value, List<Integer> temp, List<List<Integer>> res)
    {
        if(target == value)
        {
            List<Integer> oneSol = new ArrayList<>(temp);
            res.add(oneSol);
            return;
        }
        
        if(value > target){
            return;
        }
        
        for(int i = pos; i < candidate.length; i++)
        {
            value += candidate[i];
            temp.add(candidate[i]);
            
            dfs(candidate, i, target, value, temp, res);
            
            temp.remove(temp.size()-1);
            value -= candidate[i];
        }
    }
    
    private List<List<Integer>> help1(int[]candidate, int target)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidate == null || candidate.length == 0) return res;
        
        Arrays.sort(candidate);
        dfs1(candidate, 0, target, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs1(int[] candidate, int pos, int target,  List<Integer> temp, List<List<Integer>> res)
    {
        if(target == 0)
        {
            List<Integer> oneSol = new ArrayList<>(temp);
            res.add(oneSol);
            return;
        }
        
        if(target < 0 || pos >= candidate.length || candidate[pos] > target){
            return;
        }
        
        for(int i = pos; i < candidate.length; i++)
        {
            target -= candidate[i];
            temp.add(candidate[i]);
            
            dfs1(candidate, i, target, temp, res);
            
            temp.remove(temp.size()-1);
            target += candidate[i];
        }
    }
}