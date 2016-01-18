/*
103. Binary Tree Zigzag Level Order Traversal My Submissions Question
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
       // return help1(root);
        return help0(root);
    }
    
    private List<List<Integer>> help0(TreeNode root)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean isOdd = true;
        
        while(!q.isEmpty())
        {
            List<Integer> level = new LinkedList<>();
            int size = q.size();
            for(int i = 0; i < size; i++)
            {
                TreeNode top = q.remove();
                
                if(isOdd){
                    level.add(top.val);
                }else{
                    level.add(0, top.val);
                }
                
                if(top.left != null)
                {
                    q.add(top.left);
                }
                if(top.right != null)
                {
                    q.add(top.right);
                }
            }
            isOdd = !isOdd;
            res.add(level);
        }
        
        return res;
    }
    
    private List<List<Integer>> help1(TreeNode root)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        
        Deque<TreeNode> current = new LinkedList<>();
        Deque<TreeNode> next = new LinkedList<>();
        boolean isOdd = true;
        current.add(root);
        
        while(!current.isEmpty() )
        {
            int size = current.size();
            List<Integer> level = new LinkedList<Integer>();
            if(isOdd)
            {
                for(int i = 0; i < size; i++)
                {
                    TreeNode top = current.removeFirst();
                    level.add(top.val);
                    
                    if(top.left != null){
                        next.addLast(top.left);
                    }
                    
                    if(top.right != null){
                        next.addLast(top.right);
                    }
                }
            }else{
                for(int i = 0; i < size; i++)
                {
                    TreeNode top = current.removeLast();
                    level.add(top.val);
                    
                    if(top.right != null)
                    {
                        next.addFirst(top.right);
                    }
                    
                    if(top.left != null)
                    {
                        next.addFirst(top.left);
                    }
                }
            }
            isOdd = !isOdd;
            current = next;
            res.add(level);
        }
        
        return res;
    }
}