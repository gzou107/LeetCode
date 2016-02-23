/*
52. N-Queens II My Submissions Question
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/


public class Solution {
    public int totalNQueens(int n) {
        return help0(n);
    }
    
    private int help0(int n)
    {
        if(n == 2 || n == 3) return 0;
        
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        
        
        dfs(n, new ArrayList<Integer>(), result);
        return result.get(0);
    }
    
    private void dfs(int n, List<Integer> config, List<Integer>result)
    {
        if(config.size() > n){
            return;
        }
        
        if(config.size() == n){
            result.set(0, result.get(0)+1);
            return;
        }
        
        for(int i = 0; i <n; i++)
        {
            if(isValid(config, i))
            {
                config.add(i);
                dfs(n, config, result);
                config.remove(config.size()-1);
            }
        }
    }
    
    private boolean isValid(List<Integer>config, int pos)
    {
        int row = config.size();
        for(int i = 0; i < config.size(); i++)
        {
            if(config.get(i) == pos || row - i == Math.abs(pos - config.get(i) ))
            {
                return false;
            }
        }
        return true;
    }
}