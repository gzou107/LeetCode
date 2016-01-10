/*
250. Count Univalue Subtrees My Submissions Question
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
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
    public int countUnivalSubtrees(TreeNode root) {
        int [] count = new int[1];
        count[0] = 0;
        helper0(root, count);
        
        return count[0];
        
        //return helper(root, new HashMap<TreeNode, Boolean>());
    }
    
    private boolean helper0(TreeNode root, int[]count)
    {
        if(root == null)
        {
            return true;
        }
        
        Boolean l = helper0(root.left, count);
        Boolean r = helper0(root.right, count);
        
        if( l && r)
        {
            if(root.left != null && root.left.val != root.val) return false;
            
            if(root.right != null && root.right.val != root.val) return false;
            
            count[0]++;
            
            return true;
        }
        
        return false;
    }
    private int helper(TreeNode root, Map<TreeNode, Boolean> m)
    {
        if(root == null){
            return 0;
        }
        
        int l = helper(root.left, m);
        int r = helper(root.right, m);
        
        if(root.left != null)
        {
            if(m.containsKey(root.left))
            {
                m.put(root, false);
                return l + r;
            }
        }
        
        if(root.right != null)
        {
            if(m.containsKey(root.right))
            {
                m.put(root, false);
                return l + r;
            }
        }
        
        if(root.left == null && root.right == null)
        {
            return 1;
        }
        
        if(root.left == null && root.right != null)
        {
            if(root.val == root.right.val)
            {
                return r + 1;
            }else{
                m.put(root, false);
                return r;
            }
        }
        
        if(root.left != null && root.right == null)
        {
            if(root.left.val == root.val){
                return l + 1;
            }else{
                m.put(root, false);
                return l;
            }
        }
        
        if(root.left != null && root.right != null)
        {
            if(root.left.val == root.val && root.right.val == root.val)
            {
                return l + r + 1;
            }else{
                m.put(root, false);
                return l + r;
            }
        }
        
        return -1;
    }
}