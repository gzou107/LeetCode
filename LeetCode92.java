/*
92. Reverse Linked List II My Submissions Question
Total Accepted: 61221 Total Submissions: 226904 Difficulty: Medium
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        return helper(head, m, n);
    }
    
    private ListNode helper(ListNode head, int m, int n)
    {
        if(head == null || head.next == null || m == n) return head;
        // as we can change the head, it's always recommended to add a dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        int pos = 1;
        while(pos < m)
        {
            pos++;
            prev = prev.next;
        }
        // now pos == m
        ListNode start = prev.next;
        ListNode then = start.next;
        // we use three pointers to do the reverse
        // and we fix the pre, which is the tail of the first section
        // and we keep on updating the reversed section head(then), and tail
        while(pos < n )
        {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
            pos++;
        }

        return dummy.next;
    }
    
    private ListNode helper0(ListNode head, int m, int n)
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++)
        {
            pre = pre.next;
        }
        
        ListNode cur = pre.next;
        ListNode ne = null;
        for (int i = 0; i < n - m; i++) {
            ne = cur.next;
            cur.next = ne.next;
            ne.next = pre.next;
            pre.next = ne;
        }
        return dummy.next;
    }
}