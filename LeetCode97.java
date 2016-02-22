/*
97. Interleaving String My Submissions Question
Total Accepted: 45570 Total Submissions: 206680 Difficulty: Hard
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        //return help(s1, s2,s3);
        return help1(s1,s2,s3);
    }
    
    private boolean help0(String s1, String s2, String s3)
    {
        if(s1 == null) return s2==null? s3== null : s2.equals(s3);
        if(s2 == null) return s1== null? s3 == null : s1.equals(s3);
        
        if(s1.length() + s2.length()!= s3.length()) return false;
        boolean[][]invalid = new boolean[s1.length()+1][s2.length()+1];
        return dfs(s1, s2, s3, 0, 0, 0, invalid);
    }
    // https://leetcode.com/discuss/75138/1ms-tiny-dfs-beats-94-57%25
    private boolean dfs(String s1, String s2, String s3, int i, int j, int k, boolean[][] invalid)
    {
        if(invalid[i][j]) return false;
        if(k == s3.length()) return true;
        
        boolean isValid = (i < s1.length() && s1.charAt(i) == s3.charAt(k) && dfs(s1,s2,s3,i+1, j, k+1, invalid))||(j < s2.length() && s2.charAt(j) == s3.charAt(k) && dfs(s1,s2,s3, i, j+1, k+1, invalid));
        
        invalid[i][j] = !isValid;
        return isValid;
    }
    
    // https://leetcode.com/discuss/11694/my-dp-solution-in-c
    private boolean help1(String s1, String s2, String s3)
    {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        
        boolean[][]valid = new boolean[s1.length()+1][s2.length()+1];
        for(int i = 0; i < s1.length()+1; i++)
        {
            for(int j = 0; j < s2.length()+1; j++)
            {
                if(i==0 && j == 0){
                    valid[i][j] = true;
                }else if(i == 0){
                    valid[i][j] = valid[i][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
                }else if(j == 0){
                    valid[i][j] = valid[i-1][j] && s1.charAt(i-1) == s3.charAt(i-1);
                }else{
                    valid[i][j] = (valid[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)) || (valid[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1));
                }
            }
        }
        return valid[s1.length()][s2.length()];
    }
}