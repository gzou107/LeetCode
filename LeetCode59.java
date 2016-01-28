/*
59. Spiral Matrix II My Submissions Question
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

public class Solution {
    public int[][] generateMatrix(int n) {
        return help(n);
    }
    
    private int[][] help(int n)
    {
        if(n <0 || n == 0) return new int[0][0];
        
        int [][] res = new int[n][n];
        int x0 = 0;
        int y0 = 0;
        int x1 = n-1;
        int y1 = n-1;
        int d = 1;
        
        while(x0 <= x1 && y0 <= y1)
        {
            // handle line from (x0,y0) to (x0, y1)
            for(int y = y0; y <= y1; y++){
                res[x0][y] = d++;
            }
            
            //handle line from (x0,y1) to (x1,y1)
            for(int x=x0+1; x <=x1; x++)
            {
                res[x][y1] = d++;
            }

            // handle line from (x1,y1) to (x1,y0)
            for(int y = y1-1; y>=y0; y--){
                res[x1][y]  = d++;
            }
            
           // if(y0 >= y1)break;
            for(int x=x1-1; x>x0; x--){
                res[x][y0] = d++;
            }
    
            x0++;
            y0++;
            x1--;
            y1--;
        }
        
        return res;
    }
}