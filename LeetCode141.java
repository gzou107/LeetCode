/*
141. Linked List Cycle My Submissions Question
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        return helper(head);
    }
    
    private boolean helper(ListNode head)
    {
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean helper0(ListNode head)
    {
        if(head == null) return false;
        
        ListNode fast = head.next;
        ListNode slow = head;
        
        while(fast != null && fast.next != null)
        {
            if(fast == slow){
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return false;
    }
    
    private boolean helper1(ListNode head)
    {
        Set<ListNode> s = new HashSet<ListNode>();
        
        while(head != null)
        {
            if(s.contains(head)) return true;
            s.add(head);
            head = head.next;
        }
        return false;
    }
}