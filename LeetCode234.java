/*
Palindrome Linked List My Submissions Question
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        
        int len = length(head);
        ListNode newNode = middle(head);
       
       // we omit the middle one if len is odd.
        if(len % 2 == 1){
            newNode = newNode.next;
        }
        newNode = reverse(newNode);
        
        while(head != null && newNode != null){
                if(head.val != newNode.val){
                    return false;
                }else{
                    head = head.next;
                    newNode = newNode.next;
                }
            }
            
        return true;
    }
    
    private static ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;
        
        while(next != null){
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        return curr;
    }
    
    private static int length(ListNode head){
        int len = 0;
        while(head != null){
            head = head.next;
            len++;
        }
        return len;
    }
    
   private static ListNode middle(ListNode head){
       ListNode slow = head;
       ListNode fast = head;
       
       while(fast != null && fast.next != null){
           slow = slow.next;
           fast = fast.next.next;
       }
       
       return slow;
   } 
}