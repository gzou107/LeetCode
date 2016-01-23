/*
113. Path Sum II My Submissions Question
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return help(root, sum);
    }
    
    private List<List<Integer>> help(TreeNode root, int sum)
    {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if(root == null){
            return ans;
        }
        
        List<Integer> candidate = new LinkedList<Integer>();
        dfs(root, sum, candidate, ans);
        return ans;
    }
    
    private void dfs(TreeNode root, int sum, List<Integer> candidate, List<List<Integer>>ans)
    {
        if(root.left == null && root.right == null)
        {
            if(sum == root.val){
                List<Integer> oneSol = new LinkedList<Integer>(candidate);
                oneSol.add(sum);
                ans.add(oneSol);
            }
            return;
        }
        
        candidate.add(root.val);
        
        if(root.left != null){
            dfs(root.left, sum - root.val, candidate, ans);
        }
        
        if(root.right != null){
            dfs(root.right, sum - root.val, candidate, ans);
        }
        candidate.remove(candidate.size()-1);
    }
}