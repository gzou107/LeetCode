/*
221. Maximal Square My Submissions Question
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/

public class Solution {
    public int maximalSquare(char[][] matrix) {
        //return helper1(matrix);
        
        return helper2(matrix);
    }
    
    public int helper1(char [][] matrix){
        if(matrix == null) return 0;
        if(matrix.length == 0) return 0;
        
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];
        int max = 0;
        for(int i = 0; i < c; i++){
            dp[0][i] = (matrix[0][i] == '1'? 1 : 0);
            if(dp[0][i] == 1){
                max = 1;
            }
        }
        
        for(int i = 0; i < r; i++){
               dp[i][0] = (matrix[i][0] == '1'? 1 : 0);
               if(dp[i][0] == 1){
                   max = 1;
               }
        }
        
        
        for(int i = 1; i < r; i++){
            for(int j =  1; j < c; j++){
                if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = Math.max(1, Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j]) ) + 1 );
                    
                    if(max < dp[i][j] * dp[i][j]){
                        max = dp[i][j] * dp[i][j];
                    }
                }
            }
        }
        
        return max;
    }
    
     public int helper2(char [][] matrix){
        if(matrix == null) return 0;
        if(matrix.length == 0) return 0;
        
        int r = matrix.length;
        int c = matrix[0].length;
        int[] prev = new int[c];
        int[] curr = new int[c];
        int max = 0;
        for(int i = 0; i < c; i++)
        {
            prev[i] = (matrix[0][i] == '1'? 1 : 0);
            if(max < prev[i])
            {
                max = prev[i];
            }
        }
        
        for(int i = 1; i < r; i++)
        {
            curr[0] = matrix[i][0]=='1'? 1 : 0;
            if(max < curr[0])
            {
                max = curr[0];
            }
            
            for(int j =  1; j < c; j++)
            {
                if(matrix[i][j] == '0')
                {
                    curr[j] = 0;
                }else{
                    curr[j] = Math.max(1, Math.min(curr[j-1], Math.min(prev[j-1], prev[j]) ) + 1 );
                    
                    if(max < curr[j] * curr[j]){
                        max = curr[j] * curr[j];
                    }
                }
            }
            
            prev = curr.clone();
            //System.arraycopy(curr,0,prev,0, c);
        }
        
        return max;
    }
}