/*
151. Reverse Words in a String My Submissions Question
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/

public class Solution {
    public String reverseWords(String s) {
        return helper(s);
    }
    
    private String helper(String s){
        if(s == null || s.length()==0){
            return "";
        }
        
        String[] temp = s.split("\\s+");
        //we need skip the empty ones
        int len = 0;
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i].length() == 0){
                continue;
            }else
            {
                temp[len++] = temp[i];
            }
        }
        if(len == 0){
            return "";
        }
        
        for(int i = 0, j = len-1; i <j; ){
            String t = temp[i];
            temp[i] = temp[j];
            temp[j] = t;
            i++;
            j--;
        }
        
        StringBuilder res = new StringBuilder();
        for(int i = 0; i<len-1; i++){
            res.append(temp[i]+" ");
        }
        res.append(temp[len-1]);
        return res.toString();
    }
}