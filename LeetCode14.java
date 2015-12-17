/*
Longest Common Prefix My Submissions Question
Write a function to find the longest common prefix string amongst an array of strings.
*/

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        
        for(int i = 0; i < strs[0].length(); i++)
        {
            char c = strs[0].charAt(i);
            int j = 1;
            
            for(; j < strs.length; j++){
                // watch out for ==  here
                if(i >= strs[j].length() || c != strs[j].charAt(i)){
                    break;
                }
            }
            //
            if(j != strs.length){
                return strs[0].substring(0,i); // sustring(start,end), [start, end) with end exclusive
            }
        }
        
        return strs[0];
    }
}