/*
Power of Two My Submissions Question
Given an integer, write a function to determine if it is a power of two.
*/
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n-1) == 0;
    }
}