/*
106. Construct Binary Tree from Inorder and Postorder Traversal My Submissions Question
Given inorder and postorder traversal of a tree, construct the binary tree.

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return help0(inorder, postorder);
    }
    
    private TreeNode help0(int[]inorder, int[]postorder)
    {
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++)
        {
            cache.put(inorder[i], i);
        }
        
        return dfs0(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, cache);
    }
    
    private TreeNode dfs0(int[]inorder, int il, int ir, int[]postorder, int pl, int pr, Map<Integer, Integer>cache)
    {
        if(pl > pr) return null;
        if(pl == pr) return new TreeNode(postorder[pl]);
        
        TreeNode root = new TreeNode(postorder[pr]);
        int idx = cache.get(postorder[pr]);
        root.left = dfs0(inorder, il, idx-1, postorder, pl, pl + idx - il - 1, cache);
        root.right = dfs0(inorder, idx+1, ir, postorder, pl+idx -il, pr-1, cache);
        return root;
    }
}