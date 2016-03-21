/*
10. Regular Expression Matching My Submissions Question
Total Accepted: 74297 Total Submissions: 341592 Difficulty: Hard
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

public class Solution {
    public boolean isMatch(String s, String p) {
       return help(s, p);
    }
    
    public boolean help(String s, String p)
    {
        if(p.isEmpty()){
            return s.isEmpty();
        }
        
        // now p is NOT empty
        // two cases to consider, p[1] == "*" or not
        if(p.length() == 1 || p.charAt(1) != '*')
        {
            if(s.isEmpty() || (s.charAt(0) != p.charAt(0) && p.charAt(0) !='.'))
            {
                return false;
            }
            // matched one 1 character, hence move to next chars
            return help(s.substring(1), p.substring(1));
        }
        
        // when reaching here p.length() >= 2 && p.charAt(1) == '*'
        // now p[1] == '*', and it can match 0, 1 or more precedent chars
        while(!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && p.charAt(1) == '*')
        {
            if(help(s, p.substring(2))){
                return true;
            }
            
            s = s.substring(1);
        }
        
        return help(s, p.substring(2));
    }
}