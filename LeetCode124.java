/*
124. Binary Tree Maximum Path Sum My Submissions Question
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
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
    private int gMax = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        // help(root);
        // return gMax;
        int [] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        help0(root, res);
        return res[0];
    }
    
    private int help(TreeNode root)
    {
        if(root == null) return 0;
        
        int left = help(root.left);
        int right = help(root.right);
        
        int lMax = root.val + (left >0? left : 0) + (right>0? right : 0);
        
        if(lMax  > gMax){
            gMax = lMax;
        }
        
        int bigger = Math.max(left, right);
        // we can only return one branch, NOT both branch
        return bigger > 0? root.val + bigger : root.val;
    }
    
     private int help0(TreeNode root, int[] res)
    {
        if(root == null) return 0;
        
        int left = help0(root.left, res);
        int right = help0(root.right, res);
        
        int lMax = root.val + (left >0? left : 0) + (right>0? right : 0);
        
        if(lMax  > res[0]){
            res[0] = lMax;
        }
        
        int bigger = Math.max(left, right);
        // we can only return one branch, NOT both branch
        return bigger > 0? root.val + bigger : root.val;
    }
    
    public class Solution {
    public int maxPathSum(TreeNode root) {
        int[] max = {Integer.MIN_VALUE};
        helper(root, max);
        return max[0];
    }

    /*
    要求路径上的点不可以是父子关系，即不可以相连接。
    比如：
    
          1
         / \
        2   3
       / \
      1   8
    Return 11(8 + 3).
    分析
    
    方法其实与上一题是一样的，只不过我们需要考虑多一点情况，对于某一个node而言，会有两种情况来算以该点为root的路径最大值，一是最大值包含该点，二是最大值不包含该点。同样对于helper function返回值而言也是，不尽需要返回包含该点的最大值，也需要返回不包含该点向下的最大值，因为这两个值父节点都会需要用。所以我们需要把helper function改成返回两个值。
    
    复杂度
    
    time: O(n), space: O(1)
    
    代码
    */
    public int[] helper(TreeNode node, int[] max) {
        // res[0]表示包含该点的向下路径最大值，res[1]表示不包含该点向下路径最大值
        int[] res = new int[2];

        if (node == null)
            return res;

        int[] left = helper(node.left, max);
        int[] right = helper(node.right, max);

        int leftMax = 0;
        int rightMax = 0;

        // 包含本身node, 由于最大值路径node不能连接，不可使用left[0]及right[0]
        leftMax = Math.max(leftMax, left[1]);
        rightMax = Math.max(rightMax, right[1]);
        max[0] = Math.max(max[0], node.val + leftMax + rightMax);
        res[0] = node.val + Math.max(leftMax, rightMax);

        // 不包含本身node, 左右向下路径无论包不包含左右子节点都可以需要考虑
        leftMax = 0;
        rightMax = 0;
        leftMax = Math.max(leftMax, Math.max(left[0], left[1]));
        rightMax = Math.max(rightMax, Math.max(right[0], right[1]));
        max[0] = Math.max(max[0], leftMax + rightMax);
        res[1] = Math.max(leftMax, rightMax);

        return res;
    }
}
}