/*
69. Sqrt(x) My Submissions Question
Implement int sqrt(int x).

Compute and return the square root of x.
*/

public class Solution {
    public int mySqrt(int x) {
        return help(x);
    }
    
    public int help(int x)
    {
        if(x < 0) return -1;
        if(x <= 1) return x;
        
        int l = 1;
        int r = x;
        
        while( l <r)
        {
            int m = l + ((r-l + 1) >> 1);
            
            if(x/m == m){
                return m;
            }else if(x/m < m){
                r = m - 1;
            }else{
                l = m; //as we may not change l, to make sure no infinite loop, we update the formula for m
            }
        }
        return l;
    }
}