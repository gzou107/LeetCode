/*
77. Combinations My Submissions Question
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        //return help0(n, k);
        //return help(n, k);
        return help1(n, k);
    }
    
    
    private List<List<Integer>> help1(int n, int k)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(k > n || k <=0 || n <= 0) return res;
        
        List<Integer>candidate = new LinkedList<Integer>();
        int i = 1;
        while(i <=n || !candidate.isEmpty())
        {
            if(candidate.size() == k){
                res.add(new LinkedList<Integer>(candidate));
            }
            
            if(i > n || candidate.size() == k) // we need remove last one if we reach over n
            {
                i = candidate.get(candidate.size()-1) + 1;// smart way to reset to next pos
                candidate.remove(candidate.size() - 1);
            }else{
                candidate.add(i);
                i++;
            }
        }
        
        return res;
    }
    private List<List<Integer>> help(int n, int k)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( k > n || k <= 0 || n <= 0) return res;
        
        int[] arr = new int[n];
        for(int i = 1; i <= n; i++){
            arr[i-1] = i;
        }
        List<Integer> candidate = new LinkedList<>();
        dfs(arr, 0, k, candidate, res);
        return res;
    }
    
    private void dfs(int [] arr, int idx, int k, List<Integer> candidate, List<List<Integer>> res)
    {
        
        if(candidate.size() == k){
            List<Integer> oneCom = new LinkedList<Integer>(candidate);
            res.add(oneCom);
            return;
        }
        
        for(int i = idx; i < arr.length; i++)
        {
            candidate.add(arr[i]);
            dfs(arr, i+1, k, candidate, res);
            candidate.remove(candidate.size()-1);
        }
    }
    
    private List<List<Integer>> help0(int n, int k)
    {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( k > n || k <= 0 || n <= 0) return res;
        
        List<Integer> candidate = new LinkedList<>();
        dfs0(1, k, n, candidate, res);
        return res;
    }
    private void dfs0(int idx, int k, int n, List<Integer> candidate, List<List<Integer>> res)
    {
        
        if(candidate.size() == k){
            List<Integer> oneCom = new LinkedList<Integer>(candidate);
            res.add(oneCom);
            return;
        }
        
        for(int i = idx; i <= n; i++)
        {
            candidate.add(i);
            dfs0(i+1, k, n, candidate, res);
            candidate.remove(candidate.size()-1);
        }
    }
}