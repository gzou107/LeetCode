/*
Implement strStr() My Submissions Question
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Subscribe to see which companies asked this question
*/

public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack == null){
            if(needle == null) return 0;
            return -1;
        }
        
        if(haystack.length()==0){
            if(needle.length()==0) return 0;
            return -1;
        }
        
        if(haystack.length() < needle.length()) return -1;
        
        for(int i = 0; i <= haystack.length() - needle.length(); i++){
            int j = 0;
            while(j < needle.length() && needle.charAt(j) == haystack.charAt(i+j)){
                j++;
            }
            
            if(j == needle.length()){
                return i;
            }
        }
        
        return -1;
    }
}