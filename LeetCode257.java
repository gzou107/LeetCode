/*
Binary Tree Paths My Submissions Question
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<String>();
        List<Integer> path = new ArrayList<Integer>();
        if(root == null){
            return ans;
        }
        tranverseTree(root, path, ans);
        return ans;
    }
    
    private static void tranverseTree(TreeNode root, List<Integer> path, List<String> ans){
       if(root == null){
           return;
       }
        if(root.left == null && root.right == null){
            path.add(root.val);
            StringBuilder onePath = new StringBuilder();
            onePath.append(String.valueOf(path.get(0)));
            for(int i = 1; i < path.size(); i++){
                onePath.append("->").append(String.valueOf(path.get(i)));
            }
            ans.add(onePath.toString());
            path.remove(path.size()-1);
            return;
        }
        path.add(root.val);
        tranverseTree(root.left, path, ans);
        tranverseTree(root.right,path, ans);
        path.remove(path.size()-1);
    }
}