/*
Valid Sudoku My Submissions Question
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        // verify each row
        for(int i = 0; i< 9; i++){
            Set<Character> s = new HashSet<Character>();
            for(int j=0; j < 9; j++){
                char c = board[i][j];
                if(c == '.'){
                    continue;
                }else if(s.contains(c)){
                    return false;
                }else{
                    s.add(c);
                }
            }
        }
        
        // verify each column
        for(int i = 0; i< 9; i++){
            Set<Character> s = new HashSet<Character>();
            for(int j=0; j < 9; j++){
                char c = board[j][i];
                if(c == '.'){
                    continue;
                }else if(s.contains(c)){
                    return false;
                }else{
                    s.add(c);
                }
            }
        }
        
        // verify each 3 * 3 board
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                if(!isValid(board, i*3, j*3)){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // helper function to determine a 3*3 board is valid, with top left cornor is [i, j]
    public boolean isValid(char [][] b, int i, int j){
        Set<Character> s = new HashSet<Character>();
        for(int r = i; r < i+3; r++){
            for(int c = j; c < j+3; c++){
                char ch = b[r][c];
                if(ch == '.'){
                    continue;
                }else if(s.contains(ch)){
                    return false;
                }else{
                    s.add(ch);
                }
            }
        }
        
        return true;
    }
    
}