/*
Same Tree My Submissions Question
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
      //  return isSame(p, q);
        
        return bfs(p, q);
    }
    
    private boolean isSame(TreeNode p, TreeNode q){
        if(p == null){
            return q == null;
        }
        
        if( p != null && q == null){
            return false;
        }
        
        if(p.val != q.val){
            return false;
        }
        
        return isSame(p.left, q.left) && isSame(p.right, q.right);
    }
    
    private boolean bfs(TreeNode p, TreeNode q){
        
        if( p == null){
            return q == null;
        }
        
        if(p != null && q == null){
            return false;
        }
        
        if(p.val != q.val){
            return false;
        }
        
        Queue<TreeNode> l = new ArrayDeque<TreeNode>();
        Queue<TreeNode> r = new ArrayDeque<TreeNode>();
        l.add(p);
        r.add(q);
        
        while(!l.isEmpty() && !r.isEmpty()){
            TreeNode lTop = l.remove();
            TreeNode rTop = r.remove();
            
            if(lTop.val != rTop.val){
                return false;
            }else if(lTop.left == null && rTop.left != null){
                return false;
            }else if(lTop.left != null && rTop.left == null){
                return false;
            }else if(lTop.right == null && rTop.right != null){
                return false;
            }else if(lTop.right != null && rTop.right == null){
                return false;
            }
            
            if(lTop.left != null){
                l.add(lTop.left);
            }
            if(lTop.right != null){
                l.add(lTop.right);
            }
            
            if(rTop.left != null){
                r.add(rTop.left);
            }
            if(rTop.right != null){
                r.add(rTop.right);
            }
        }
        
        return true;
    }
}