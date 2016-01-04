/*
270. Closest Binary Search Tree Value My Submissions Question
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
    public int closestValue(TreeNode root, double target) {
        return helper(root, target);
       // return helper1(root,target);
    }
    
    public int helper(TreeNode root, double target)
    {
        int closest = root.val;
        while(root != null)
        {
            closest = Math.abs(root.val - target) < Math.abs(closest - target) ? root.val : closest;
            
            root = root.val < target ? root.right : root.left;
        }
        
        return closest;
    }
    
    
    private int helper1(TreeNode root, double target){
        TreeNode preSmaller = null;
        TreeNode preBigger = null;
        
        while(root != null)
        {
            if(root.val == target){
                return (int)target;
            }else if(root.val < target)
            {
                preSmaller = root;
                root = root.right;
            }else{
                preBigger = root;
                root = root.left;
            }
        }
        
        if(preSmaller == null){
            return preBigger.val;
        }else if(preBigger == null){
            return preSmaller.val;
        }else{
            if(target - preSmaller.val < preBigger.val - target)
            {
                return preSmaller.val;
            }
            return preBigger.val;
        }
    }
}