/*
145. Binary Tree Postorder Traversal My Submissions Question
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

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
    public List<Integer> postorderTraversal(TreeNode root) {
        return helper1(root);
    }
    
    public List<Integer> helper1(TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode prev = null;
        
        while(!s.isEmpty()){
             TreeNode top = s.peek();
             
             if(prev == null || prev.left == top || prev.right == top){
                 if(top.left != null){
                     s.push(top.left);
                 }else if(top.right != null){
                     s.push(top.right);
                 }
             }else if(top.left == prev){
                 if(top.right != null){
                     s.push(top.right);
                 }
             }else { // two cases here, either prev == top or top.right == prev, which means we're ready to process this node
                 res.add(top.val);
                 s.pop();
             }
             prev = top;
        }
        
        return res;
    }
}