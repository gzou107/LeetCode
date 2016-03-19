/*
44. Wildcard Matching My Submissions Question
Total Accepted: 52978 Total Submissions: 308386 Difficulty: Hard
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

public class Solution {
    public boolean isMatch(String s, String p) {
        return help(s, p);
    }
    
    private boolean help(String s, String p)
    {
        int sIndex = 0;
        int pIndex = 0;
        int pStar = -1;
        int match = -1;
        
        while(sIndex < s.length())
        {
            if(pIndex < p.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) =='?'))
            {
                sIndex++;
                pIndex++;
            }else if(pIndex < p.length() && p.charAt(pIndex) == '*')
            {
                pStar = pIndex;
                pIndex++;
                match = sIndex;
            }else if(pStar != -1)
            {
                pIndex = pStar + 1;
                sIndex = match+1;
                match++;
            }else{
                return false;
            }
        }
        
        while(pIndex < p.length() && p.charAt(pIndex) == '*')
        {
            pIndex++;
        }
        
        return pIndex == p.length();
    }
}