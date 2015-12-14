/*
Remove Linked List Elements My Submissions Question
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
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
    public ListNode removeElements(ListNode head, int val) {
        
        if(head == null){
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode newHead = dummy;
        ListNode newTail = dummy;
        while(head != null){
            if(head.val != val){
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }
        newTail.next = null;
        
        return newHead.next;
        
    }
}