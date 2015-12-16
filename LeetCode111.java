/*
Minimum Depth of Binary Tree My Submissions Question
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
    public int minDepth(TreeNode root) {
        
        return bfs(root);
        /*
        if(root == null) return 0;
        
        if(root.left == null && root.right != null){ 
            return 1 + minDepth(root.right);
        }else if(root.left != null && root.right == null){
            return 1 + minDepth(root.left);
        }else if(root.left == null && root.right == null){
            return 1;
        }else{
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
        */
    }
    
    private static int bfs(TreeNode root){
        
        if(root == null) return 0;
        
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        int currCount  = 1;
        int nextCount = 0;
        int depth = 1;
        
        while(!q.isEmpty()){
            
            // handle all the nodes of level depth
            while(currCount > 0){
                TreeNode top = q.remove();
                currCount--;
                
                if(top.left == null && top.right == null){
                    return depth;
                }
                
                if(top.left != null){
                    q.add(top.left);
                    nextCount++;
                }
                
                if(top.right != null){
                    q.add(top.right);
                    nextCount++;
                }
            }
            
            currCount = nextCount;
            nextCount = 0;
            
            depth++;
        }
        
        return depth;
    }
}