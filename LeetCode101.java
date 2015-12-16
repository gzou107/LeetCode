/*
Symmetric Tree My Submissions Question
Total Accepted: 84656 Total Submissions: 259371 Difficulty: Easy
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
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
    public boolean isSymmetric(TreeNode root) {
        
       // return isMirrorHelper(root);
        
        return bfs(root);
    }
    
    public boolean isMirrorHelper(TreeNode root){
        if(root == null) return true;
        
        return isMirror(root.left, root.right);
    }
    public boolean isMirror(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }else if(left == null && right != null){
            return false;
        }else if(left != null && right == null){
            return false;
        }else if(left.val != right.val){
            return false;
        }else{
            return isMirror(left.right, right.left) && isMirror(left.left, right.right);
        }
    }
    
    public boolean bfs(TreeNode root){
        if(root == null) return true;
        
        if(root.left == null && root.right == null) {
            return true;
        }else if(root.left == null && root.right != null){
            return false;
        }else if(root.left != null && root.right == null){
            return false;
        }else if(root.left.val != root.right.val){
            return false;
        }else{
            
            Queue<TreeNode> l = new ArrayDeque<TreeNode>();
            l.add(root.left);
            Queue<TreeNode> r = new ArrayDeque<TreeNode>();
            r.add(root.right);
            
            while(!l.isEmpty() && !r.isEmpty()){
                TreeNode lTop = l.remove();
                TreeNode rTop = r.remove();
                
                if(lTop.left == null && rTop.right != null){
                    return false;
                }else if(lTop.left != null && rTop.right == null){
                    return false;
                }else if(lTop.left != null && rTop.right != null && lTop.left.val != rTop.right.val){
                    return false;
                }else if(lTop.right == null && rTop.left != null){
                    return false;
                }else if(lTop.right != null && rTop.left == null){
                    return false;
                }else if(lTop.right != null && rTop.left != null && lTop.right.val != rTop.left.val){
                    return false;
                }
                
                // now we move to their children
                // and their values equals, check its non-null before adding to the queue
                if(lTop.left != null){
                    l.add(lTop.left);
                }
                if(lTop.right != null){
                    l.add(lTop.right);
                }
                
                if(rTop.right != null){
                    r.add(rTop.right);
                }
                if(rTop.left != null){
                    r.add(rTop.left);
                }
            }
            
            return true;
        }
        
    }
}