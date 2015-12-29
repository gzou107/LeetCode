/*
201. Bitwise AND of Numbers Range My Submissions Question
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/

public class Solution {
    // we just need find the common bits of m and n
    public int rangeBitwiseAnd(int m, int n) {
        return helper1(m,n);
       // return helper2(m, n);
    }
    
    private int helper1(int m, int n){
        int mask = Integer.MAX_VALUE;
        
        while((mask&m) != (mask & n))
        {
            mask <<= 1;
        }
        
        return mask&m;
    }
    
    private int helper2(int m, int n){
        
        int i = 0;
        while ( m != n){
            m >>= 1;
            n >>= 1;
            i++;
        }
        
        return m<<i;
    }
}