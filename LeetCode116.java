/*
116. Populating Next Right Pointers in Each Node My Submissions Question
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        //help0(root);
        //help1(root);
        //help2(root);
        
        if(root != null){
            help3(root.left, root.right);
        }
    }
    
    private void help3(TreeLinkNode l, TreeLinkNode r)
    {
        if(l == null || r == null){
            return;
        }
        
        l.next = r;
        help3(l.left, l.right);
        help3(l.right, r.left);
        help3(r.left, r.right);
    }
    private void help2(TreeLinkNode root)
    {
        if(root == null || root.left == null){
            return;
        }
        
        root.left.next = root.right;
        if(root.next != null)
        {
            root.right.next = root.next.left;
        }

        help2(root.left);
        help2(root.right);
    }
    private TreeLinkNode help0(TreeLinkNode root)
    {
        if(root == null || (root.left == null && root.right == null)){
            return root;
        }
        
        TreeLinkNode l = help0(root.left);
        TreeLinkNode r = help0(root.right);
        
        if( l == null || r == null)
        {
            return null;
        }
        l.next = r;
        while(l.right != null && l.right.next == null)
        {
            l.right.next = r.left;
            l = l.right;
            r = r.left;
        }
        
        return root;
    }
    private void help1(TreeLinkNode root)
    {
        if(root == null) return;
        
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty())
        {
            int size = q.size();
            TreeLinkNode prev = null;
            for(int i = 0; i < size; i++)
            {
                TreeLinkNode t = q.poll();
                if(prev == null){
                    prev = t;
                }else{
                    prev.next = t;
                    prev = t;
                }
                
                if(t.left != null){
                    q.offer(t.left);
                }
                
                if(t.right != null){
                    q.offer(t.right);
                }
            }
        }
    }
}