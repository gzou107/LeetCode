/*
61. Rotate List My Submissions Question
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
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
    public ListNode rotateRight(ListNode head, int k) {
        return helper(head, k);
    }
    
    private ListNode helper(ListNode head, int k)
    {
        if(k <= 0 || head == null) return head;
        
        int len = getLen(head);
        if( k % len == 0) return head;
        
        // now we find the new head by advance the head pointer k %len
        k = k%len;
        int pos = 0;
        ListNode newHead = head;
        while(pos < len - k - 1) // the left side is len -k rather than k, we want to set the new tail, hence -1
        {
            newHead = newHead.next;
            pos++;
        }
        
        ListNode res = newHead.next;
        ListNode resTail = res;
        while(resTail.next != null)
        {
            resTail = resTail.next;
        }
        newHead.next = null;
        resTail.next = head;
        
        return res;
    }
    
    private int getLen(ListNode head)
    {
        int len = 0;
        while(head != null)
        {
            head = head.next;
            len++;
        }
        return len;
    }
}