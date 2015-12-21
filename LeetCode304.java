/*
304. Range Sum Query 2D - Immutable My Submissions Question
Total Accepted: 4665 Total Submissions: 22663 Difficulty: Medium
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

public class NumMatrix {

   private int [][] presum;
   private int []pre;
   private boolean isNull = false;
   private boolean isZeroLen = false;
   private boolean is1D = false;
   // we define the presum[i][j] to include all cell sum between [0,0] to [i,j] both inclusive
    public NumMatrix(int[][] matrix) {
        if(matrix == null){
            isNull = true;
        }else if(matrix.length == 0){
            isZeroLen = true;
        }else if(matrix[0].length == 0){
            is1D = true;
            pre[0] = matrix[0][0];
            for(int i =1; i < matrix.length; i++){
                pre[i] = pre[i-1] + matrix[0][i];
            }
        }else{
            int r = matrix.length;
            int c = matrix[0].length;
            presum = new int[r][c];
            presum[0][0] = matrix[0][0];
            
            for(int i = 1; i < c; i++){
                presum[0][i] = presum[0][i-1] + matrix[0][i]; 
            }
            
            for(int i = 1; i < r; i++){
                presum[i][0] = presum[i-1][0] + matrix[i][0];
            }
            
            // now calculate the rest
            for(int i = 1; i < r; i++){
                for(int j = 1; j < c; j++){
                    presum[i][j] = presum[i-1][j] + presum[i][j-1] + matrix[i][j] - presum[i-1][j-1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(isNull || isZeroLen){
            return 0;
        }
        
        if(is1D){
            return pre[col2] - pre[col1];
        }
        
        if(row1 == 0){
            if(col1 == 0){
                return presum[row2][col2];
            }else{
                return presum[row2][col2] - presum[row2][col1-1];
            }
        }else{
            if(col1 == 0){
                return presum[row2][col2] - presum[row1-1][col2];
            }else{
               return presum[row2][col2] + presum[row1-1][col1-1] - presum[row1-1][col2] - presum[row2][col1-1];  
            }
        }
       
    }
    
    /*
    int[][] sum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i+1][j+1] = sum[i+1][j] + sum[i][j+1] - sum[i][j] + matrix[i][j]; 
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2+1][col2+1] - sum[row2+1][col1] - sum[row1][col2+1] + sum[row1][col1];
    }
    */
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);