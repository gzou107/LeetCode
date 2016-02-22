/*
51. N-Queens My Submissions Question
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Hide Tags Backtracking
Show Similar Problems
*/

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        return help0(n);
    }
    
    private List<List<String>> help0(int n)
    {
        List<List<String>> result = new ArrayList<List<String>>();
        
        if(n <= 0 || n == 2 || n == 3) return result;
        
        dfs(n, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void dfs(int n, List<Integer> candidate, List<List<String>> result)
    {
        if(candidate.size() == n){
            List<String> oneSol = convert(candidate, n);
            result.add(oneSol);
            return;
        }
        
        if(candidate.size() > n){
            return;
        }
        
        for(int i= 0; i < n; i++)
        {
            if(isValid(candidate, i))
            {
                candidate.add(i);
                dfs(n, candidate, result);
                candidate.remove(candidate.size()-1);
            }
        }
    }
    
    private List<String> convert(List<Integer> config, int n)
    {
        List<String> sol = new ArrayList<String>();
        for(int i = 0; i < n; i++)
        {
            StringBuilder sb = new StringBuilder();
            int queen = config.get(i);
            for(int j = 0; j < n; j++)
            {
                if(j == queen){
                    sb.append('Q');
                }else{
                    sb.append('.');
                }
            }
            sol.add(sb.toString());
        }
        return sol;
    }
    
    private boolean isValid(List<Integer>config, int pos)
    {
        // assume config is a valid partial solution, and add
        // a new queen to see whether it's good or bad
        int row = config.size();
        for(int i = 0; i < config.size(); i++)
        {
            if(config.get(i) == pos || row - i == Math.abs(pos - config.get(i)) )
            {
                return false;
            }
        }
        
        return true;
    }
}