/*
98. Validate Binary Search Tree My Submissions Question
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
    public boolean isValidBST(TreeNode root) {
        //return helper0(root);
        return driver0(root);
    }
    
    private boolean helper0(TreeNode root)
    {
        if(root == null) return true;
        
        TreeNode prev = null;
        Stack<TreeNode> s = new Stack<TreeNode>();
        while( root != null || !s.isEmpty())
        {
            while(root != null)
            {
                s.push(root);
                root = root.left;
            }
            
            TreeNode top = s.pop();
            if(prev != null)
            {
                if(prev.val >= top.val){
                    return false;
                }
            }
            prev = top;
            root = top.right;
        }
        
        return true;
        
    }
    private boolean driver0(TreeNode root)
    {
        if(root == null){
            return true;
        }
        
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean helper(TreeNode root, long lower, long upper)
    {
        if(root == null) return true;
        
        if(root.val <= lower || root.val >= upper) return false;
        
        return helper(root.left, lower, root.val) && helper(root.right, root.val, upper);
    }
}