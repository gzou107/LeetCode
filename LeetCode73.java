/*
73. Set Matrix Zeroes My Submissions Question
Total Accepted: 61524 Total Submissions: 185740 Difficulty: Medium
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

public class Solution {
    public void setZeroes(int[][] matrix) {
        help(matrix);
    }
    
    private void help(int[][]m)
    {
        if(m == null || m.length ==0) return;
        
        int row = m.length;
        int col = m[0].length;
        boolean emptyRow = false;
        boolean emptyCol = false;
        
        for(int i = 0; i < col; i++)
        {
            if(m[0][i] == 0){
                emptyRow = true;
                break;
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            if(m[i][0] == 0){
                emptyCol = true;
                break;
            }
        }
        
        for(int i =  1; i < row; i++)
        {
            for(int j = 1; j < col; j++)
            {
                if(m[i][j] == 0)
                {
                    m[0][j] = 0;
                    m[i][0] = 0;
                }
            }
        }
        
        for(int i = 1; i < row; i++)
        {
            for(int j = 1; j < col; j++)
            {
                if(m[i][0] == 0 || m[0][j] == 0){
                    m[i][j] = 0;
                }
            }
        }
        
        if(emptyRow)
        {
            for(int i = 0; i < col; i++)
            {
                m[0][i] = 0;
            }
        }
        
        if(emptyCol)
        {
            for(int i = 0; i < row; i++)
            {
                m[i][0] = 0;
            }
        }
    }
}