/*
82. Remove Duplicates from Sorted List II My Submissions Question
Total Accepted: 63294 Total Submissions: 241950 Difficulty: Medium
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
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
        return help(head);
    }
    
    private ListNode help(ListNode head)
    {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while(head != null)
        {
            int count = 1;
            while(head.next != null && head.val == head.next.val)
            {
                head = head.next;
                count++;
            }
            
            if(count == 1)
            {
                tail.next = head;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null;
        return dummy.next;
    }
}