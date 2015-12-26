/*
222. Count Complete Tree Nodes My Submissions Question
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
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
    public int countNodes(TreeNode root) {
      //  return helper1(root);
        
        return helper2(root);
    }
    
    private int helper1(TreeNode root){
        if(root == null) return 0;
        
        int lH = getLHeight(root);
        int rH = getRHeight(root);
        
        if( lH == rH){
            return (1 <<lH)-1;
        }else{
          return 1 + helper1(root.left) + helper1(root.right);
        }
    }
    
    private int getLHeight(TreeNode r){
        int ans = 0;
        while( r != null){
            ans++;
            r = r.left;
        }
        return ans;
    }
    
    private int getRHeight(TreeNode r){
        int ans = 0;
        while( r != null){
            ans++;
            r = r.right;
        }
        return ans;
    }
    
    private int helper2(TreeNode root){
        
        if(root == null){
            return 0;
        }
        
        int height = 0;
        TreeNode t = root;
        while( t != null){
            height++;
            t = t.left;
        }
        
        int level = height - 2;
        int count = 0;
        TreeNode temp = root;
        while(level >= 0)
        {
            TreeNode left = temp.left;
            for(int i = 0; i < level; i++)
            {
                left = left.right;
            }
            
            
            if(left != null)
            {
                // now we know all the left subtree is complete, hence we can calculate the nodes count
                count += (1 << level);
                temp = temp.right;
            }else
            {
                temp = temp.left;
            }
            
            level--;
        }
        
        //now we return the total count
         if(temp != null){
             count++;
         }
         return (1 << (height - 1)) + count - 1;
    }
}