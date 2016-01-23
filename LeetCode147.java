/*
147. Insertion Sort List My Submissions Question
Sort a linked list using insertion sort.
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
    public ListNode insertionSortList(ListNode head) {
        return help0(head);
        //return help(head);
    }
    
    public ListNode help0(ListNode head)
    {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        
        // for every round, find the minimum from the rest
        while(head != null)
        {
            // now min point to the minumum
            ListNode node = dummy;
            ListNode temp = head.next;
            
            while(node.next != null && node.next.val <= head.val){
                node = node.next;
            }
            // now insert tail into right position
            head.next = node.next;
            // adjust the 
            node.next = head;
            
            // now we have the right position for 
            head = temp;
        }
        
        return dummy.next;
    }
    
    private ListNode[] getMinAndPrev(ListNode head)
    {
        if(head == null) return new ListNode[]{null, null};
        
        if(head.next == null) return new ListNode[]{null, head};
        
        ListNode prev = null;
        ListNode min = head;
        head = head.next;
        
        while(head != null){
            if(min.val <= head.val){
                prev = head;
            }else{
                min = head;
            }
           head = head.next;
        }
        return new ListNode[]{prev, min};
        
    }
    
    public ListNode help(ListNode head)
    {
        if(head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        // for every round, find the minimum from the rest
        while(head != null)
        {
            // now min point to the minumum
            ListNode min = getMin(head);
            tail.next = min;
            tail = tail.next;
            
            ListNode prev = getPrevOfMin(head, min);
            if(prev == null){
                // head is minimum element
                head = head.next;
            }else{
                prev.next = min.next;
                min.next = head;
            }
        }
        
        return dummy.next;
    }
    
    private ListNode getMin(ListNode head)
    {
        if(head == null || head.next == null){
            return head;
        }
        
        // now get the min
        ListNode min = head;
        head = head.next;
        
        while(head != null)
        {
            if(min.val > head.val)
            {
                min = head;
            }
            head = head.next;
        }
        return min;
    }
    
    private ListNode getPrevOfMin(ListNode head, ListNode min)
    {
        if(head == null || min == null) return null;
        
        while(head != null){
            if(head.next == min){
                return head;
            }
            head = head.next;
        }
        return null;
    }
    
}