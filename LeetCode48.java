/*
48. Rotate Image My Submissions Question
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class Solution {
    public void rotate(int[][] matrix) {
        help(matrix);
    }
    
    private void help(int[][] matrix)
    {
        if(matrix == null || matrix.length == 0)
        {
            return;
        }
        int n = matrix.length;
        for(int i = 0; i < (n+1)/2; i++)
        {
            for(int j = i; j < n - i -1; j++)
            {
                rott(matrix, i, j);
            }
        }
    }
    
    private void rott(int[][] matrix, int i, int j)
    {
        int n = matrix.length;
        int t = matrix[i][j];
        matrix[i][j] = matrix[n-1-j][i];
        matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
        matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
        matrix[j][n-1-i] = t;
    }
}