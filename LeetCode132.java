/*
132. Palindrome Partitioning II My Submissions Question
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

Hide Tags
*/

public class Solution {
    public int minCut(String s) {
        return help1(s);
    }
    
    // https://leetcode.com/discuss/76411/easiest-java-dp-solution-97-36%25
    // dp[i][j]: minimum cut to make it palindrom from postion i to position j
    // dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + 1) when isPalindrom[i][k] && isPalindrom[k][j]
    private int help1(String s)
    {
        if(s == null || s.length() == 0) return 0;
        
        int[] cut = new int[s.length()];
        boolean[][] pat = new boolean[s.length()][s.length()];
        
        //calculatePalindrom(s, isPalindrom);
        for(int i = 0; i < s.length(); i++)
        {
            int min = i;
            for(int j = 0; j <= i; j++)
            {
                if(s.charAt(i) == s.charAt(j) && (i -j < 2 || pat[i-1][j+1]))
                {
                    pat[i][j] = true;
                    min = j ==0? 0 :Math.min(min, cut[j-1]+1);
                }
            }
            cut[i] = min;
        }
        
        return cut[s.length()-1];
    }
    
    private void calculatePalindrom(String s, boolean[][] isPalindrom)
    {
        // palindrom[i][j] = 1 if substring from char i to char j is palindrom
        int len = s.length();
        for(int i = 0; i < len; i++)
        {
            isPalindrom[i][i] = true;
        }
        
        for(int i = 0; i < s.length(); i++)
        {
            for(int j = i + 1; j < s.length(); j++)
            {
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalindrom[i+1][j-1]))
                {
                    isPalindrom[i][j] = true;
                }
            }
        }
    }
}