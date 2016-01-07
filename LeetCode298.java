/*
298. Binary Tree Longest Consecutive Sequence My Submissions Question
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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
    
    int gMax = 0;
    public int longestConsecutive(TreeNode root) {
       
        //return (root==null)?0:Math.max(dfs(root.left, 1, root.val), dfs(root.right, 1, root.val));
        
        goodhelper(root);
        return gMax;
        /*
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        helper(root, res);
        return res.get(0);
        */
    }

    public int dfs(TreeNode root, int count, int val){
        if(root==null) return count;
        count = (root.val - val == 1)?count+1:1;// we update the count before visiting the children
        int left = dfs(root.left, count, root.val);
        int right = dfs(root.right, count, root.val);
        return Math.max(Math.max(left, right), count);
    }
    
    // !!! This implementation is wrong
    // thinking this example: 4,5,#,8#,9,#
    // the below algorithm returns 3, while it should return 2
    // to fix it, we define the return variable to be the one starting at the root
    // we take the maxL only if it's continuous
    public int Bug_helper(TreeNode root)
    {
        if(root == null) return 0;
        
        int maxL = Bug_helper(root.left);
        int maxR = Bug_helper(root.right);
        
        if(root.left != null && root.val + 1 == root.left.val){
            maxL += 1;
        }
        
        if(root.right != null && root.val + 1 == root.right.val){
            maxR += 1;
        }
        
        return Math.max(Math.max(maxL, maxR), 1);
    }
    // stackoverflow ???
    public int helper(TreeNode root, List<Integer> res)
    {
        if(root == null) return 0;
        
        int maxL = helper(root.left, res);
        int maxR = helper(root.right, res);
        int newL = maxL;
        int newR = maxR;
        if(root.left != null && root.val + 1 == root.left.val){
            newL++;
        }
        
        if(root.right != null && root.val + 1 == root.right.val){
           newR++;
        }
        
        if(newL > maxL){
            if(newR > maxR){
                int t = Math.max(newL, newR);
                res.set(0,res.get(0) < t? t : res.get(0)); 
                return t;
            }else{
                res.set(0,res.get(0) < newL? newL : res.get(0)); 
                return newL;
            }
        }
        
        if(newR > maxR){
            res.set(0,res.get(0) < newR? newR : res.get(0)); 
            return newR;
        }else{
             res.set(0,res.get(0) < 1? 1 : res.get(0)); 
            return 1;
        }
    }
    
    // this works
    public int goodhelper(TreeNode root)
    {
        if(root == null) return 0;
        
        int maxL = goodhelper(root.left);
        int maxR = goodhelper(root.right);
        int newL = maxL;
        int newR = maxR;
        if(root.left != null && root.val + 1 == root.left.val){
            newL++;
        }
        
        if(root.right != null && root.val + 1 == root.right.val){
           newR++;
        }
        
        if(newL > maxL){
            if(newR > maxR){
                int t = Math.max(newL, newR);
                gMax = gMax < t ? t : gMax;
                return t;
            }else{
                 gMax = gMax < newL ? newL : gMax;
                return newL;
            }
        }
        
        if(newR > maxR){
             gMax = gMax < newR ? newR : gMax;
            return newR;
        }else{
             gMax = gMax < 1 ? 1 : gMax;
            return 1;
        }
    }
}