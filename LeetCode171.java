/*
Excel Sheet Column Number My Submissions Question
Total Accepted: 55444 Total Submissions: 142194 Difficulty: Easy
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
*/	

public class Solution {
    public int titleToNumber(String s) {
        if(s  == null || s.length() == 0){
            return 0;
        }
        
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            ans = (c - 'A' + 1)  + ans * 26;
        }
        return ans;
    }
}