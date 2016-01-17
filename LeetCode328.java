/*
328. Odd Even Linked List My Submissions Question
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
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
    public ListNode oddEvenList(ListNode head) {
        return helper(head);
    }
    
    public ListNode helper(ListNode head)
    {
        ListNode oddHead = new ListNode(0);
        ListNode oddTail = oddHead;
        ListNode evenHead = new ListNode(0);
        ListNode evenTail = evenHead;
        
        boolean isOdd = true;
        while(head != null)
        {
            if(isOdd){
                oddTail.next = head;
                oddTail = oddTail.next;
            }else{
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            isOdd = !isOdd;
            head = head.next;
        }
        oddTail.next = evenHead.next;
        evenTail.next = null;
        return oddHead.next;
    }
}