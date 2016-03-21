/*
22. Generate Parentheses My Submissions Question
Total Accepted: 81709 Total Submissions: 225315 Difficulty: Medium
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

public class Solution {
    public List<String> generateParenthesis(int n) {
        return help(n);
    }
    
    public List<String> help(int n)
    {
        List<String> result = new ArrayList<String>();
        if(n <= 0){
            return result;
        }
        
        dfs(n,n, new StringBuilder(), result);
        
        return result;
    }
    
    private void dfs(int leftRemaining, int rightRemaining, StringBuilder sb, List<String>result)
    {
        if(leftRemaining == 0 && rightRemaining == 0){
            result.add(sb.toString());
        }
        
        if(leftRemaining >0){
            sb.append("(");
            dfs(leftRemaining-1, rightRemaining, sb, result);
            sb.setLength(sb.length()-1);
        }
        
        if(leftRemaining < rightRemaining){
            sb.append(")");
            dfs(leftRemaining, rightRemaining-1, sb, result);
            sb.setLength(sb.length()-1);
        }
    }
}