/*
282. Expression Add Operators My Submissions Question
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/

public class Solution {
    public List<String> addOperators(String num, int target) {
        
        return help1(num, target);
    }
    
    private List<String> help1(String num, int target)
    {
        List<String> res = new LinkedList<>();
        if(num == null || num.length() == 0) return res;
        
        // dfs(res, num, "",0, target, 0, 0);
        dfs2(res, num, new StringBuilder(), 0, target, 0, 0);
        return res;
    }
    
    private void dfs(List<String> res, String num, String candidate, int pos, int target, long current, long prev)
    {
        if(pos == num.length())
        {
            if(current == target)
            {
                res.add(candidate);
            }
            return;
        }
        
        for(int i = pos; i < num.length(); i++)
        {
            if(i != pos && num.charAt(pos) == '0'){
                break;
            }
            
            long val = Long.valueOf(num.substring(pos, i+1));
            if(pos == 0)
            {
                dfs(res, num, candidate+ val, i+1, target, val, val);
            }else{
                dfs(res, num, candidate +"+" + val, i + 1, target, current+val, val);
                
                dfs(res, num, candidate +"-" + val, i + 1, target, current-val, -val);
                
                dfs(res, num, candidate +"*" + val, i + 1, target, current-prev + prev * val, prev*val);
            }
        }
    }
    
    private void dfs2(List<String> res, String num, StringBuilder candidate, int pos, int target, long current, long prev)
    {
        if(pos == num.length())
        {
            if(current == target)
            {
                res.add(candidate.toString());
            }
            return;
        }
        
        for(int i = pos; i < num.length(); i++)
        {
            if(i != pos && num.charAt(pos) == '0'){
                break;
            }
            int len = candidate.length();
            
            long val = Long.parseLong(num.substring(pos, i + 1)); //Long.valueOf(num.substring(pos, i+1));
            if(pos == 0)
            {
                
                dfs2(res, num, candidate.append(val), i+1, target, val, val);
                candidate.setLength(len);
                
            }else{
                dfs2(res, num, candidate.append("+").append(val), i + 1, target, current+val, val);
                candidate.setLength(len);
                
                dfs2(res, num, candidate.append("-").append(val), i + 1, target, current-val, -val);
                candidate.setLength(len);
                
                dfs2(res, num, candidate.append("*").append(val), i + 1, target, current-prev + prev * val, prev*val);
                candidate.setLength(len);
            }
        }
    }
}