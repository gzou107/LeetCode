/*
Remove Duplicates from Sorted List My Submissions Question
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        
        if(head == null) return head;
        
        ListNode current = head.next;
        ListNode newHead = head;
        ListNode tail = head;
        
        while(current != null){
            if(current.val != tail.val){
                tail.next = current;
                tail = current;
            }
            current = current.next;
        }
        tail.next = null;
        
        return newHead;
    }
}