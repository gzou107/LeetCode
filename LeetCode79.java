/*
79. Word Search My Submissions Question
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        return help(board, word);
       
    
    }
    
    private boolean help(char[][]board, String word)
    {
        if(word == null || word.length() == 0) return true;
        
        boolean [][]visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(board[i][j] == word.charAt(0))
                {
                    if(dfs(board, i, j, 0, word, visited))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
   
 
  private boolean dfs(char[][]board, int i, int j, int k, String word, boolean[][]visited)
    {
       if(k == word.length()){
            return true;
        }
        
        if(i <0 || i>=board.length || j <0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(k) ){
            return false;
        }
        
        
        visited[i][j] = true;
        /*
        if(dfs(board,  i-1, j, k+1,word, visited) || 
           dfs(board,  i+1, j, k+1,word, visited) ||
           dfs(board,  i, j-1, k+1,word, visited) || 
           dfs(board,  i, j+1, k+1,word, visited)){
            return true;
        }*/
        
        boolean up =  dfs(board, i-1, j, k+1, word, visited);
        if(up){
            visited[i][j] = false;
            return true;
        }
        
        boolean down = dfs(board, i+1, j, k+1, word, visited);
        if(down){
            visited[i][j] = false;
            return true;
        }
        
        boolean left = dfs(board, i, j-1, k + 1, word, visited);
        if(left){
            visited[i][j] = false;
            return true; 
        }
        
        boolean right = dfs(board, i, j+1, k+1, word, visited);
        if(right){
            visited[i][j] = false;
            return true;
        }
 
        visited[i][j] = false;
        return false;
    }
    
    private boolean help2(char[][]board, String word)
    {
        char[] w = word.toCharArray();
            for (int y=0; y<board.length; y++) {
                for (int x=0; x<board[y].length; x++) {
                    if (exist(board, y, x, w, 0)) return true;
                }
            }
            return false;
    }
        
   private boolean exist(char[][] board, int y, int x, char[] word, int i) {
    if (i == word.length) return true;
    if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
    if (board[y][x] != word[i]) return false;
    board[y][x] ^= 256;
    boolean exist = exist(board, y, x+1, word, i+1)
        || exist(board, y, x-1, word, i+1)
        || exist(board, y+1, x, word, i+1)
        || exist(board, y-1, x, word, i+1);
    board[y][x] ^= 256;
    return exist;
}

    private class position
    {
        public int i;
        public int j;
        public int k;
        
        public position(int i, int j, int k)
        {
            this.i = i;
            this.j = j;
            this.k = k;
        }
        
        public int getHashCode()
        {
            int prime = 37;
            int result = 1;
            result = (prime*result) ^ i;
            result = (prime * result) ^ j;
            result = (prime * result) ^ k;
            return result;
        }
        
        public boolean equals(Object other){
            if(other == this) return true;
            if(other == null) return false;
            
            if(!(other instanceof position)){
                return false;
            }
          
            position another = (position)other;
            return this.i == another.i && this.j == another.j && this.k == another.k;
        }
    }
    
     private boolean dfs0(char[][]board, int i, int j, int k, String word, boolean[][]visited, Map<position, Boolean> cache)
    {
        if(i <0 || i>=board.length || j <0 || j >= board[0].length){
            return false;
        }
        
        if(k == word.length()){
            return true;
        }
        
        if(board[i][j] != word.charAt(k)){
            return false;
        }else if(k == word.length()-1){
            return true;
        }
        position p = new position(i, j, k);
        visited[i][j] = true;
        boolean up = false;
        if(i-1 >=0 && !visited[i-1][j]){
            up = dfs0(board, i-1, j, k+1, word, visited, cache);
        }
        
        boolean down = false;
        if(i+1 < board.length && !visited[i+1][j]){
            down = dfs0(board, i+1, j, k+1, word, visited, cache);
        }
        
        boolean left = false;
        if(j-1 >=0 && !visited[i][j-1])
        {
            left = dfs0(board, i, j-1, k + 1, word, visited, cache);
        }
        
        boolean right = false;
        if(j+1 < board[0].length && !visited[i][j+1])
        {
            right = dfs0(board, i, j+1, k+1, word, visited, cache);
        }
        
        visited[i][j] = false;
        
        boolean res = up||down||left||right;
        cache.put(p, res);
        
        return res;
    }
}