/*
109. Convert Sorted List to Binary Search Tree My Submissions Question
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
    ListNode curr;
    
    public TreeNode sortedListToBST(ListNode head) {
        curr = head;
        return help0(head);
    }
    
    // O(N) solution
    //   bottom-up solotion
    private TreeNode help0(ListNode head)
    {
        int l = 0;
        int r = getLength(head);
        
        return dfs2(0, r-1);
    }
    
    private TreeNode dfs2(int l, int r)
    {
        if(l > r) return null;
        
        if(l == r) {
            TreeNode newNode = new TreeNode(curr.val);
            curr = curr.next;
            return newNode;
        }
       // return new TreeNode(curr.val);
        
        int m = l + ((r-l) >> 1);
        TreeNode left = dfs2(l, m-1);
        TreeNode root = new TreeNode(curr.val);
        curr = curr.next;
        root.right = dfs2(m+1, r);
        root.left = left;
        
        return root;
    }
    
     private TreeNode dfs3(int l, int r)
    {
        if(l > r) return null;
        
        int m = l + ((r-l) >> 1);
        
        TreeNode left = dfs2(l, m-1);
        TreeNode root = new TreeNode(curr.val);
        //after we use curr val, we need advance the curr
        //  so that we do not re-use same pointer again
        curr = curr.next;
        root.right = dfs2(m+1, r);
        root.left = left;
        
        return root;
    }
    
    // O (nlogn), time out!!!
    // top-down solution, we need find median every time
    private TreeNode help(ListNode head)
    {
        int l = 0;
        int r = getLength(head);
        
        return dfs(head, l, r-1);
    }
    
    private TreeNode dfs(ListNode head, int l, int r)
    {
        if( l > r){
            return null;
        }
        if(l == r){
            return new TreeNode(head.val);
        }
        
        int mid = l + ((r-l)>>1);
        ListNode m = findMedian(head);
        TreeNode root = new TreeNode(m.val);
        root.right = dfs(m.next, mid+1, r);
        root.left = dfs(head, l, mid-1);
        
        return root;
    }
    
    private ListNode findMedian(ListNode head)
    {
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private int getLength(ListNode head)
    {
        int len = 0;
        while(head != null){
            head = head.next;
            len++;
        }
        return len;
    }
}