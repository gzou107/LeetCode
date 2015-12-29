/*
322. Coin Change My Submissions Question
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

public class Solution {
    public int coinChange(int[] coins, int amount) {
       // return helper1(coins, amount);
       // return helper2(coins, amount);
       
       if(amount <=0) return 0;
       return helper2(coins, amount, new int[amount]);
    }
    
    private int helper2(int [] coins, int amount){
          if(amount<1) return 0;
            int L = coins.length;
            int[] dp = new int[amount+1];
            int sum = 0;
        
            while(++sum<=amount)
            {
                int min = -1;
                for(int coin : coins) 
                {
                    if(sum >= coin && dp[sum-coin]!=-1) 
                    {
                        int temp = dp[sum-coin]+1;
                        min = min<0 ? temp : (temp < min ? temp : min);
                    }
                }
                dp[sum] = min;
            }
            
            return dp[amount];
    }
    
    private int helper1(int[] coins, int amount){
        if(amount <0) return -1;
        if(amount == 0) return 0;
        if(coins == null || coins.length <1) return -1;
        
        int [] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        
        int amt = 0;
        while(amt <= amount)
        {
            for(int coin : coins)
            {
               if(amt>=coin && dp[amt] > dp[amt-coin] + 1 )
               {
                   dp[amt] = dp[amt-coin] + 1;
               }
            }
            amt++;
        }
        
        return dp[amount] == amount+1?-1:dp[amount];
    }
     
    private int helper2(int [] coins, int rem, int [] count){
        if(rem < 0) return -1;
        if(rem == 0) return 0;
        if(count[rem-1] != 0)  return count[rem-1];
        
        int min = Integer.MAX_VALUE;
        for(int coin : coins){
            int counter = helper2(coins, rem - coin, count);
            if(counter >=0 && counter < min){
                min = 1 + counter;
            }
        }
         count[rem-1] = (min == Integer.MAX_VALUE? -1: min);
         
         return count[rem-1];
    }
}