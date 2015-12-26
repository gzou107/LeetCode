/*
144. Binary Tree Preorder Traversal My Submissions Question
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> preorderTraversal(TreeNode root) {
        return helper1(root);
    }
    
    private List<Integer> helper1(TreeNode root){
        
        List<Integer> ans = new ArrayList<Integer>();
        if(root == null){
            return ans;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        
        while(!s.isEmpty()){
            TreeNode top = s.pop();
            ans.add(top.val);
            
            if(top.right != null){
                s.push(top.right);
            }
            
            if(top.left != null){
                s.push(top.left);
            }
        }
        
        return ans;
    }
}