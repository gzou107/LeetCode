/*
272. Closest Binary Search Tree Value II My Submissions Question
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.
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

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        return helper(root, target, k);
       // return helper1(root, target, k);
    }
    
    private List<Integer> helper(TreeNode root, double target, int k)
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Queue<Integer> q = new LinkedList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        int count = 0;
        while(root != null){
            s.push(root);
            root = root.left;
        }
        
        while(!s.isEmpty())
        {
            TreeNode top = s.pop();
            if(count < k)
            {
                q.add(top.val);
            }else{
                // performance improvement here, we do not further transverse once we meet un favorable one
                Integer first = q.peek();
                if(Math.abs(first - target) > Math.abs(top.val - target))
                {
                    q.poll();
                    q.offer(top.val);
                }else{
                    break;
                }
            }
            
            /*else if(Math.abs(top.val - target) < Math.abs(q.peek() - target))
            {
                q.remove();
                q.add(top.val);
            }*/
            
            if(top.right != null)
            {
                top = top.right;
                while(top != null){
                    s.add(top);
                    top = top.left;
                }
            }
            count++;
        }
        
        while(!q.isEmpty()){
            res.add(q.poll());
        }
        return res;
    }
    
    private List<Integer> helper1(TreeNode root, double target, int k)
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Stack<TreeNode> small = new Stack<TreeNode>();
        Stack<TreeNode> big = new Stack<TreeNode>();
        
        initIterator(root, target, small, big);
        int count = 0;
        while(count < k )
        {
            if(small.isEmpty()|| !big.isEmpty() && target - small.peek().val > big.peek().val - target){
                res.add(big.peek().val);
                updateBig(big);
            }else{
                res.add(small.peek().val);
                updateSmall(small);
            }
            
            count++;
        }
        return res;
    }
    
    private void updateSmall(Stack<TreeNode> s)
    {
        TreeNode top = s.pop();
        if(top.left != null){
            top = top.left;
            while(top != null)
            {
                s.push(top);
                top = top.right;
            }
        }
    }
    
    private void updateBig(Stack<TreeNode> s)
    {
        TreeNode top = s.pop();
        if(top.right != null)
        {
            top = top.right;
            while(top != null)
            {
                s.push(top);
                top = top.left;
            }
        }
    }
    
    private void initIterator(TreeNode root, double target, Stack<TreeNode> pre, Stack<TreeNode> suc)
    {
        while(root != null)
        {
            if(root.val < target)
            {
                pre.push(root);
                root = root.right;
            }else{
                suc.push(root);
                root = root.left;
            }
        }
    }
}