/*
Best Time to Buy and Sell Stock with Cooldown My Submissions Question
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
*/

public class Solution {
    public int maxProfit(int[] prices) {
        return helper1(prices);
    }
    
    public int helper1(int[]prices){
        // buy[i]: the maximum profit for day i with last operation as buy, and the buy does not need hapen at day i, [0,...,i]
        // sell[i]: the maximum profit for day i with last operation as sell
        // buy[i] = max(buy[i-1], sell[i-2] - prices[i])
        // sell[i] = max(sell[i-1], buy[i-1] + prices[i])
        if(prices.length <= 1) return 0;
        
        int b_2 = -prices[0];
        int b_1 = -prices[0];
        int s_2 = 0;
        int s_1 = 0;
        int s = 0;
        int b = 0;
        for(int i = 1; i < prices.length; i++){
            b = Math.max(b_1, s_2 - prices[i]);
            s = Math.max(s_1, b_1 + prices[i]);
            b_2 = b_1;
            b_1 = b;
            s_2 = s_1;
            s_1 = s;
        }
        
        return s;
    }
}