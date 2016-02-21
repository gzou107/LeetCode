/*
96. Unique Binary Search Trees My Submissions Question
Total Accepted: 75385 Total Submissions: 204714 Difficulty: Medium
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/   

public class Solution {
    public int numTrees(int n) {
        //return help0(n, new int[n]);
        return help1(n);
    }
    
    private int help0(int n, int[]cache)
    {
        if( n <= 1) return 1;
        if(cache[n-1] != 0) return cache[n-1];
        
        int count = 0;
        for(int root = 1; root <=n; root++)
        {
            count += help0(root-1, cache)*help0(n-root, cache);
        }
        cache[n-1] = count;
        return count;
    }
    // https://leetcode.com/discuss/63693/short-and-simple-java-solution-with-intuitive-explanation
    private int help1(int n)
    {
        if(n <= 1) return 1;
        
        int []cache = new int[n+1];
        cache[0] = 1;
        
        for(int root = 1; root <= n; root++)
        {
            int count = 0;
            for(int i = 0; i < root; i++)
            {
                int left = cache[i];
                int right = cache[root - i -1];
                count += left*right;
            }
            cache[root] = count;
        }
        return cache[n];
    }
}