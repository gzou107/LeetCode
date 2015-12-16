/*
Maximum Depth of Binary Tree My Submissions Question
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
    public int maxDepth(TreeNode root) {
        
       // return getMax(root);
        
       // return bfs(root);
        
        return dfsHelper(root);
    }
    
    private static int getMax(TreeNode root){
        if(root == null) return 0;
        
        return 1 + Math.max(getMax(root.left), getMax(root.right));
    }
    
    private static int bfs(TreeNode root){
        
        if(root == null) return 0;
        
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        int currCount = 1;
        int nextCount = 0;
        int depth = 0;
        
        while(!q.isEmpty()){
            
            while(currCount > 0){
                TreeNode top = q.remove();
                currCount--;
                
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
    
    private static int dfsHelper(TreeNode root){
        List<Integer> max = new ArrayList<Integer>();
        int depth = 0;
        dfs(root, max, depth);
        
        return max.get(0);
    }
    private static void dfs(TreeNode root, List<Integer> max, int depth){
        
        if(root == null)
        {
            if(max.size() == 0){
                max.add(depth); // we need add depth here, instead of 0, or 1
            }else if(max.get(0) < depth)
            {
                max.set(0, depth);
            }
            return;
        }
        
        dfs(root.left, max, depth+1);
        dfs(root.right,max, depth+1);
    }
}