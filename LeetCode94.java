/*
94. Binary Tree Inorder Traversal My Submissions Question
Total Accepted: 115962 Total Submissions: 296048 Difficulty: Medium
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
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
    public List<Integer> inorderTraversal(TreeNode root) {
        return help(root);
    }
    
    private List<Integer> help(TreeNode root)
    {
        List<Integer> result = new ArrayList<Integer>();
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        while(root != null || !s.isEmpty())
        {
            if(root != null){
                s.push(root);
                root = root.left;
            }else{
                root = s.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        
        return result;
    }
}