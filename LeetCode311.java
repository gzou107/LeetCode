/*
311. Sparse Matrix Multiplication My Submissions Question
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        //return helper(A,B);
        //return helper1(A,B);
        return helper1(A,B);
    }
    
    public int[][] helper2(int[][]A, int[][]B){
        Map<Integer, Map<Integer, Integer>> mA = new HashMap<Integer, Map<Integer,Integer>>();
        
        for(int i = 0; i <A.length; i++){
            Map<Integer, Integer> newRow = new HashMap<Integer, Integer>();
            for(int j = 0; j < A[0].length; j++){
                if(A[i][j] != 0){
                    newRow.put(j, A[i][j]);
                }
            }
            mA.put(i, newRow);
        }
        
        int[][]C = new int[A.length][B[0].length];
        
        for(int i = 0; i < mA.size(); i++)
        {
            for(int j = 0; j <B[0].length;j++ )
            {
                if(B[i][j] != 0 && mA.get(i).containsKey(j)){
                    C[i][j] += B[i][j] * mA.get(i).get(j);
                }
            }
        }
        
        return C;
        
    }
    
    public int[][] helper(int[][]A, int[][]B){
        Map<Integer, Map<Integer, Integer>> mA = new HashMap<Integer, Map<Integer,Integer>>();
        Map<Integer, Map<Integer, Integer>> mB = new HashMap<Integer, Map<Integer,Integer>>();
        
        for(int i = 0; i <A.length; i++){
            Map<Integer, Integer> newRow = new HashMap<Integer, Integer>();
            for(int j = 0; j < A[0].length; j++){
                if(A[i][j] != 0){
                    newRow.put(j, A[i][j]);
                }
            }
            mA.put(i, newRow);
        }
        
        for(int c = 0; c < B[0].length; c++){
            Map<Integer, Integer> newCol = new HashMap<Integer,Integer>();
            for(int r = 0; r < B.length; r++){
                if(B[r][c] != 0){
                    newCol.put(r, B[r][c]);
                }
            }
            mB.put(c, newCol);
        }
        
        int[][]C = new int[A.length][B[0].length];
        /*for(int i = 0; i < A.length; i++){
            Arrays.fill(C[i], 0);
        }*/
        
        for(int i = 0; i < mA.size(); i++)
        {
            for(int j = 0; j <mB.size();j++ )
            {
                if(mA.get(i).size() > 0 && mB.get(j).size() > 0)
                {
                    int ans = 0;
                    for(Integer k : mA.get(i).keySet())
                    {
                        if(mB.get(j).containsKey(k))
                        {
                            ans += mA.get(i).get(k) * mB.get(j).get(k);
                        }
                    }
                    C[i][j] = ans;
                }
            }
        }
        
        return C;
        
    }
    
    public int[][] helper1(int[][]A, int[][]B){
        int r = A.length;
        int m = A[0].length;
        int n = B[0].length;
        
        int[][]c = new int[r][n];
        for(int i = 0; i < r; i++){
            for(int j = 0; j <m; j++){
                if(A[i][j] != 0){
                    for(int k = 0; k <n; k++){
                        if(B[j][k] != 0){
                            c[i][k] += A[i][j] * B[j][k];
                        }
                    }
                }
            }
        }
        
        return c;
    }
}