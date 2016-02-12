/*
308. Range Sum Query 2D - Mutable My Submissions Question
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
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/
// log(mn) running time complexity
// O(m*n) space complexity
public class NumMatrix {
    private int[][]bits;
    private int[][]mat;
    
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
		// re-use matrix, and hence can't combine insert and u
        this.mat = matrix;
        
        this.bits = new int[matrix.length + 1][matrix[0].length+1];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                insert(i, j);
            }
        }
    }
    
    private int incre(int i)
    {
        return i&(-i);
    }
    
    private void insert(int i, int j)
    {
        for(int m = i+1; m <= mat.length; m += incre(m))
        {
            for(int n = j+1; n <= mat[0].length; n += incre(n))
            {
                bits[m][n] += mat[i][j];
            }
        }
    }
    
    public void update(int row, int col, int val) 
    {
        int diff = this.mat[row][col] - val;
        this.mat[row][col] = val;// remember to update the value!!!!
        if(diff == 0) return;
        for(int i = row+1; i <= mat.length; i += incre(i))
        {
            for(int j =col+1; j<= mat[0].length; j += incre(j))
            {
                bits[i][j] -= diff;
            }
        }
    }
    

    private int getSum(int row1, int col1)
    {
        int sum = 0;
        for(int i = row1+1; i > 0; i -= incre(i))
        {
            for(int j = col1+1; j > 0; j -= incre(j))
            {
                sum += bits[i][j];
            }
        }
        return sum;
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2) - getSum(row2, col1-1) - getSum(row1-1, col2) + getSum(row1-1, col1-1);
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);
// log(m*n) running time complexity
// 2*O(m*n) space complexity
public class NumMatrix {
    private int[][]bits;
    private int[][]mat;
    
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        this.mat = new int[matrix.length][matrix[0].length];
        
        this.bits = new int[matrix.length + 1][matrix[0].length+1];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public void update(int row, int col, int val) 
    {
        int diff = val - this.mat[row][col];
        this.mat[row][col] = val;// remember to update the value!!!!
       // if(diff == 0) return;
        for(int i = row+1; i <= mat.length; i += incre(i))
        {
            for(int j =col+1; j<= mat[0].length; j += incre(j))
            {
                bits[i][j] += diff;
            }
        }
    }
}

//ANother approach
// running time: O(min(m, n))
// space: O(m*n)
public class NumMatrix {
    private int[][]colSum;// colSum[i][j]: represents column sum of elements from matrix[0][j],...matrix[i-1][j]
    private int[][]mat;
    
    public NumMatrix(int[][] matrix) 
    {
        if(matrix == null || matrix.length == 0) return;
        this.mat = matrix;
        
        this.colSum = new int[matrix.length + 1][matrix[0].length];
        for(int i = 1; i <= matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                colSum[i][j] = colSum[i-1][j] + matrix[i-1][j];
            }
        }
    }
    
    public void update(int row, int col, int val) 
    {
        int diff = val - this.mat[row][col];
        this.mat[row][col] = val;// remember to update the value!!!!

        for(int i = row+1; i <= mat.length; i++)
        {
            colSum[i][col] += diff;
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) 
    {
        int sum = 0;
        for(int i = col1; i <=col2; i++)
        {
            sum += colSum[row2+1][i] - colSum[row1][i];
        }
        return sum;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);