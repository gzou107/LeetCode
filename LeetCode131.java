/*
131. Palindrome Partitioning My Submissions Question
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

public class Solution {
    public List<List<String>> partition(String s) {
        return help(s);
    }
    
    private List<List<String>> help(String s)
    {
        List<List<String>> result = new ArrayList<List<String>>();
        if(s== null || s.length() == 0) return result;
        
        dfs(s, new ArrayList<String>(), result);
        return result;
    }
    
    private void dfs(String s, List<String>candidate, List<List<String>> result)
    {
        if(s == null || s.length() == 0)
        {
            result.add(new ArrayList<String>(candidate));// create a new one !!!
            return;
        }
        
        for(int i = 1; i <= s.length(); i++)
        {
            String subString = s.substring(0, i);
            if(isPalindrom(subString))
            {
                candidate.add(subString);
                dfs(s.substring(i), candidate, result);
                candidate.remove(candidate.size()-1);
            }
        }
    }
    
    private boolean isPalindrom(String s)
    {
        for(int i = 0, j = s.length()-1; i < j; i++,j--)
        {
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
    private void calculatePalin(String s, boolean[][]cache)
    {
        for(int i = 0; i < s.length(); i++)
        {
            cache[i][i] = true;
        }
        
        for(int i = 0; i < s.length(); i++)
        {
            for(int j = i +1; j < s.length(); j++)
            {
                if(s.charAt(i) == s.charAt(j) && (j-i < 2 || cache[i+1][j-1]))
                {
                    cache[i][j] = true;
                }
            }
        }
    }
}