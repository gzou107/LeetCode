/*
337. House Robber III My Submissions Question
Total Accepted: 1842 Total Submissions: 5005 Difficulty: Medium
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int rob(TreeNode root) {
        return help(root);
    }
    
    private class Wrapper
    {
        public int maxWithRoot;
        public int maxWithoutRoot;
        
        public Wrapper(int maxWithRoot, int maxWithoutRoot)
        {
            this.maxWithRoot = maxWithRoot;
            this.maxWithoutRoot = maxWithoutRoot;
        }
    }
    
    public int help(TreeNode root)
    {
        if(root == null) return 0;
        
        Wrapper result = dfs(root);
        
        return result.maxWithRoot > result.maxWithoutRoot ? result.maxWithRoot : result.maxWithoutRoot;
    }
    
    private Wrapper dfs(TreeNode root)
    {
        if(root == null){
            return new Wrapper(0, 0);
        }
        
        if(root.left == null && root.right == null){
            return new Wrapper(root.val, 0);
        }
        
        Wrapper left = dfs(root.left);
        Wrapper right = dfs(root.right);
        
        int rootSelected = root.val + left.maxWithoutRoot + right.maxWithoutRoot;
        int rootNotSelected = Math.max(left.maxWithRoot, left.maxWithoutRoot) + Math.max(right.maxWithRoot, right.maxWithoutRoot);
        
        return new Wrapper(rootSelected, rootNotSelected);
    }
}