/*
142. Linked List Cycle II My Submissions Question
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

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
    public ListNode detectCycle(ListNode head) {
        return helper0(head);
    }
    
    public ListNode helper0(ListNode head)
    {
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        
        if(fast == null || fast.next == null) return null;
        
        slow = head;
        while(slow != fast)
        {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public ListNode helper1(ListNode head)
    {
        Set<ListNode> s = new HashSet<ListNode>();
        while(head != null)
        {
            if(s.contains(head)) return head;
            s.add(head);
            head = head.next;
        }
        return null;
    }
}