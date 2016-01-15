/*
120. Triangle My Submissions Question
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/


public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return help0(triangle);
        //return help1(triangle);
    }
    
    private int help0(List<List<Integer>> t)
    {
      int [] dp = new int[t.size() + 1];
      
      for(int i = t.size()-1; i >=0; i--)
      {
          for(int j = 0; j < i + 1; j++)
          {
              dp[j] = Math.min(dp[j], dp[j+1]) + t.get(i).get(j);
          }
      }
      
      return dp[0];
    }
    
    private int help1(List<List<Integer>> t)
    {
        if(t == null || t.size() == 0) return 0;
        
        int [] curr = new int[t.size()];
        int [] prev = new int[t.size()];
        for(int i = 0; i < t.size(); i++)
        {
            prev[i] = t.get(t.size()-1).get(i);
        }
      
        for(int i = t.size() -2; i>=0; i--)
        {
            for(int j = 0; j < i+1; j++)
            {
                curr[j] = t.get(i).get(j) + Math.min(prev[j], prev[j+1] );
            }
            prev = curr.clone();
        }
        return prev[0];
        
    }
}