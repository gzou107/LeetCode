/*
74. Search a 2D Matrix My Submissions Question
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true
*/

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        return helper(matrix, target);
    }
    
    private boolean helper(int [][] matrix, int target)
    {
        if(matrix == null || matrix.length == 0) return false;
        
        int l =0;
        int r = matrix[0].length * matrix.length - 1;;
        while(l <= r)
        {
            int m = l + (r-l)/2;
            int row = m /matrix[0].length;
            int col = m %matrix[0].length;
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] < target){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        
        return false;
    }
}