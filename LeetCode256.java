/*
256. Paint House My Submissions Question
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

public class Solution {
    public int minCost(int[][] costs) {
        return help0(costs);
       //return help(costs);
    }
    
    private int help0(int [][] costs)
    {
        // we defines the candidate[i][j] by minimum total costs of i+1 house, with the ith house painted with color j, and j can be 0, 1, and 2
        //  and we know candidate[i][j] = costs[i][j] + Math.min(candidate[i][k]) where k does not equals to j
        // and the min cost is totalCost[i] = Math.min(candidate[i][j]) for j = 0, 1 and 2
        // notice that we only need track 3 variables in 2 stages, hence we can use 7 variables here
        
        if(costs == null || costs.length == 0) return 0;
        int n = costs.length;
         
        int [] prev = new int[3];
        int [] curr = new int[3];
        
        // now we handle the rest
        for(int i = 0; i < n; i++)
        {
            curr[0] = costs[i][0] + Math.min(prev[1], prev[2]);
            curr[1] = costs[i][1] + Math.min(prev[0], prev[2]);
            curr[2] = costs[i][2] + Math.min(prev[0], prev[1]);
            /* it turns out the clone and arraycopy are both slower than value assignment
            prev = curr.clone();
            System.arraycopy(curr, 0, prev, 0, 3);
            */
            prev[0] = curr[0];
            prev[1] = curr[1];
            prev[2] = curr[2];
            
        }
        
        return Math.min(prev[0], Math.min(prev[1], prev[2]));
    }
    
    private int help(int [][] costs)
    {
        // we defines the candidate[i][j] by minimum total costs of i+1 house, with the ith house painted with color j, and j can be 0, 1, and 2
        //  and we know candidate[i][j] = costs[i][j] + Math.min(candidate[i][k]) where k does not equals to j
        // and the min cost is totalCost[i] = Math.min(candidate[i][j]) for j = 0, 1 and 2
        
        if(costs == null || costs.length == 0) return 0;
        int n = costs.length;
         
        int[][]candidates = new int[n][3];
        int [] totalMinCost = new int[n];
        
       
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++)
        {
            candidates[0][i] = costs[0][i];
            min = Math.min(min, candidates[0][i]);
        }
        totalMinCost[0] = min;
        
        // now we handle the rest
        for(int i = 1; i < n; i++)
        {
            candidates[i][0] = costs[i][0] + Math.min(candidates[i-1][1], candidates[i-1][2]);
            candidates[i][1] = costs[i][1] + Math.min(candidates[i-1][0], candidates[i-1][2]);
            candidates[i][2] = costs[i][2] + Math.min(candidates[i-1][0], candidates[i-1][1]);
            totalMinCost[i] = Math.min(candidates[i][0], Math.min(candidates[i][1], candidates[i][2]));
        }
        
        return totalMinCost[n-1];
    }
}