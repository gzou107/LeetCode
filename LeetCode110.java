/*
Balanced Binary Tree My Submissions Question
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
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
    public boolean isBalanced(TreeNode root) {
        if(getH(root)== -1){
            return false;
        }
        
        return true;
    }
    
    private static int getH(TreeNode root){
        if(root == null) return 0;
        
        int lH = getH(root.left);
        int rH = getH(root.right);
        
        // left, or right, or node itself not balance
        if(lH == -1 || rH == -1 || Math.abs(lH - rH) > 1){
            return -1;
        }
        
        // return its depth: depth equals to longest path
        return 1 + Math.max(lH, rH);
    }
}