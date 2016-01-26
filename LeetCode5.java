/*
5. Longest Palindromic Substring My Submissions Question
Total Accepted: 89770 Total Submissions: 405781 Difficulty: Medium
Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

public class Solution {
    public String longestPalindrome(String s) {
        //return help(s);
        return help1(s);
    }
    
    private String help(String s)
    {
        if(s == null || s.length() == 0) return "";
        
        int max = 1; // inital set to 1 as at least length is 1
        int start = 0;
        boolean[][]pal = new boolean[s.length()][s.length()];
        for(int i = 0; i <s.length(); i++)
        {
            pal[i][i] = true;
            for(int j = 0; j < i; j++)
            {
                pal[i][j] = s.charAt(i) == s.charAt(j) && (i - j < 2 || pal[i-1][j+1]);
                
                if(pal[i][j] && max < i - j + 1){
                    max = i - j + 1;
                    start = j;
                }
            }
        }
        
        return s.substring(start, start + max);
    }
    
    private String help1(String s)
    {
        if( s == null || s.length() == 0) return "";
        
        String max = "";
        
        for(int i = 0; i < s.length(); i++)
        {
            String can1 = expand(s, i, i);
            if(can1.length() > max.length()){
                max = can1;
            }
            
            String can2 = expand(s, i, i + 1);
            if(can2.length() > max.length()){
                max = can2;
            }
        }
        
        return max;
    }
    
    private String expand(String s, int l, int r)
    {
        int n = s.length();
        
        while(l >=0 && r < n && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        
        return s.substring(l+1, r);
    }
}