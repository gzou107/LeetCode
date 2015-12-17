/*
Palindrome Number My Submissions Question
Determine whether an integer is a palindrome. Do this without extra space.
*/
public class Solution {
    public boolean isPalindrome(int x) {
         return helper1(x);
        
       // return helper2(x);
    }
    
    public boolean helper1(int x){
        if(x < 0) return false;
        
        // first find the divisor to reach left
        int div = 1;
        while(x/div > 9){
            div *= 10;
        }
        
        while(x > 0){
            int left = x/div;
            int right = x%10;
            if(left != right){
                return false;
            }
            
            x = (x%div)/10;
            div /= 100;
        }
        
        return true;
    }
    
    public boolean helper2(int x){
       if(x<0){
            return false;
        }
        
        if(x < 10){
            return true;
        }
        
        String t = String.valueOf(x);
        int i = 0;
        int j = t.length()-1;
        while(i < j){
            if(t.charAt(i++) != t.charAt(j--)){
                return false;
            }
        }
        
        return true;
    }
}