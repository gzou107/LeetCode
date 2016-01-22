/*
265. Paint House II My Submissions Question
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/

public class Solution {
    public int minCostII(int[][] costs) {
        return help2(costs);
        //return help0(costs);
        //return help(costs);
    }
    // O(N*K)
    private int help2(int[][]costs)
    {
        if(costs == null || costs.length == 0) return 0;
        
        int n = costs.length;
        int k = costs[0].length;
        
        int[] dp = new int[k];
        int min = 0;
        int secondMin = 0;
        
        for(int i = 0; i <n; i++)
        {
            int pMin = min;
            int pSecondMin = secondMin;
            
            min = secondMin = Integer.MAX_VALUE;
                
            for(int j = 0; j < k; j++)
            {
                dp[j] = (dp[j] == pMin ? pSecondMin : pMin) + costs[i][j];
                    
                if(min <= dp[j]){
                    secondMin = Math.min(dp[j], secondMin);
                }else
                {
                    secondMin = min;
                    min = dp[j];
                }
            }
        }

        return min;
    }
    
    //O(N*K*K)
    private int help(int[][]costs)
    {
        if(costs == null || costs.length == 0) return 0;
        
        if(costs.length == 1)
        {
            int min = costs[0][0];
            for(int i = 0; i < costs[0].length; i++){
                min = Math.min(min, costs[0][i]);
            }
            return min;
        }
        
        int n = costs.length;
        int k = costs[0].length;
        
        int[] prev = new int[k];
        int[] curr = new int[k];
        
        for(int i = 0; i <n; i++)
        {
            for(int j = 0; j < k; j++)
            {
                    int min = Integer.MAX_VALUE;
                    for(int c = 0; c < k; c++)
                    {
                        if(c == j) continue;
                        min = Math.min(min, prev[c]);
                    }
                    curr[j] = costs[i][j] + min;
            }
            
            for(int j = 0; j < k; j++)
            {
                prev[j] = curr[j];
            }
        }
        
        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++)
        {
            minCost = Math.min(minCost, prev[i]);
        }
        return minCost;
    }
}