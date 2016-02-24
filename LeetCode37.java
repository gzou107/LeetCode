/*
37. Sudoku Solver My Submissions Question
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


A sudoku puzzle...


...and its solution numbers marked in red.
*/

public class Solution {
    
    public void solveSudoku(char[][] board) {
        help(board, 0,0);
    }
    
    private boolean help(char[][]board, int row, int col)
    {
        if(row == 9){
            return true;
        }
        
        if(col == 9){
            return help(board, row+1, 0);
        }
        
        if(board[row][col] == '.')
        {
            for(char ch = '1'; ch <='9'; ch++)
            {
                board[row][col]= ch;
                if(isValid(board, ch, row, col))
                {
                    if(help(board, row, col+1))
                    {
                        return true;
                    }
                }
                board[row][col]='.';
            }
            return false;
        }else
        {
            return help(board, row, col+1);
        }
    }
    
    // check whether it's legal to add c into position (i, j)
    private boolean isValid(char[][]board, char c, int x, int y)
    {
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i++)
        {
            // !!! remember to avoid compare with itself
            if( i != x && board[i][y] == c){
                return false;
            }
        }
        
        for(int j = 0; j < col; j++)
        {
            if(j != y && board[x][j] == c){
                return false;
            }
        }
        
        int topX = (x/3)*3;
        int topY = (y/3)*3;
        for(int i = topX; i < topX+3; i++)
        {
            for(int j = topY; j < topY+3; j++)
            {
                if(i != x && j !=y && board[i][j] == c){
                    return false;
                }
            }
        }
        return true;
    }
}