/*
161. One Edit Distance My Submissions Question
Given two strings S and T, determine if they are both one edit distance apart.
*/

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        return help0(s, t);
        //return help(s, t);
    }
    private boolean help0(String s, String t)
    {
        int len = Math.min(s.length(), t.length());
        for(int i = 0; i < len; i++)
        {
            if(s.charAt(i) != t.charAt(i)){
                if(s.length() == t.length()){
                    return s.substring(i+1).equals(t.substring(i+1));
                }else if(s.length() < t.length()){
                    return s.substring(i).equals(t.substring(i+1));
                }else{
                    return s.substring(i+1).equals(t.substring(i));
                }
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
    // equal does not satisfy the requirement
    private boolean help(String s, String t)
    {
        if(s == null) return t != null && t.length() == 1;
        if(t == null) return s != null && s.length() == 1;
        
        int sLen = s.length();
        int tLen = t.length();
        if(Math.abs(sLen - tLen) > 1) return false;
 
        if(sLen == tLen){
            return isReplace(s, t);
        }
        else if(sLen < tLen)
        {
            return isInsert(s, t);
        }else{
            return isInsert(t, s);
        }
    }
    
    private boolean isReplace(String s, String t)
    {
           int diff = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != t.charAt(i)){
                    if(diff == 1){
                        return false;
                    }else{
                        diff++;
                    }
                }
            }
            return diff == 1;
    }
    private boolean isInsert(String shortS, String longS)
    {
        int i = 0;
        while(i < shortS.length() && shortS.charAt(i) == longS.charAt(i)){
            i++;
        }
        
        return shortS.substring(i).equals(longS.substring(i+1));
        /*
        if(i == shortS.length()) return true;
        
        for(; i < shortS.length(); i++)
        {
            if(shortS.charAt(i) != longS.charAt(i+1)){
                return false;
            }
        }
        return true;
        */
    }
}