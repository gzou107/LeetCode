/*
105. Construct Binary Tree from Preorder and Inorder Traversal My Submissions Question
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return help0(preorder, inorder);
       // return help(preorder, inorder);
    }
    
    private TreeNode help0(int[]preorder, int[]inorder)
    {
        Map<Integer, Integer> cache = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
        {
            cache.put(inorder[i], i);
        }
        
        return dfs0(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, cache);
    }
    
    private TreeNode dfs0(int[]preorder, int pl, int pr, int[]inorder, int il, int ir, Map<Integer, Integer> cache)
    {
        if(pl > pr) return null;
        if(pl == pr) return new TreeNode(preorder[pl]);
        
        TreeNode root = new TreeNode(preorder[pl]);
        int idx = cache.get(preorder[pl]);
        root.left = dfs0(preorder, pl+1, pl+1 + idx - il -1, inorder, il, idx-1, cache);
        root.right = dfs0(preorder, pl+1+idx-il, pr, inorder, idx+1, ir, cache);
        return root;
    }
    
    private TreeNode help(int[]preorder, int[]inorder)
    {
        return dfs(preorder, 0, preorder.length-1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode dfs(int[]preorder, int pl, int pr, int[]inorder, int il, int ir)
    {
        if(pl > pr) return null;
        if(pl == pr) return new TreeNode(preorder[pl]);
        
        TreeNode root = new TreeNode(preorder[pl]);
        int idx = getRootPos(inorder, il, ir, preorder[pl]);
        // [il, idx-1] belongs to left tree, hence its length == idx-1 -il+1 = idx - il
        root.left = dfs(preorder, pl+1, pl+1 + idx - il-1, inorder, il, idx-1);
        root.right = dfs(preorder,pl+1 + idx - il, pr, inorder, idx+1, ir);
        return root;
    }
    
    private int getRootPos(int[]inorder, int l, int r, int root)
    {
        for(int i = l; i <= r; i++)
        {
            if(inorder[i] == root){
                return i;
            }
        }
        return -1;
    }
}