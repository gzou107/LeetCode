/*
240. Search a 2D Matrix II My Submissions Question
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        return helper1(matrix, target);
    }
    
    private boolean helper1(int[][]matrix, int target){
        if(matrix == null) return false;
        if(matrix[0].length == 0) return false;
        int r = matrix.length;
        int c = matrix[0].length;
        int y = c - 1;
        int x = 0;
        
        while( x < r && y >= 0){
            if(matrix[x][y] == target){
                return true;
            }else if(matrix[x][y] < target){
                x++;
            }else{
                y--;
            }
        }
        
        return false;
    }
}