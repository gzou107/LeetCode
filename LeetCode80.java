/*
Climbing Stairs My Submissions Question
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class Solution {
    public int climbStairs(int n) {
        // f(n) =  f(n - 1) + f(n-2);
        
        if(n <= 0) return 0;
        if(n <= 2) return n;
        
        int fN_2 = 1;
        int fN_1 = 2;
        int fN = 0;
        
        int i = 2;
        while( i < n){
            fN = fN_2 + fN_1;
            fN_2 = fN_1;
            fN_1 = fN;
            i++;
        }
        
        return fN;
    }
}