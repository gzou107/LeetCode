/*
93. Restore IP Addresses My Submissions Question
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        return help0(s);
    }
    
    private List<String> help0(String s)
    {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() < 4) return result;
        
        dfs(s, 0, 0, new StringBuilder(), result);
        
        return result;
    }
    
    private void dfs(String s, int pos, int count, StringBuilder candidate, List<String> result)
    {
        if(pos == s.length() && count == 4){
            String can = candidate.toString();
            result.add( can.substring(0, can.length()-1 ) );
        }
        
        if(pos + (4-count)* 3 < s.length()){
            return;
        }
        
        if(pos + (4-count) * 1 > s.length()){
            return;
        }
        
        int num = 0;
        for(int i = pos; i < s.length(); i++)
        {
            num = num*10 + s.charAt(i)-'0';
            if(num == 0)
            {
                int len = candidate.length();
                candidate.append("0.");
                dfs(s, i+1, count+1, candidate, result);
                candidate.setLength(len);
                break;
            }else if(num <= 255){
                int len = candidate.length();
                candidate.append(String.valueOf(num)+".");
                dfs(s, i+1, count+1, candidate, result);
                candidate.setLength(len);
            }else{
                break;
            }
        }
    }
}