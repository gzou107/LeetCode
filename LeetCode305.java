305. Number of Islands II My Submissions Question
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        return helper0(m, n, positions);
       // return helper(m, n, positions);
    }
    
    private List<Integer> helper0(int m, int n, int[][]positions)
    {
        List<Integer> ans = new ArrayList<Integer>();
        int[] id = new int[m*n];
        Arrays.fill(id, -1); // have to set to -1, as 0 can be the root node of position (0,0)
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int count = 0;
        
        for(int i = 0; i < positions.length; i++)
        {
            count++;
            int x = positions[i][0];
            int y = positions[i][1];
            int idx = x * n + y;
            id[idx] = idx;
            
            
            for(int j = 0; j <dirs.length; j++)
            {
                int newX = x + dirs[j][0];
                int newY = y + dirs[j][1];
                int newIdx = newX * n + newY;
                
                if(newX >=0 && newX < m && newY >=0 && newY < n && id[newIdx] != -1)
                {
                    
                    int root = find(id, newIdx);
                    if(root != idx)
                    {
                       //id[idx] = root; // !!!!! this will not work, why???. as we have reached the root, which means id[root] = root, if we change root's parent, all its descendant will be changed automatically; however, if we change id[idx], within the inner for loop, the root is kept on updated, which meaning we do not union the nodes correctly in this assignment. remember always set the root node's parent
                       id[root] = idx;
                       count--; 
                    }
                    // !!!bad code comment here, as the id[newIdx] is not necessary its root node
                    // we should always use its root node
                    /*
                    if(id[newIdx] != idx)
                    {
                       int root = find(id, newIdx);
                       id[root] = idx;
                       count--; 
                    }
                    */
                }
            }
           ans.add(count);
        }
        
        return ans;
    }
    
    private int find(int[]id, int i)
    {
        while(i != id[i])
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    private List<Integer> helper(int m, int n, int[][] positions)
    {
       int [][] grid = new int[m][n];
       UF uf = new UF(grid);
       
       List<Integer> ans = new ArrayList<Integer>();
       for(int i = 0; i < positions.length; i++)
       {
           uf.add(positions[i][0], positions[i][1]);
           ans.add(uf.size());
       }
       
       return ans;
    }
    
    private class UF
    {
        private int [] id;
        private int [] sz;
        private int [][] grid;
        private int count;
        private int [][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        private UF(int [][] grid)
        {
            this.grid = grid;
            this.id = new int[grid.length*grid[0].length];
            this.sz = new int[grid.length*grid[0].length];
            this.count = 0;
        }
        
        private void union(int x1, int y1, int x2, int y2)
        {
            int p1 = find(x1,y1);
            int p2 = find(x2, y2);
            
            if(p1 ==p2){
                return;
            }else{
                if(sz[p1] < sz[p2]) // weighted 
                {
                    id[p2] = p1;
                    sz[p1] += sz[p2];
                }else{
                    id[p1] = p2;
                    sz[p2] += sz[p1];
                }
                count--;
            }
        }
        
        public void add(int x, int y)
        {
            this.grid[x][y] = 1;
            this.count++;
            int id = x * grid[0].length + y;
            this.sz[id] = 1;
            this.id[id] = id ;
            
            for(int i = 0; i < dirs.length; i++)
            {
                int newX = x + dirs[i][0];
                int newY = y + dirs[i][1];
                
                if(newX >=0 && newX < grid.length && newY >=0 && newY < grid[0].length && grid[newX][newY] == 1)
                {
                    union(x, y, newX, newY);
                }
            }
        }
        
        private int size()
        {
            return count;
        }
        
        private int find(int x, int y)
        {
            int idx = x * grid[0].length + y;
            while(id[idx] != idx)
            {
                id[idx] = id[id[idx]]; // path compression
                idx = id[idx];
            }
            return idx;
        }
    }
}