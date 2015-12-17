/*
Remove Nth Node From End of List My Submissions Question
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
       //return helper(head, n);
       
       return helper2(head, n);
    }
    
    private ListNode helper2(ListNode head, int n){
            if(head == null)
                return null;
 
            ListNode fast = head;
            ListNode slow = head;
         
            for(int i=0; i<n; i++){
                fast = fast.next;
            }
         
            //if remove the first node
            if(fast == null){
                head = head.next;
                return head;
            }
         
            while(fast.next != null){
                fast = fast.next;
                slow = slow.next;
            }
         
            slow.next = slow.next.next;
         
            return head;
    }
    
    private ListNode helper(ListNode head, int n){
        // we should save the head information
        // save the precessor of the 'exist' to be deleted node
        // we need advace fast pointer by n+1 step
        
        if(n <= 0 || head == null) return head;
 
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode front = dummy;
        ListNode tail = dummy;
        int i = 0;
        while(front != null && i < n+1){
            front = front.next;
            i++;
        }
        
        // handle two special cases here: either n bigger than list length or n == list length
        // if we assume the n is always valid, then when front == null, we want to remove head
        if(front == null ){
            return dummy.next.next;
          /*
          if(i == n + 1)
          {
            return dummy.next.next;
          }else{
            return dummy.next;  
          }*/
        }
        
        while(front != null){
            front = front.next;
            tail = tail.next;
        }
        
       // if(tail.next != null)
        {
            tail.next = tail.next.next;
        }
        
        return dummy.next;
    }
}