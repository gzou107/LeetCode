/*
123. Best Time to Buy and Sell Stock III My Submissions Question
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Show Tags
Show Similar Problems
*/

public class Solution {
    public int maxProfit(int[] prices) {
        return help(prices);
    }
    
    private int help(int[] prices)
    {
        if(prices == null || prices.length == 0) return 0;
        
        //define left[i] to be the profit we can get by visiting from left of the array to i, with i inclusive
        //define right[i] to be the profit we can get by visiting from right of the array to i, with i inclusive
        // we will find the max of sum of left[i] and right[i]
        int []left = new int[prices.length];
        int []right = new int[prices.length];
        
        left[0] = 0;
        int min = prices[0];
        int max = 0;
        for(int i = 1; i < prices.length; i++)
        {
            if(prices[i] < min){
                min = prices[i];
            }else{
                max = Math.max(prices[i] - min, max);
            }
            left[i] = max;
        }
        
        right[prices.length-1] = 0;
        int rMax = prices[prices.length-1];
        max = 0;
        for(int i = prices.length-2; i >=0; i--)
        {
            if(prices[i] > rMax){
                rMax = prices[i];
            }else{
                max = Math.max(max, rMax - prices[i]);
            }
            right[i] = max;
        }
        
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++)
        {
            if(res < left[i] + right[i]){
                res = left[i] + right[i];
            }
        }
        return res;
        
    }
}