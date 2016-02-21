/*
156. Binary Tree Upside Down My Submissions Question
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return help(root);
    }
    
    private class WrapperTreeNode
    {
        public TreeNode root;
        public TreeNode right;
        
        public WrapperTreeNode(TreeNode root, TreeNode right)
        {
            this.root = root;
            this.right = right;
        }
    }
    
    private TreeNode help(TreeNode root)
    {
        if(root == null) return root;
        
        WrapperTreeNode result = dfs(root);
        return result.root;
    }
    
    private WrapperTreeNode dfs(TreeNode root)
    {
        if(root == null || (root.left == null && root.right == null))
        {
            return new WrapperTreeNode(root, null);
        }
        
        WrapperTreeNode l = dfs(root.left);
        
        if(l.root == null) return l;
        
        if(l.right == null)
        {
            l.root.right = root;
            l.root.left = root.right;
        }else{
            l.right.right = root;
            l.right.left = root.right;
        }
        root.left = null;
        root.right = null;
        
        return new WrapperTreeNode(l.root, root);
    }
}