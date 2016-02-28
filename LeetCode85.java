/*
85. Maximal Rectangle My Submissions Question
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        return help1(matrix);
    }
    
    private int help1(char[][]board)
    {
        if(board == null || board.length == 0) return 0;
        int row = board.length;
        int col = board[0].length;
        
        int []dp = new int[col];
        int gMax = -1;
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == 0)
                {
                    dp[j] = board[i][j] == '0'? 0 : 1;
                }else{
                    dp[j] = board[i][j] == '0'? 0 : dp[j] + 1;
                }
            }
            int lMax = helper(dp);
            gMax = Math.max(gMax, lMax);
        }
        
        return gMax;
    }
    private int help2(char[][]board)
    {
        if(board == null || board.length == 0) return 0;
        int row = board.length;
        int col = board[0].length;
        
        int [][]dp = new int[row][col];
        int gMax = -1;
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == 0)
                {
                    dp[i][j] = board[i][j] == '0'? 0 : 1;
                }else{
                    dp[i][j] = board[i][j] == '0'? 0 : dp[i-1][j] + 1;
                }
            }
            int lMax = helper(dp[i]);
            gMax = Math.max(gMax, lMax);
        }
        
        return gMax;
    }
    
    private int helper(int[] num)
    {
        Stack<Integer> s = new Stack<Integer>();
        int max  = 0;
        int area = 0;
        int i = 0;
        for(i = 0; i < num.length; )
        {
            if(s.isEmpty() || num[s.peek()] <= num[i]){
                s.push(i++);
            }else{
                // now calculate the area now
                int idx = s.pop();
                int candidate = num[idx] * (s.isEmpty()? i  : i - s.peek()-1);
                max = Math.max(max, candidate);
            }
        }
    
            while(!s.isEmpty())
            {
                 int candidate = num[s.pop()] * (s.isEmpty()? i : i - s.peek()-1);
                 max = Math.max(max, candidate);
            }
        
        return max;
    }
}