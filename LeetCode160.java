/*
Intersection of Two Linked Lists My Submissions Question
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        
        if(lenA > lenB){
            headA = advanceHead(headA,lenA-lenB);
        }else{
            headB = advanceHead(headB,lenB-lenA);
        }
        
        while(headA!= null){
            if(headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    
    private static int getLength(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
    
    // n must be greater or equal 0.
    private static ListNode advanceHead(ListNode head, int n){
        while(n >0){
            n--;
            head = head.next;
        }
        return head;
    }
}