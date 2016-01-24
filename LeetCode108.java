/*
108. Convert Sorted Array to Binary Search Tree My Submissions Question
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return help(nums);
    }
    
    private TreeNode help(int[] nums)
    {
        if(nums == null || nums.length == 0) return null;
        
        TreeNode res = dfs(nums, 0, nums.length-1);
        return res;
    }
    
    private TreeNode dfs(int [] nums, int l, int r)
    {
        if(l > r) return null;
        if(l==r){return new TreeNode(nums[l]);}
        
        int m = l + ((r-l) >> 1);
        TreeNode root = new TreeNode(nums[m]);
        root.left = dfs(nums, l, m - 1);
        root.right = dfs(nums, m+1, r);
        
        return root;
    }
}