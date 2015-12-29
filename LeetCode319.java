/*
319. Bulb Switcher My Submissions Question
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
*/

public class Solution {
    public int bulbSwitch(int n) {
        return helper1(n);
    }
    
    private int helper1(int n){
        // all starting with off (0), 
        // for 5 bulbs, it turned on the first,
        // 00000 <-- initial state
        // 11111  <-- 1
        // 10101  <-- 2
        // 10001  <-- 3
        // 10011  <---4
        // 10010  <---5
        // hence for every position, we count how many divisors it has beteen [1, n]; if it has odd divisors, then it will be on
        //   otherwise it will be off.
        // for example, postion [1,2,3,4,5]. n = 5, position 4 has 1,2,4, three divisors, and it will be 1(flip 3 times)
        // for n = 25, it will be flipped at 1,5,25
        // for n = 8, it will be flipped at 1,2,4,8, hence will be off
        // we will count all the ones with odd number of divisors
        // the trick is ONLY the squares (1^2, 2^2,3^3 has odd number of divisors!!!), all the rest are not
        if(n <= 0) return 0;
        int count = 0;
        int i = 1;
        while(i*i <= n){
            count++;
            i++;
        }
        
        return count;
    }

}