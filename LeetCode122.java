/*
122. Best Time to Buy and Sell Stock II My Submissions Question
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

public class Solution {
    public int maxProfit(int[] prices) {
        return help(prices);
    }
    
    private int help(int[]prices)
    {
        int max = 0;
        for(int i = 1; i < prices.length; i++)
        {
            max += prices[i] > prices[i-1] ? prices[i] - prices[i-1] : 0;
        }
        return max;
    }
}