/*
3. Longest Substring Without Repeating Characters My Submissions Question
Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        return help(s);
    }
    
    private int help(String s)
    {
        if(s == null || s.length() == 0) return 0;
        
        int [] pos = new int [256];// the last time this element appears
        Arrays.fill(pos, -1);
        int max = 0;
        int start = 0;
        
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            
            if(pos[c] >= start) // we need calculate the maximum distance if it has appeared before
            {
                 max = Math.max(max, i - start);
                 start = pos[c] + 1;
            }
            
            pos[c] = i;
        }
        
        return Math.max(max, s.length() - start);
    }
}