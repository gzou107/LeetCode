/*
Length of Last Word My Submissions Question
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/
public class Solution {
    public int lengthOfLastWord(String s) {
        
        if( s== null || s.length() <= 0){
            return 0;
        }
        
        int start = s.length() - 1;
        while(start >=0 && !Character.isLetter(s.charAt(start))){
            start--;
        }
        
        if(start < 0) return 0;
        int end = start - 1;
        while(end >=0 && Character.isLetter(s.charAt(end))){
            end--;
        }
        // end will always point to an invalid postion
        return start - end;
        
    }
}