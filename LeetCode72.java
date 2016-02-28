/*
72. Edit Distance My Submissions Question
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

public class Solution {
    public int minDistance(String word1, String word2) {
        return help0(word1, word2);
    }
    
    public int help4(String word1, String word2) {
        int[] d = new int[word2.length() + 1];
        for (int i = 0; i <= word2.length(); ++i) d[i] = i;
        for (int i = 1; i <= word1.length(); ++i) {
            int prev = d[0];
            d[0] = i;
            for (int j = 1; j <= word2.length(); ++j) {
                int tmp = d[j];
                d[j] = Math.min(d[j - 1], d[j]) + 1;
                d[j] = Math.min(d[j], prev + (word1.charAt(i -1) == word2.charAt(j - 1) ? 0: 1));
                prev = tmp;
            }
        }
        return d[word2.length()];
    }
    private int help0(String word1, String word2)
    {
        if(word1 == null && word2 == null) return 0;
        if(word1 == null) return word2.length();
        if(word2 == null) return word1.length();
        
        int []current = new int[word2.length()+1];
        for(int i = 0; i <= word2.length(); i++)
        {
            current[i] = i;
        }
        
       // int prev = -1;
        for(int row = 1; row <= word1.length(); row++)
        {
            int prev = current[0];
            current[0] = row;
            for(int col = 1; col <= word2.length(); col++)
            {
                int temp = current[col];
                current[col] = Math.min(current[col], current[col-1]) + 1;
                current[col] = Math.min(current[col], prev + (word1.charAt(row-1) == word2.charAt(col-1) ? 0 : 1) );
           
                prev = temp;
            }
        }
        
        return current[word2.length()];
    }
    
    private int help1(String word1, String word2)
    {
        if(word1 == null && word2 == null) return 0;
        if(word1 == null) return word2.length();
        if(word2 == null) return word1.length();
        
        int []current = new int[word2.length()+1];
        int []next = new int[word2.length()+1];
        for(int i = 0; i <= word2.length(); i++)
        {
            current[i] = i;
        }
        
        for(int row = 1; row <= word1.length(); row++)
        {
            for(int col = 0; col <= word2.length(); col++)
            {
                if(col == 0){
                    next[col] = current[col]+1;
                }
                else if(word1.charAt(row-1) == word2.charAt(col-1))
                {
                    next[col] = current[col-1];
                }else{
                    next[col] = Math.min(Math.min(current[col], next[col-1]), current[col-1]) + 1;
                }
            }
           System.arraycopy(next,  0,current, 0, next.length);
        }
        
        return current[word2.length()];
    }
    
    private int help2(String word1, String word2)
    {
        if(word1 == null && word2 == null) return 0;
        if(word1 == null) return word2.length();
        if(word2 == null) return word1.length();
        
        int [][]dp = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word2.length(); i++)
        {
            dp[0][i] = i;
        }
        
        for(int i = 0; i <= word1.length(); i++)
        {
            dp[i][0] = i;
        }
        
        for(int row = 1; row <= word1.length(); row++)
        {
            for(int col = 1; col <= word2.length(); col++)
            {
                if(word1.charAt(row-1) == word2.charAt(col-1))
                {
                    dp[row][col] = dp[row-1][col-1];
                }else{
                    dp[row][col] = Math.min(Math.min(dp[row-1][col], dp[row][col-1]), dp[row-1][col-1]) + 1;
                }
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}