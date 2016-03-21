/*
99. Recover Binary Search Tree My Submissions Question
Total Accepted: 49207 Total Submissions: 188751 Difficulty: Hard
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
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
    public void recoverTree(TreeNode root) {
        help(root);
    }
    
    private void help(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) return;
        
        TreeNode first = null;
        TreeNode second = null;
        
        TreeNode current = root;
        TreeNode prev = null;
        
        while(current != null)
        {
            // morris inorder transverse
            if(current.left == null){
                //we're safe to process this node
                if(prev != null && prev.val > current.val)
                {
                    if(first == null){
                        first = prev;
                    }
                    second = current;
                }
                
                prev = current;
                current = current.right;
            }else{
                //now we find the in-order visit precessor
                TreeNode precessor = current.left;
                while(precessor.right != null && precessor.right != current){
                    precessor = precessor.right;
                }
                
                if(precessor.right == null){
                    precessor.right = current;
                    current = current.left;
                }else{
                    // we should break the link now
                    precessor.right = null;
                    //we're ready to process current node now
                     if(prev != null && prev.val > current.val)
                     {
                        if(first == null){
                            first = prev;
                        }
                        second = current;
                    }
                    
                    prev = current;
                    current = current.right;
                }
            }
        }
        
        if(first != null && second != null){
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }
}