/*
Isomorphic Strings My Submissions Question
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        
        if(s == null){
            return t == null;
        }
        
        if( t == null){
            return false;
        }
        
        Map<Character, Character> c2cMapping = new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            char ct = t.charAt(i);
            if(c2cMapping.containsKey(c))
            {
                if(c2cMapping.get(c) != ct)
                {
                    return false;
                }
            }else if(c2cMapping.containsValue(ct)) // make sure no two chars mapping to the same char
            {
                return false;
            }else
            {
                c2cMapping.put(c, ct);
            }
        }
        
        return true;
    }
}