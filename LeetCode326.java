/*
326. Power of Three My Submissions Question
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
*/

public class Solution {
    public boolean isPowerOfThree(int n) {
        return helper0(n);
      //  return helper(n);
    }
    
    private boolean helper0(int n)
    {
        if(n <= 0) return false;
        
        return Integer.toString(n, 3).matches("10*");
    }
    
    private boolean helper(int n)
    {
        if(n < 1) return false;
        
        while(n % 3 == 0)
        {
            n = n/3;
        }
        
        return n == 1;
    }
}