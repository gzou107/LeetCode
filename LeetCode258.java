/*
Add Digits My Submissions Question
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
*/
public class Solution {
    //  \mbox{dr}(n) = 1\ +\ ((n-1)\ {\rm mod}\ 9).\ 
    public int addDigits(int num) {
        // or use the congruence formula
        return (num - 1)%9 + 1;
        /*
        int ans = getSumOfDigits(num);
        while(ans >9){
            ans = getSumOfDigits(ans);
        }
        return ans;
        */
    }
    
    private static int getSumOfDigits(int val){
        int ans = 0;
        while(val > 0){
            ans += val%10;
            val /= 10;
        }
        return ans;
    }
}