/*
24. Swap Nodes in Pairs My Submissions Question
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

Show Company Tags
Show Tags
Show Similar Problems
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
    public ListNode swapPairs(ListNode head) {
        return help(head);
    }
    
    private ListNode help(ListNode head)
    {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        ListNode prev = head;
        ListNode current = head.next;
        ListNode ne = current == null ? null : current.next;
        
        while(current != null)
        {
            tail.next = current;
            current.next = prev;
            prev.next = ne;
            
            // now move to next round if applicable
            if(ne == null || ne.next == null){
                break;
            }else{
                tail = prev;
                prev = ne;
                current = ne.next;
                ne = current == null? null : current.next;
            }
        }
        
        return dummy.next;
    }
}