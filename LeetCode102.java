/*
Binary Tree Level Order Traversal My Submissions Question
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>ans = new ArrayList<List<Integer>>();
        if(root == null){
            return ans;
        }
        
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        int currCount = 1;
        int nextCount = 0;
        
        while(!q.isEmpty()){
            
            List<Integer> newLevel = new ArrayList<Integer>();
            while(currCount > 0){
                TreeNode top = q.remove();
                currCount--;
                newLevel.add(top.val);
                
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
            ans.add(newLevel);
        }
        
        return ans;
    }
}