/*
Number of 1 Bits My Submissions Question
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/
public class Solution {
    
        public int hammingWeight(int n) {
        int ans = 0;
        
        while ( n != 0){
            n &= (n-1);
            ans++;
        }
        /*
        // we can't use n > 0, as LeetCode compiler somehow interprets the n as signed integer 
        while (n != 0) {
            //ans += n & 1;
            // & precedence is lower than ==, hence we can't write n & 1 == 1, which is equivalent n & true
            if ( (n & 1) == 1){
                ans++;
            }
            n >>>= 1; // logical right shift, does not treat the first bit as sign, all fill in 0
        }
        */
        return ans;
    }

}