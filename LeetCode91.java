/*
91. Decode Ways My Submissions Question
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

public class Solution {
    public int numDecodings(String s) {
        return help0(s);
        //return help(s);
    }
    
    private int help0(String s)
    {
      if(s == null || s.length() == 0 || s.startsWith("0")) return 0;
      
      int t_2 = 1;
      int t_1 = 1;
      int t = 1;
      for(int i = 2; i <= s.length(); i++)
      {
          int val = Integer.valueOf(s.substring(i-2, i));
          int twoStepsBehind = val>9 && val <=26 ? t_2 : 0;
          int val1 = Integer.valueOf(s.substring(i-1, i));
          int oneStepBehind = val1 !=0 ? t_1 : 0;
          t = twoStepsBehind + oneStepBehind;
          t_2 = t_1;
          t_1 = t;
      }
      return t;    
    }
    
    private int help(String s)
    {
      if(s == null || s.length() == 0 || s.startsWith("0")) return 0;
      
      int[] dp = new int[s.length()+1];
      dp[0] = 1;
      dp[1] = 1;
      for(int i = 2; i <= s.length(); i++)
      {
          int val = Integer.valueOf(s.substring(i-2, i));
          int twoStepsBehind = val>9 && val <=26 ? dp[i-2] : 0;
          int val1 = Integer.valueOf(s.substring(i-1, i));
          int oneStepBehind = val1 !=0 ? dp[i-1] : 0;
          dp[i] = twoStepsBehind + oneStepBehind;
      }
      return dp[s.length()];    
    }
}