/*
130. Surrounded Regions My Submissions Question
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/
public class Solution {
    public void solve(char[][] board) {
        //help(board);
        help5(board);
       //help2(board);
       // help3(board);
       // help4(board);
    }
    
     public void help4(char[][] board) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if((i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) && (board[i][j]=='O')){
                    Queue<Integer> q = new LinkedList<Integer>();
                    q.offer(i * board[0].length + j);//save a single index is faster than two numbers or an array
                    board[i][j] = 'B';
                    while(!q.isEmpty()){
                        Integer key = q.poll();
                        int x = key / board[0].length;
                        int y = key % board[0].length;
                        if(x > 0 && board[x-1][y]=='O'){
                            q.offer((x-1) * board[0].length + y);
                            board[x-1][y] = 'B';
                        }
                        if(x < board.length - 1  && board[x+1][y]=='O'){
                            q.offer((x+1) * board[0].length + y);
                            board[x+1][y] = 'B';
                        }
                        if(y > 0  && board[x][y-1]=='O'){
                            q.offer(x * board[0].length + y - 1);
                            board[x][y-1] = 'B';
                        }
                        if(y < board[0].length - 1  && board[x][y+1]=='O'){
                            q.offer(x * board[0].length + y + 1);
                            board[x][y+1] = 'B';
                        }
                    }
                }
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j]=='O') board[i][j]='X';
                if(board[i][j]=='B') board[i][j]='O';
            }
        }
    }
    // 16ms
    private void help2(char[][]board){
        if(board == null || board.length == 0) return;
        
        int row = board.length;
        int col = board[0].length;
        //we only need do dfs for all the boundary cells whose value is O
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < col; i++)
        {
            if(board[0][i] == 'O'){
                q.add(new int[]{0,i});
            }
            
            if(board[row-1][i] == 'O'){
                 q.add(new int[]{row-1,i});
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            if(board[i][0] == 'O'){
                q.add(new int[]{i, 0});
            }
            
            if(board[i][col-1] == 'O'){
                q.add(new int[]{i, col-1});
            }
        }
        
        while(!q.isEmpty())
        {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            board[r][c] = '1';
            
            if(r-1>=0 && board[r-1][c] == 'O'){
                q.add(new int[]{r-1,c});
            }
            
            if(r+1 < row && board[r+1][c] == 'O'){
                q.add(new int[]{r+1, c});
            }
            
            if(c-1>=0 && board[r][c-1] == 'O'){
                q.add(new int[]{r,c-1});
            }
            
            if(c+1 < col && board[r][c+1] == 'O'){
                q.add(new int[]{r,c+1});
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(board[i][j] =='1')
                {
                    board[i][j] ='O';
                }else{
                    board[i][j] ='X';
                }
            }
        }
    }
    
        // 21 ms
    private void help3(char[][]board){
        if(board == null || board.length == 0) return;
        
        int row = board.length;
        int col = board[0].length;
        //we only need do dfs for all the boundary cells whose value is O
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < col; i++)
        {
            if(board[0][i] == 'O'){
                q.add(0);
                q.add(i);
            }
            
            if(board[row-1][i] == 'O'){
                 q.add(row-1);
                 q.add(i);
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            if(board[i][0] == 'O'){
                q.add(i);
                q.add(0);
            }
            
            if(board[i][col-1] == 'O'){
                q.add(i);
                q.add(col-1);
            }
        }
        
        while(!q.isEmpty())
        {
            int r = q.poll();
            int c = q.poll();
            board[r][c] = '1';
            
            if(r-1>=0 && board[r-1][c] == 'O'){
                q.add(r-1);
                q.add(c);
            }
            
            if(r+1 < row && board[r+1][c] == 'O'){
                q.add(r+1);
                q.add(c);
            }
            
            if(c-1>=0 && board[r][c-1] == 'O'){
                q.add(r);
                q.add(c-1);
            }
            
            if(c+1 < col && board[r][c+1] == 'O'){
                q.add(r);
                q.add(c+1);
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(board[i][j] =='1')
                {
                    board[i][j] ='O';
                }else{
                    board[i][j] ='X';
                }
            }
        }
    }
    
    public void help5(char[][] board) {
    if (board.length == 0 || board[0].length == 0)
        return;
    if (board.length < 2 || board[0].length < 2)
        return;
    int m = board.length, n = board[0].length;
    //Any 'O' connected to a boundary can't be turned to 'X', so ...
    //Start from first and last column, turn 'O' to '*'.
    for (int i = 0; i < m; i++) {
        if (board[i][0] == 'O')
            boundaryDFS(board, i, 0);
        if (board[i][n-1] == 'O')
            boundaryDFS(board, i, n-1); 
    }
    //Start from first and last row, turn '0' to '*'
    for (int j = 0; j < n; j++) {
        if (board[0][j] == 'O')
            boundaryDFS(board, 0, j);
        if (board[m-1][j] == 'O')
            boundaryDFS(board, m-1, j); 
    }
    //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 'O')
                board[i][j] = 'X';
            else if (board[i][j] == '*')
                board[i][j] = 'O';
        }
    }
}
//Use DFS algo to turn internal however boundary-connected 'O' to '*';
private void boundaryDFS(char[][] board, int i, int j) {
    if (i < 0 || i > board.length - 1 || j <0 || j > board[0].length - 1)
        return;
    if (board[i][j] == 'O')
        board[i][j] = '*';
    if (i > 1 && board[i-1][j] == 'O')
        boundaryDFS(board, i-1, j);
    if (i < board.length - 2 && board[i+1][j] == 'O')
        boundaryDFS(board, i+1, j);
    if (j > 1 && board[i][j-1] == 'O')
        boundaryDFS(board, i, j-1);
    if (j < board[i].length - 2 && board[i][j+1] == 'O' )
        boundaryDFS(board, i, j+1);
}

}

    // stackoverflow !!! wtf?
    private void help(char[][]board){
        if(board == null || board.length == 0) return;
        
        int row = board.length;
        int col = board[0].length;
        //we only need do dfs for all the boundary cells whose value is O
        for(int i = 0; i < col; i++)
        {
            if(board[0][i] == 'O'){
                dfs(board, 0, i, row, col);
            }
            
            if(board[row-1][i] == 'O'){
                dfs(board, row-1, i, row, col);
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            if(board[i][0] == 'O'){
                dfs(board, i, 0, row, col);
            }
            
            if(board[i][col-1] == 'O'){
                dfs(board, i, col-1, row, col);
            }
        }
        
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(board[i][j] !='O')
                {
                    board[i][j] ='X';
                }
            }
        }
    }
    // this can reduce the speed to 8 ms, as we avoid the long line from top left to bottom right
	 private void dfs0(char[][]board, int i, int j)
    {
        int row = board.length;
        int col = board[0].length;
        if(i <0 || i >= row || j < 0 || j >= col || board[i][j] =='X' || board[i][j]=='1'){
            return;
        }
        
        board[i][j]='*';
        if(i-1 >0 && board[i-1][j] == 'O'){
            dfs(board, i-1, j);
        }
        if(i+1<row-1 && board[i+1][j] == 'O'){
            dfs(board, i+1, j);
        }
        
        if(j-1>0 && board[i][j-1] =='O'){
            dfs(board, i, j-1);
        }
        if(j+1 < col-1 && board[i][j+1] == 'O'){
            dfs(board, i, j+1);
        }
    }
	
    private void dfs(char[][]board, int i, int j, int row, int col)
    {
        if(i <0 || i >= row || j < 0 || j >= col || board[i][j] =='X' || board[i][j]=='1'){
            return;
        }
        
        board[i][j]='1';
        if(i-1 >=0 && board[i-1][j] == 'O'){
            dfs(board, i-1, j, row, col);
        }
        if(i+1<row && board[i+1][j] == 'O'){
            dfs(board, i+1, j, row, col);
        }
        
        if(j-1>=0 && board[i][j-1] =='O'){
            dfs(board, i, j-1, row, col);
        }
        if(j+1 < col && board[i][j+1] == 'O'){
            dfs(board, i, j+1, row, col);
        }
    }
    
    private void dfs1(char[][]board, int i, int j, int row, int col)
    {
        if(i <0 || i >= row || j < 0 || j >= col || board[i][j] =='X' || board[i][j]=='1'){
            return;
        }
        
        board[i][j]='1';
        dfs(board, i-1, j, row, col);
        dfs(board, i+1, j, row, col);
        dfs(board, i, j-1, row, col);
        dfs(board, i, j+1, row, col);
    }