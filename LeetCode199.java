/*
199. Binary Tree Right Side View My Submissions Question
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
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
    public List<Integer> rightSideView(TreeNode root) {
        
       // return helper1(root);
       return helper2(root);
    }
    
    private List<Integer> helper1(TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        int curr = 1;
        int next = 0;
        
        while(!q.isEmpty()){
            
            while(curr>0){
                TreeNode t = q.remove();
                curr--;
                if(curr == 0){
                    res.add(t.val);
                }
                
                if(t.left != null){
                    q.add(t.left);
                    next++;
                }
                
                if(t.right != null){
                    q.add(t.right);
                    next++;
                }
            }
            
            curr = next;
            next = 0;
        }
        
        return res;
    }
    
    private List<Integer> helper2(TreeNode root){
        
        List<Integer> ans = new ArrayList<Integer>();
        dfs(root, ans, 0);
        return ans;
    }
    
    void dfs(TreeNode root, List<Integer>ans, int depth){
        if(root == null){
            return;
        }
        
        if(depth == ans.size()){
            ans.add(root.val);
        }
        
        // we  need do right first as we want to do the right side view
        dfs(root.right, ans,depth+1);
        dfs(root.left, ans, depth+1);
    }
}