/*
25. Reverse Nodes in k-Group My Submissions Question
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if( k <= 0) return head;
        return help(head, k);
    }
    
    private ListNode help(ListNode head, int k)
    {
        ListNode dummy = new ListNode(0);
        ListNode t = dummy;
        dummy.next = head;
        ListNode prev = null;
        
        while(hasEnoughNodes(head, k))
        {
            ListNode tail   = advanceKNodes(head, k);
            ListNode [] res = swap(head, prev, tail);
            t.next = res[0];
            t = res[1];
            head = res[1].next;
            prev = res[1];
        }
        return dummy.next;
    }
    
    private boolean hasEnoughNodes(ListNode head, int k)
    {
        while(head != null && k > 0)
        {
            head = head.next;
            k--;
        }
        return k == 0;
    }
    
    private ListNode advanceKNodes(ListNode head, int k)
    {
        while(head != null && k > 0)
        {
            head = head.next;
            k--;
        }
        return head;
    }
    
    // swap k nodes between [head, tail), and return tail, which is the new
    //   starting point
    private ListNode[] swap(ListNode head, ListNode prev, ListNode tail)
    {
        ListNode current = head;
        ListNode next = current.next;
        ListNode t = head;
        
        while(next != tail)
        {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }
        current.next = prev;
        t.next = tail;
        return new ListNode[]{current, t}; //current is head of the reverse linkedlist
        // t is the tail of reversed linkedlist
    }
}