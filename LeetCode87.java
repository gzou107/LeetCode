/*
87. Scramble String My Submissions Question
Total Accepted: 43886 Total Submissions: 167218 Difficulty: Hard
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

public class Solution {
    public boolean isScramble(String s1, String s2) {
        return dfs(s1,s2);
    }
    
    private boolean dfs(String s1, String s2)
    {
        int len = s1.length();
        
        if(len == 1){
            return s1.equals(s2);
        }
        
        char [] ch1 = s1.toCharArray();
        Arrays.sort(ch1);
        String s1Sorted = new String(ch1);
        
        char [] ch2 = s2.toCharArray();
        Arrays.sort(ch2);
        String s2Sorted = new String(ch2);
        
        if(!s1Sorted.equals(s2Sorted)){
            return false;
        }
        
        for(int i =1 ; i < len; i++)
        {
            if(dfs(s1.substring(0, i), s2.substring(0, i)) && dfs(s1.substring(i), s2.substring(i))){
                return true;
            }
            
            if(dfs(s1.substring(0, i), s2.substring(len-i)) && dfs(s1.substring(i), s2.substring(0, len-i))){
                return true;
            }
        }
        
        return false;
    }
}