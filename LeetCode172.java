/*
Factorial Trailing Zeroes My Submissions Question
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/
public class Solution {
    public int trailingZeroes(int n) {
        if( n < 5) return 0;
        return trailingZerosFrom5(n);
    }
    
    private static int trailingZerosFrom5(int n){
        int ans = 0;
        while (n >=5){
            ans += n/5;
            n /= 5;
        }
        return ans;
    }
    
    // an unnecessry facier version
    /*
    public int trailingZeroes(int n) {
	if (n < 0)
		return -1;
 
	int count = 0;
	for (long i = 5; n / i >= 1; i *= 5) {
		count += n / i;
	}
 
	return count;
   }
   */
}