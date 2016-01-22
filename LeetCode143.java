/*
143. Reorder List My Submissions Question
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
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
    public void reorderList(ListNode head) {
         help(head);
    }
    
    private void help(ListNode head){
        
        if(head == null || head.next == null) return;
        
        // first split the list into two
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode curr = slow.next;
        //handle the last block
        slow.next = null;
        
        // now reverse l1
        ListNode prev = null;
        ListNode ne = curr.next;
        while(ne != null)
        {
            curr.next = prev;
            prev = curr;
            curr = ne;
            ne = ne.next;
        }
        curr.next = prev;
        
        // now merge the list l0 and curr
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        ListNode l0 = head;
        boolean l0Turn = true;
        while(l0 != null || curr != null)
        {
            if(l0Turn && l0 != null){
                tail.next = l0;
                l0 = l0.next;
            }else if(curr != null){
                tail.next = curr;
                curr = curr.next;
            }
            tail = tail.next;
            l0Turn = !l0Turn;
        }
    }
}