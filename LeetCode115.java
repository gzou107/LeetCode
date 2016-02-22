/*
115. Distinct Subsequences My Submissions Question
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

public class Solution {
    public int numDistinct(String s, String t) {
        
        return help0(s, t);
    }
    // https://leetcode.com/discuss/26680/easy-to-understand-dp-in-java
    /*
    As a typical way to implement a dynamic programming algorithm, we construct a matrix dp, where each cell dp[i][j] represents the number of solutions of aligning substring T[0..i] with S[0..j];

    Rule 1). dp[0][j] = 1, since aligning T = "" with any substring of S would have only ONE solution which is to delete all characters in S.
    
    Rule 2). when i > 0, dp[i][j] can be derived by two cases:
    
    case 1). if T[i] != S[j], then the solution would be to ignore the character S[j] and align substring T[0..i] with S[0..(j-1)]. Therefore, dp[i][j] = dp[i][j-1].
    
    case 2). if T[i] == S[j], then first we could adopt the solution in case 1), but also we could match the characters T[i] and S[j] and align the rest of them (i.e. T[0..(i-1)] and S[0..(j-1)]. As a result, dp[i][j] = dp[i][j-1] + d[i-1][j-1]
   */
   
    private int help0(String s, String t)
    {
        // s contains t
        int[][] dp = new int[t.length()+1][s.length()+1];
        
        for(int i = 0; i <=s.length(); i++)
        {
            dp[0][i] = 1;
        }
        
        for(int i = 0; i < t.length(); i++)
        {
            for(int j = 0; j < s.length(); j++)
            {
                if(t.charAt(i) == s.charAt(j))
                {
                    dp[i+1][j+1] = dp[i+1][j] + dp[i][j];
                }else{
                    dp[i+1][j+1] = dp[i+1][j];
                }
            }
        }
        
        return dp[t.length()][s.length()];
    }
}