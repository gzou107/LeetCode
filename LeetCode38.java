/*
Count and Say My Submissions Question
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/
public class Solution {
    public String countAndSay(int n) {
        
        // assumign n should be positive (n > 0)
        // and also this always assume the seed is 1
        if( n <= 0){
            return "";
        }
        
        String ans = "1";
       
        int i = 1;
        StringBuilder temp = new StringBuilder();
        while(i < n)
        {
            int start = 0;
            int end = 0;
            while(end < ans.length())
            {
                while(end < ans.length() && ans.charAt(end) == ans.charAt(start)){
                    end++;
                }
                
                temp.append(String.valueOf(end-start) + ans.charAt(start));
                start = end;
            }
            i++;
            ans = temp.toString();;
            temp.setLength(0);
        }
        
        return ans;
    }
}