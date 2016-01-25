/*
129. Sum Root to Leaf Numbers My Submissions Question
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
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
    public int sumNumbers(TreeNode root) {
        return help(root);
    }
    
    private int help(TreeNode root){
        if(root == null) return 0;
        List<Integer> res = new ArrayList<Integer>(1);
        res.add(0);
        
        dfs0(root, 0, res);
        return res.get(0);
    }
    
    private void dfs(TreeNode root, int currVal, List<Integer> res)
    {
        if(root.left == null && root.right == null){
            res.set(0,currVal*10 + root.val + res.get(0));
            return;
        }
        
        if(root.left != null){
        dfs(root.left, currVal*10 + root.val, res);
        }
        if(root.right != null){
        dfs(root.right, currVal*10 + root.val, res);
        }
    }
}