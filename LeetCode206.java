/*
Reverse Linked List My Submissions Question
Reverse a singly linked list.
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
    public ListNode reverseList(ListNode head) {
        
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode ne = head.next;
        
        while( ne != null){
            curr.next = prev;
            prev = curr;
            curr = ne;
            ne = ne.next;
        }
        
        curr.next = prev;
        return curr;
    }
}