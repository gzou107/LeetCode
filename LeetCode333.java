/*
333. Largest BST Subtree My Submissions Question
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
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
    private int max = -1;
     
    public int largestBSTSubtree(TreeNode root) {
        return help(root);
    }
    
    private int help(TreeNode r)
    {
        if(r == null){
            return 0;
        }
        
        wrapper res = dfs(r);
        return max;
    }
   
    private class wrapper
    {
        public boolean isBst;
        public long maxValue;
        public long minValue;
        public int count;
        public wrapper(boolean isBst, long min, long max, int count){
            this.isBst = isBst;
            this.minValue = min;
            this.maxValue = max;
            this.count = count;
        }
    }
    
    private wrapper dfs(TreeNode root)
    {
        if(root.left == null && root.right == null)
        {
            max = Math.max(max, 1);
            return new wrapper(true, root.val, root.val, 1);
        }
        
        wrapper l = null;
        wrapper r = null;
        if(root.left != null)
        {
            l = dfs(root.left);
        }
        if(root.right != null)
        {
            r = dfs(root.right);
        }
        
        if( l != null && r != null)
        {
            if(l.isBst && r.isBst && root.val > l.maxValue && root.val <r.minValue)
             {
                int candidate = l.count + r.count+1;
                max = Math.max(max, candidate);
                return new wrapper(true, l.minValue, r.maxValue, candidate);
            }else
            {
                return new wrapper(false, 0, 0, 0);
            }
        }else if(l == null && r != null)
        {
            if(r.isBst && root.val < r.minValue)
            {
                int candidate = r.count + 1;
                max = Math.max(max, candidate);
                return new wrapper(true, root.val, r.maxValue, candidate);
            }else{
                return new wrapper(false, 0, 0, 0);
            }
        }else if( l!= null && r == null){
            if(l.isBst && root.val > l.maxValue){
                int candidate = l.count + 1;
                max = Math.max(max, candidate);
                return new wrapper(true, l.minValue, root.val, candidate);
            }else{
                return new wrapper(false, 0, 0 ,0);
            }
        }
        return new wrapper(false, 0, 0 ,0);
    }
}