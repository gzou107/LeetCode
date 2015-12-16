/*
Path Sum My Submissions Question
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Subscribe to see which companies asked this question
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
    
    public boolean hasPathSum(TreeNode root, int sum) {
        
        return bfs(root, sum);
       // return helper2(root, sum);
       // if(root == null) return false;
        
       // return helper(root, sum);
    }
    
    private static boolean helper(TreeNode root, int sum)
    {
        if(root == null){
            if(sum == 0) return true;
            else return false;
        }
         
         // we can't use next line to represent below 4 cases, e.g., tree of 1,null, 2
         // and the sum is 1, we should return false, the below line will return true
         // return helper(root.left, sum - root.val) || helper(root.right, sum - root.val);
        if(root.left == null && root.right != null) {
            return helper(root.right, sum - root.val);
        }else if(root.left != null && root.right == null) {
            return helper(root.left, sum - root.val);
        }else if(root.left == null && root.right == null){
            return root.val == sum;
        }else{
            return helper(root.left, sum - root.val) || helper(root.right, sum - root.val);
        }
    }
    
    private static boolean helper2(TreeNode root, int sum){
        if(root == null) return false;
        
        if(root.left == null && root.right == null && root.val == sum) return true;
        
        return helper2(root.left, sum - root.val) || helper2(root.right, sum - root.val);
    }
    
    private static boolean bfs(TreeNode root, int sum){
        
        if(root == null) return false;
        
        Deque<TreeNode> q = new ArrayDeque<TreeNode>();
        Deque<Integer> v = new ArrayDeque<Integer>();
        q.add(root);
        v.add(root.val);
        
        while(!q.isEmpty()){
            TreeNode top = q.removeFirst();
            int sumV = v.removeFirst();
            
            if(top.left == null && top.right == null && sumV == sum){
                return true;
            }
            
            if(top.left != null){
                q.addLast(top.left);
                v.addLast(top.left.val + sumV);
            }
            
            if(top.right != null){
                q.addLast(top.right);
                v.addLast(top.right.val + sumV);
            }
        }
        
        return false;
    }
}