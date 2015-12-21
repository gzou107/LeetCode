/*
289. Game of Life My Submissions Question
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/

public class Solution {
    public void gameOfLife(int[][] board) {
       // helper(board);
       
       helper2(board);
    }
    
    private void helper(int[][]board){
        if(board == null || board.length == 0){
            return;
        }
        
        int row = board.length;
        int col = board[0].length;
        
        int[][] backup = new int[row][col];
        for(int i = 0; i < row; i++){
            System.arraycopy(board[i],0,backup[i],0,col);
        }
       
        for(int i = 0; i <row; i++ ){
            for(int j = 0; j < col; j++){
                 board[i][j] = update(backup, i, j);
            }
        }
    }
    
        private int update(int[][]board, int i, int j){
        int count = countOnes(board, i, j, board.length, board[0].length);
        
        if(board[i][j] == 0){
            if(count == 3){
                return 1;
            }
            return 0;
        }else if(board[i][j] == 1){
            if(count == 2 || count == 3){
                return 1;
            }
            return 0;
        }
        return 0;
    }
    
        private int countOnes(int [][] board, int i, int j, int r, int c){
        int count =0;
        
        if(i-1>=0 && j-1>=0){
            count += board[i-1][j-1];
        }
        
        if(i-1 >=0){
            count += board[i-1][j];
        }
        
        if(i-1 >=0 && j + 1 < c){
            count += board[i-1][j+1];
        }
        
        if(j - 1>= 0){
            count += board[i][j-1];
        }
        
        if(j+1 <c){
            count += board[i][j+1];
        }
        
        if(i + 1 < r && j - 1 >= 0){
            count += board[i+1][j-1];
        }
        
        if(i+1 < r){
            count += board[i+1][j];
        }
        
        if(i+1 < r && j+1 < c){
            count += board[i+1][j+1];
        }
        
        return count;
        /*
        return (i-1>=0 && j-1 >=0?board[i-1][j-1]:0) + (i-1 >=0?board[i-1][j]:0) + (i-1 >=0 && j+1<c?board[i-1][j+1]:0) + (j>=1?board[i][j-1]:0) + (j+1<c?board[i][j+1]:0) + (i+1<r && j>=1?board[i+1][j-1]:0) + (i+1 < r?board[i+1][j]:0) + (i+1 <r && j + 1 <c?board[i+1][j+1]:0);
        */
    }
    
    private void helper2(int [][] board){
        if(board == null || board.length == 0){
            return;
        }
        
        int row = board.length;
        int col = board[0].length;
        
        int [] prev = new int[col];
        int [] curr = new int[col];
        
        for(int i = 0; i <row; i++ )
        {
             prev = curr.clone();
             System.arraycopy(board[i],0, curr, 0, col);
             
            for(int j = 0; j < col; j++){
               
                 // now we update the value within board[i][...]
                 board[i][j] = updateSmarter(board, i, j, prev,curr);
            }
        }
    }
    

    
     private int updateSmarter(int[][]board, int i, int j, int[]prev, int[]curr){
        int count = countOnesSmart(board, i, j, board.length, board[0].length, prev,curr);
        
        if(board[i][j] == 0){
            if(count == 3){
                return 1;
            }
            return 0;
        }else if(board[i][j] == 1){
            if(count == 2 || count == 3){
                return 1;
            }
            return 0;
        }
        return 0;
    }
    
     private int countOnesSmart(int [][] board, int i, int j, int r, int c, int[]prev, int [] curr){
        int count =0;
        
        if(i-1>=0 && j-1>=0){
            count += prev[j-1];//board[i-1][j-1];
        }
        
        if(i-1 >=0){
            count += prev[j];//board[i-1][j];
        }
        
        if(i-1 >=0 && j + 1 < c){
            count += prev[j+1];//board[i-1][j+1];
        }
        
        if(j - 1>= 0){
            count += curr[j-1];
        }
        
        if(j+1 <c){
            count += curr[j+1];
        }
        
        if(i + 1 < r && j - 1 >= 0){
            count += board[i+1][j-1];
        }
        
        if(i+1 < r){
            count += board[i+1][j];
        }
        
        if(i+1 < r && j+1 < c){
            count += board[i+1][j+1];
        }
        
        return count;
        /*
        return (i-1>=0 && j-1 >=0?board[i-1][j-1]:0) + (i-1 >=0?board[i-1][j]:0) + (i-1 >=0 && j+1<c?board[i-1][j+1]:0) + (j>=1?board[i][j-1]:0) + (j+1<c?board[i][j+1]:0) + (i+1<r && j>=1?board[i+1][j-1]:0) + (i+1 < r?board[i+1][j]:0) + (i+1 <r && j + 1 <c?board[i+1][j+1]:0);
        */
    }
    

}

/*
【解题思路】
1、这个题目的难点在于，每次既要修改当前状态，又要保存当前状态。
2、初始状态为0和1，那么变化后的状态无非有四个，{00，01，10，11}，其中高位表示下个状态，低位表示当前状态。
3、根据题目要求：
      1）、当前状态为1，活细胞少于2个，那么它就死亡了，变为01，即数字1，其实不需要改变当前值。
      2）、当前状态为1，活细胞为2或者3，它继续生存，变为11，即数字3。
      3）、当前状态为1，活细胞多余3个，死亡，变为01，还是1，不需要改变当前值。
      4）、当前状态为0，活细胞为3个，它变为活细胞，状态为10，即数字2。
4、这样统计每个细胞周围活细胞的数量，根据3的描述，值为1和3的都是活着的。
5、对每个细胞做一次这样的操作，最后右移一位即为下一个状态。

public class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int status = getStatus(board, m, n, i, j);
                if ((board[i][j] == 1 || board[i][j] == 3)
                        && (status == 2 || status == 3)) {
                    board[i][j] = 3;
                } else {
                    board[i][j] = status == 3 ? 2 : board[i][j];
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int getStatus(int[][] board, int m, int n, int x, int y) {
        int live = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX == x && newY == y) {
                    continue;
                }
                if (newX >= 0 && newY >= 0 && newX < m && newY < n) {
                    if (board[newX][newY] == 1 || board[newX][newY] == 3) {
                        live++;
                    }
                }
            }
        }
        return live;
    }
}
*/