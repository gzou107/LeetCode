/*
230. Kth Smallest Element in a BST My Submissions Question
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
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
    public int kthSmallest(TreeNode root, int k) {
        
      //  return helper1(root, k);
        
        return helper2(root, k);
    }
    
    private int helper2(TreeNode root, int k){
        int ans = 0;
        Stack<TreeNode> s = new Stack<TreeNode>();
        while(root != null){
            s.push(root);
            root = root.left;
        }
        
        int count = 0;
        while(!s.isEmpty() && count < k){
            TreeNode top = s.pop();
            ans = top.val;
            count++;
            
            if(top.right != null){
                TreeNode t = top.right;
                while( t != null){
                    s.push(t);
                    t = t.left;
                }
            }
        }
        
        return ans;
    }
    
    
    private int helper1(TreeNode root, int k){
        InOrderIterator it = new Solution.InOrderIterator(root);
        int count = 0;
        int ans = 0;
        while(count < k){
            ans = it.next().val;
            count++;
        }
        return ans;
    }
    private static class InOrderIterator{
        private Stack<TreeNode> s;
        
        public InOrderIterator(TreeNode root){
            s = new Stack<TreeNode>();
            while(root != null){
                s.push(root);
                root = root.left;
            }
        }
        
        public boolean hasNext(){
            return !s.isEmpty();
        }
        
        public TreeNode next(){
        
            TreeNode top = s.pop();
            if(top.right != null){
                TreeNode t = top.right;
                while(t != null){
                    s.push(t);
                    t = t.left;
                }
            }
            return top;
        }
    }
}