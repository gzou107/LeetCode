/*
114. Flatten Binary Tree to Linked List My Submissions Question
Total Accepted: 77541 Total Submissions: 252221 Difficulty: Medium
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.
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
    
    private class Wrapper
    {
        public TreeNode head;
        public TreeNode tail;
        
        public Wrapper(TreeNode h, TreeNode t)
        {
            this.head = h;
            this.tail = t;
        }
    }
    
    public void flatten(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return;
        
        help(root);
    }
    
    private void help(TreeNode root)
    {
        dfs(root);
    }
    
    private Wrapper dfs(TreeNode root)
    {
        if(root == null){
            return new Wrapper(null, null);
        }
        
        Wrapper l = dfs(root.left);
        
        Wrapper r = dfs(root.right);
        
        root.left = null;
        root.right = l.head == null? r.head: l.head;
        if(l.tail != null){
            l.tail.right = r.head;
        }
        
        return new Wrapper(root, r.tail== null? (l.tail == null? root :l.tail) : r.tail);
    }
}