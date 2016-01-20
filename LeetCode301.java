/*
301. Remove Invalid Parentheses My Submissions Question
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        return help(s);
        //return bfs(s);
    }
    
    private List<String> help(String s)
    {
        Set<String> res = new HashSet<>();
        int rmL = 0;
        int rmR = 0;
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c == '('){
                rmL++;
            }else if(c == ')')
            {
                if(rmL >0){
                    rmL--;
                }else{
                    rmR++;
                }
            }
        }
        
        dfs(res, s, 0, rmL, rmR, 0, new StringBuilder());
        
        return new ArrayList<String>(res);
    }
    
    private void dfs(Set<String> res, String s, int i, int rmL, int rmR, int open, StringBuilder sb)
    {
        if(i == s.length() && rmL == 0 && rmR == 0 && open == 0){
            res.add(sb.toString());
            return;
        }
        
        if(i == s.length() || rmL <0 || rmR < 0 || open < 0) return;
        
        char c = s.charAt(i);
        int len = sb.length();
        
        if(c == '('){
            dfs(res, s, i+1, rmL-1, rmR, open, sb); // not keep ( in the result
            dfs(res, s, i+1, rmL, rmR, open+1, sb.append(c)); // keep ( in the result
        }else if(c == ')'){
            dfs(res, s, i+1, rmL, rmR-1, open, sb);
            dfs(res, s, i+1, rmL, rmR, open-1, sb.append(c)); // keep ) in the result
        }else{
            dfs(res, s, i+1, rmL, rmR, open, sb.append(c));
        }

        sb.setLength(len);
    }
    
    private List<String> bfs(String s)
    {
        List<String> res = new LinkedList<>();
        if(s == null ) return res;
        
        Set<String> visited = new HashSet<String>();
        Queue<String> q = new LinkedList<>();
        q.add(s);
        visited.add(s);
        boolean found = false;
        
        while(!q.isEmpty())
        {
            //boolean found = false; if put here, we will find all valid ones, including shorter ones
            // we are finding all the valid ones within each level
            // if set the found = false in the outside of the loop, only the ones from same level will be
            // accepted
            String top = q.poll();
            
            if(isValid(top)){
                res.add(top);
                found = true;
            }
            
            if(found) continue;
            
            for(int i = 0; i < top.length(); i++)
            {
                char c = top.charAt(i);
                
                if( c != '(' && c !=')') continue;
                
                String t = top.substring(0, i) + top.substring(i+1);
                
                if(visited.contains(t)) continue;
                
                q.add(t);
                visited.add(t);
            }
        }
        return res;
    }
    
    private boolean isValid(String s)
    {
        int count = 0;
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c == '('){
                count++;
            }else if(c == ')'){
                if(count == 0) return false;
                count--;
            }
        }
        
        return count == 0;
    }
}