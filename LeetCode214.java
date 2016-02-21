/*
214. Shortest Palindrome My Submissions Question
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Thanks to @Freezen for additional test cases.
*/

public class Solution {
  
    public String shortestPalindrome(String s) {
        return help(s);
    }
    
    private String help(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        String rev = (new StringBuilder(s)).reverse().toString();
        String comb = s + "#" + rev; // itself + reverse !!! order matters
        int [] next = new int[comb.length()];
        getNext(comb, next);
        int max = next[next.length-1];
        return rev.substring(0, rev.length()- max) + s;
    }
    //https://segmentfault.com/a/1190000003797346
    private void getNext(String s, int[] next)
    {
         // i是后缀末尾的指针，j是前缀末尾的指针
        int i = 1; 
        int j = 0;
        while(i < s.length())
        {
            if(s.charAt(i) == s.charAt(j))
            {
                next[i] = j + 1;
                i++;
                j++;
            }else{
                // 如果前缀末尾指针还没退回0点，则找上一个子前缀的末尾位置
                if(j != 0){
                    j = next[j-1];
                }else{
                    // 如果退回0点，则最长相同前后缀的长度就是0了
                    next[i] = 0;
                    i++;
                }
            }
        }
    }
}