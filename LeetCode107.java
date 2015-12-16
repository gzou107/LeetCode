/*
Binary Tree Level Order Traversal II My Submissions Question
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
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
                newLevel.add(top.val);
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
            
            ans.add(newLevel);
        }
        
        // now reverse the order
        int i = 0;
        int j = ans.size()-1;
        while(i < j){
            List<Integer> t = ans.get(i);
            ans.set(i, ans.get(j));
            ans.set(j, t);
            i++;
            j--;
        }
        
        return ans;
    }
}