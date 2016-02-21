/*
95. Unique Binary Search Trees II My Submissions Question
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
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

    public List<TreeNode> generateTrees(int n) {
        
        if(n == 0) return new ArrayList<TreeNode>();
        return help0(1, n);
    }
    // https://leetcode.com/discuss/33003/java-recursive-solution-straight-forward
    private List<TreeNode> help0(int low, int high)
    {
        List<TreeNode> res = new ArrayList<>();
        if(low > high){
            res.add(null);
            return res;
        }
        
        for(int root = low; root <= high; root++)
        {
            List<TreeNode> l = help0(low, root-1);
            List<TreeNode> r = help0(root+1, high);
            for(int i = 0; i < l.size(); i++)
            {
                for(int j = 0; j < r.size(); j++)
                {
                    TreeNode ro = new TreeNode(root);
                    ro.left = l.get(i);
                    ro.right = r.get(j);
                    res.add(ro);
                }
            }
        }
        return res;
    }
}