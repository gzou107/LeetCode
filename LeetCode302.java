/*
302. Smallest Rectangle Enclosing Black Pixels My Submissions Question
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*/

public class Solution {
    public int minArea(char[][] image, int x, int y) {
        //return helper(image, x, y);
        return helper1(image, x, y);
    }

    private int helper1(char[][]image, int x, int y)
    {
        int row = image.length;
        int col = image[0].length;
        //top = search row [0...x], find first row contain 1,
        //bottom = search row[x+1, row], find first row contian all 0
        //left = search col[0...y], find first col contain 1,
        //right = search col[y+1, col], find first col contain all 0
        int left = searchCol(image, 0,    y,  0, row, true);
        int right = searchCol(image,y+1, col, 0, row, false);
        int top = searchRow(image, 0,    x,   left, right, true);
        int bott = searchRow(image, x+1, row, left, right, false);
        return (right-left)*(bott-top);
    }
    
    private int searchCol(char[][] image, int l, int r, int top, int bottom, boolean option)
    {
        while(l != r)
        {
            int m = l + (r-l)/2;
            int k = top;
            while(k < bottom && image[k][m] == '0')
            {
                k++;
            }
            
            if( k < bottom == option)
            {
                r = m;
            }else{
                l = m + 1;
            }
        }
        
        return l;
    }
    
    private int searchRow(char[][]image, int top, int bottom, int l, int r, boolean option)
    {
        while(top != bottom)
        {
            int k = l;
            int m = top + (bottom - top)/2;
            while( k < r && image[m][k] == '0')
            {
                k++;
            }
            
            if( k < r == option)
            {
                bottom = m;
            }else
            {
                top = m + 1;
            }
        }
        
        return top;
    }
    public int helper(char[][] image, int x, int y)
    {
        
        Deque<Integer> q = new LinkedList<Integer>();
        boolean[][]visited = new boolean[image.length][image[0].length];
        q.add(x);
        q.add(y);
        visited[x][y] = true;
        int minX = x;
        int maxX = x;
        int minY = y;
        int maxY = y;
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
       
        
        while(!q.isEmpty())
        {
            int topX = q.pop();
            int topY = q.pop();
           
            minX = minX < topX ? minX : topX;
            maxX = maxX < topX ? topX : maxX;
            minY = minY < topY ? minY : topY;
            maxY = maxY < topY ? topY : maxY;
            
            for(int i = 0; i < dirs.length; i++)
            {
                int newX = topX + dirs[i][0];
                int newY = topY + dirs[i][1];
                
                if(newX >=0 && newX < image.length && newY >=0 && newY < image[0].length && image[newX][newY] == '1' && !visited[newX][newY])
                {
                    visited[newX][newY] = true;
                    q.add(newX);
                    q.add(newY);
                }
            }
        }
        
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}