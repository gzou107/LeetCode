/*
Valid Palindrome My Submissions Question
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/
public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return false;
        if(s.length() == 0) return true;
        
        int l = 0;
        int h = s.length()-1;
        
        while(l < h){
            while(l < h && !Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }
            
            while( h > l && !Character.isLetterOrDigit(s.charAt(h))){
                h--;
            }
            
            if(l == h) 
            {
                return true;
            }
            else if(l < h)
            {
                char lc = s.charAt(l);
                char hc = s.charAt(h);
                if(Character.toLowerCase(lc) != Character.toLowerCase(hc)){
                    return false;
                }
                l++;
                h--;
            }
        }
        
        return true;
    }
}