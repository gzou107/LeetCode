/*
188. Best Time to Buy and Sell Stock IV My Submissions Question
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/

public class Solution {

    public int maxProfit(int k, int[] prices) {
        return help0(k, prices);
    }
    // https://leetcode.com/discuss/57669/understanding-easily-modified-different-situations-solution
    private int help0(int k, int [] prices)
    {
        if(prices == null || prices.length <= 1) return 0;
        
        if(k > prices.length/2){
            int max = 0;
            for(int i = 0; i < prices.length-1; i++)
            {
                max += Math.max(prices[i+1] - prices[i], 0);
            }
            return max;
        }
        
        int [][]hold = new int[k+1][prices.length];
        // hold[i][j]: at most i transactions on day j, with one stock in hand
        int [][]unhold = new int[k+1][prices.length];
        // unhold[i][j]: at most i transations on day j, withOUT one stock in hand
        
        for(int i = 1; i <= k; i++)
        {
            hold[i][0] = -prices[0];
            unhold[i][0] = 0;
            for(int j = 1; j < prices.length; j++)
            {
                hold[i][j] = Math.max(hold[i][j-1], unhold[i-1][j] - prices[j]);//not buy, buy
                unhold[i][j] = Math.max(unhold[i][j-1], hold[i][j-1] + prices[j]);// not sell, sell
            }
        }
        
        return unhold[k][prices.length-1];
    }
}