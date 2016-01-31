/*
54. Spiral Matrix My Submissions Question
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        return help(matrix);
    }
    
    private List<Integer> help(int[][]matrix)
    {
        List<Integer> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0) return res;
        
        int row = matrix.length;
        int col = matrix[0].length;
        int x0 = 0;
        int y0 = 0;
        int x1 = row-1;
        int y1 = col-1;
        
        while(x0 <= x1 && y0 <= y1)
        {
            // handle (x0,y0) to (x0, y1)
            for(int y = y0; y<=y1; y++){
                res.add(matrix[x0][y]);
            }
            
            //handle (x0,y1) to (x1, y1)
            for(int x = x0+1; x<= x1; x++)
            {
                res.add(matrix[x][y1]);
            }
            
            for(int y=y1-1; y >=y0 && x1 > x0; y--)
            {
                res.add(matrix[x1][y]);
            }
            
            for(int x = x1-1; x>x0 && y1>y0; x--)
            {
                res.add(matrix[x][y0]);
            }
            
            x0++;
            y0++;
            x1--;
            y1--;
        }
        return res;
    }
}